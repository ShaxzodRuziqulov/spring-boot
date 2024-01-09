package com.example.demo.Service;

import com.example.demo.Domain.FileStorage;
import com.example.demo.Domain.FileStorageStatus;
import com.example.demo.Repository.FileStorageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    private final Hashids hashids;


    @Value("${upload.folder}")
    private String uploadFolder;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName());
    }

    public void save(MultipartFile multipartFile) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(String.valueOf(multipartFile.getSize()));
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorageRepository.save(fileStorage);
        Date now = new Date();
        File uploadFolder = new File(String.format("%s/upload_files/%d/%d/%d/",
                this.uploadFolder,
                1900 + now.getYear(),
                1 + now.getMonth(),
                now.getDate()));
        if (!uploadFolder.exists() && uploadFolder.mkdirs()) {
            System.out.println("papkallar yaratildi");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s", 1900 + now.getYear(), 1 + now.getMonth(), now.getDate(), fileStorage.getHashId(), fileStorage.getExtension()));
        fileStorageRepository.save(fileStorage);
        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder, String.format("%s.%s", fileStorage.getHashId(), fileStorage.getExtension()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public FileStorage findByHashId(String hashId){
        return fileStorageRepository.findByHashId(hashId);
    }

    public void delete(String hashId){
        FileStorage fileStorage = findByHashId(hashId);
        File file = new File(String.format("%s/%s",this.uploadFolder,fileStorage.getUploadPath()));
        if (file.delete()){
            fileStorageRepository.delete(fileStorage);
        }
    }
    @Scheduled(cron = "0 0 0 * * * ")
    public void deleteAlldraft(){
        List<FileStorage> fileStoragesList = (List<FileStorage>) fileStorageRepository.findAllByFileStorageStatus(FileStorageStatus.DRAFT);
//        for (FileStorage fileStorage : fileStoragesList){
//            delete(fileStorage.getHashId());
//        }
        fileStoragesList.forEach(fileStorage ->
                delete(fileStorage.getHashId()));
    }

    public String getExt(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }

}

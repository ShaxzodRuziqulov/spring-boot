package com.example.demo.WebRest;

import com.example.demo.Domain.FileStorage;
import com.example.demo.Service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api")
public class FileStorageResourse {
    private final FileStorageService fileStorageService;

    @Value("${upload.folder}")
    private String uploadFolder;

    public FileStorageResourse(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile multipartFile) {
        fileStorageService.save(multipartFile);
        return ResponseEntity.ok(multipartFile.getOriginalFilename() + "fayllar saqlandi");
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity previewFile(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + UriEncoder.encode(fileStorage.getName()))
                    .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                    .contentLength(Long.parseLong(fileStorage.getFileSize()))
                    .body(new FileUrlResource(String.format("%s/%s",uploadFolder,fileStorage.getUploadPath())));

    }
    @GetMapping("/download/{hashId}")
    public ResponseEntity downloadFile(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + UriEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(Long.parseLong(fileStorage.getFileSize()))
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,fileStorage.getUploadPath())));

    }
    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity delete(@PathVariable String hashId){
        fileStorageService.delete(hashId);
        return ResponseEntity.ok("Fayl o'chirildi");
    }

}

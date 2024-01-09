package com.example.demo.Domain;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "file_storage")
public class FileStorage implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String extension;
    private String fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;
    @Enumerated(EnumType.STRING)
    private FileStorageStatus fileStorageStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public FileStorageStatus getFileStorageStatus() {
        return fileStorageStatus;
    }

    public void setFileStorageStatus(FileStorageStatus fileStorageStatus) {
        this.fileStorageStatus = fileStorageStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

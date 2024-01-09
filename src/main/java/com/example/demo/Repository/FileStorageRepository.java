package com.example.demo.Repository;

import com.example.demo.Domain.FileStorage;
import com.example.demo.Domain.FileStorageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {
    FileStorage findByHashId(String hashId);

    FileStorage findAllByFileStorageStatus(FileStorageStatus status);
}

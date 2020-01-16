package com.insat.maktabti.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileService {

    private static final String FILE_DIRECTORY = "E:\\GL4\\angular\\front-maktabti\\src\\assets\\images\\books\\";

    private static final String USER_FILE_DIRECTORY = "E:\\GL4\\angular\\front-maktabti\\src\\assets\\images\\users\\";


    public String storeFile(MultipartFile file, String prefix, long id, String type) throws IOException {
        String fileName = getBaseName(file.getOriginalFilename()) + prefix + id + getExtension(file.getOriginalFilename());
        if (type.equals("book")) {
            Path filePath = Paths.get(FILE_DIRECTORY + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } else {
            Path filePath = Paths.get(USER_FILE_DIRECTORY + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }


    }
    public static String getBaseName(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }
    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }

        int index = filename.lastIndexOf('.');

        if (index == -1) {
            return filename;
        } else {
            return filename.substring(index);
        }
    }
}

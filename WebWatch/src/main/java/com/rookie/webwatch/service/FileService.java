package com.rookie.webwatch.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
    public String uploadFile(File file, String fileName) throws IOException;
    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException;
    public String getExtension(String fileName);
    public Object upload(MultipartFile multipartFile) throws IOException;
    public Object download(String fileName) throws IOException;
}

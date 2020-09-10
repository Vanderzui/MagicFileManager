package com.service;

public interface FileService {
     abstract void createFile(String path, String fileName);

     abstract void createDirectory(String path, String fileName);

     abstract void delete(String path, String fileName);

     abstract void write(String path, String fileName, String text);

     abstract void open(String path, String fileName);
}

package com.service;

import java.io.File;
import java.util.List;

public interface FileService {
     abstract void createFile(String path, String fileName);

     abstract void createDirectory(String path, String fileName);

     abstract void delete(String path, String fileName);

     abstract void write(String path, String fileName, String text);

     abstract void openFile(String path, String fileName);

     abstract List<String> openDirectory(String path, String fileName);
}

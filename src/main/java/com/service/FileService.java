package com.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
     abstract void createFile(String path, String fileName);

     abstract void createDirectory(String path, String fileName);

     abstract void delete(String path, String fileName);

     abstract void write(String path, String fileName, String text);

     abstract String openFile(String path) throws IOException;

     abstract List<String> getFileNames(String path, String fileName);

     abstract List<String> getDirectoryNames(String path, String fileName);
}

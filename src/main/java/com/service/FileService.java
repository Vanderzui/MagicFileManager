package com.service;

import com.dto.FileDto;

import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    String getRootDir() throws IOException;

    FileDto createFile(String path, String fileName);

    FileDto createDirectory(String path, String name);

    String checkURL(String path);

    void delete(String path);

    void write(String path, String text);

    FileDto openFile(String path) throws IOException;

    List<FileDto> getFileNames(String path) throws IOException;

    List<FileDto> getDirectoryNames(String path);

    Map<String, String> openNote(String fileName);

    void makeNote(String path, String text);

    void deleteNote(String path);

    String getFileName(Part part);

    String getFileExtension(String name);

    String getIcons(String extension) throws IOException;


}

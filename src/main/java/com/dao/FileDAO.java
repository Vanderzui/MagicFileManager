package com.dao;

import com.entities.FileEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileDAO {
    FileEntity openFile(String path) throws IOException;

    List<FileEntity> getFileNames(String path);

    List<FileEntity> getDirectoryNames(String path);

    FileEntity createDirectory(String path, String name);

    FileEntity createFile(String path, String name);

    void write(String path, String text);

    Map<String, String> openNote(String path);

    void makeNote(String path, String text);

    void deleteNote(String path);

}

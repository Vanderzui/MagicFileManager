package com.dao;

import com.entities.FileEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileDAO {
    abstract public FileEntity openFile(String path) throws IOException;

    abstract public List<FileEntity> getFileNames(String path);

    abstract public List<FileEntity> getDirectoryNames(String path);

    abstract FileEntity createDirectory(String path, String name);

    abstract public FileEntity createFile(String path, String name);

    abstract public void write(String path, String text);

    Map<String, String> openNote (String path);

    void makeNote(String path, String text);

}

package com.dao;
import com.entities.FileEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface FileDAO {
    FileEntity openFile(String path) throws IOException;

    List<FileEntity> getFileNames(String path);

    List<FileEntity> getDirectoryNames(String path);

    FileEntity createDirectory(String path, String name);

    FileEntity createFile(String path, String name);

    void write(String path, String text);

    String openNote(String path) throws IOException, SQLException;

    void makeNote(String path, String text) throws IOException, SQLException;

    void deleteNote(String path) throws IOException, SQLException;

}

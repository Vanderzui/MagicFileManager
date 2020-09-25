package com.service;

import com.dto.FileDto;
import com.entities.FileEntity;
import com.model.FileModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
     abstract FileDto createFile(String path, String fileName);

     abstract FileDto createDirectory(String path, String name);

     abstract void delete(String path);

     abstract void write(String path, String text);

     //abstract String openFile(String path) throws IOException;

     abstract FileDto openFile(String path) throws IOException;

//     abstract List<String> getNames(String path, String fileName);

     abstract List<FileDto> getFileNames(String path);

     abstract List<FileDto> getDirectoryNames(String path);

     abstract void openNote(File file);

     abstract void writeToNote(String text);
}

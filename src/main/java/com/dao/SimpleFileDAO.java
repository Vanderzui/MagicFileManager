package com.dao;

import com.entities.FileEntity;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileDAO implements FileDAO{
    FileEntity fileEntity = new FileEntity();
    File file;

    @Override //works!
    public FileEntity createFile(String path, String name) {
        file = new File(path, name);
        if (!file.exists()) {
            try {
                file.createNewFile();
                fileEntity.setName(file.getName());
                fileEntity.setPath(file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileEntity;
    }

    @Override //it works!
    public FileEntity openFile(String path) throws IOException {
        File file = new File(path);
        String text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        fileEntity.setPath(path);
        fileEntity.setText(text);
        return fileEntity;

    }

    @Override //работает!
    public List<FileEntity> getFileNames(String path) {
        file = new File(path);
        List<FileEntity> filesList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File f: files) {
            if(f.isFile()) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(f.getName());
                filesList.add(fileEntity);
            }
        }
        return filesList;
    }

    @Override //it works!
    public List<FileEntity> getDirectoryNames(String path) {
        file = new File(path);
        List<FileEntity> dirList = new ArrayList<>();
        File[] dirs = file.listFiles();
        for (File d: dirs) {
            if(d.isDirectory()) {
                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(d.getName());
                dirList.add(fileEntity);
            }
        }
        return dirList;
    }


    @Override //works
    public FileEntity createDirectory(String path, String name) {
        File file = new File(path, name);
        if (!file.exists()) {
            file.mkdir();
        }
        fileEntity.setName(file.getName());
        fileEntity.setPath(file.getPath());
        return fileEntity;
    }

    @Override
    public void write(String path, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), true))) {
            writer.write(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

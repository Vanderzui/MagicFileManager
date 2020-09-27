package com.dao;

import com.entities.FileEntity;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleFileDAO implements FileDAO{
    Map<String, Map<String, String>> fileNotes = new HashMap<>();

    @Override //works!
    public FileEntity createFile(String path, String name) {
        FileEntity fileEntity = new FileEntity();
        File file = new File(path, name);
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
        FileEntity fileEntity = new FileEntity();
        File file = new File(path);
        String text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        fileEntity.setPath(path);
        fileEntity.setText(text);
        return fileEntity;

    }

    @Override //работает!
    public List<FileEntity> getFileNames(String path) {
        File file = new File(path);
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
        File file = new File(path);
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
        FileEntity fileEntity = new FileEntity();
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



    @Override
    public Map<String, String> openNote(String path) {
        if(!fileNotes.containsKey(path)) {
            fileNotes.put(path, new HashMap<>());
        }
        Map<String, String> notesMap = fileNotes.get(path);
        return notesMap;
    }

    @Override
    public void makeNote(String path, String text) {
//        FileEntity fileEntity = new FileEntity();
//        Map<String, String> notes = fileEntity.getNotes();
        Map<String, String> mapNotes = fileNotes.get(path);
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(currentDate);
        mapNotes.put(date, text);
    }

}

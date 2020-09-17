package com.service;

import com.converter.FileToModelConverter;
import com.model.FileModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleFileService implements FileService {
    @Override
    public void createFile(String path, String fileName) {
        File file = new File(path, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Такой файл уже существует");
        }
    }

    @Override
    public void createDirectory(String path, String fileName) {
        File file = new File(path, fileName);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public void delete(String path, String fileName) {
        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }
    }


    @Override
    public void write(String path, String fileName, String text) {
        FileToModelConverter fTM = new FileToModelConverter();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fTM.fileModelToFile(new FileModel(path)), true))) {
            writer.write(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public String openFile(String path) {
//        //писать проверку, что это файл??
//        String currentLine;
//        StringBuilder builder = new StringBuilder();
//        try (BufferedReader bf = new BufferedReader(new FileReader(new File(path)))) {
//            currentLine = bf.readLine();
//            while (currentLine != null) {
//                builder.append(currentLine);
//                builder.append("\n");
//                currentLine = bf.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }

    @Override
    public String openFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }

    @Override
    public List<String> getFileNames(String path, String fileName) {
        //изменить на модель!!
        File file = new File(path, fileName);
        List<String> filesList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File f: files) {
            if(f.isFile()) {
                filesList.add(f.getName());
            }
        }
        return filesList;
    }

    @Override
    public List<String> getDirectoryNames(String path, String fileName) {
        File file = new File(path, fileName);
        List<String> dirList = new ArrayList<>();
        File[] dirs = file.listFiles();
        for (File d: dirs) {
            if(d.isDirectory()) {
                dirList.add(d.getName());
            }
        }
        return dirList;
    }

    private void wrongOpen(String pathName, String fileName) {
        BufferedInputStream readFile = null;
        try {
            readFile = new BufferedInputStream(new FileInputStream(new File(pathName, fileName)), 200);
            int i;
            while ((i = readFile.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (readFile != null) {
                try {
                    readFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

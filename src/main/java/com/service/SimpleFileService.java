package com.service;

import com.converter.FileToModelConverter;
import com.model.FileModel;

import java.io.*;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fTM.fileModelToFile(new FileModel(path, fileName)), true))) {
            writer.write(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openFile(String path, String fileName) {
        //писать проверку, что это файл??
        FileToModelConverter fTM = new FileToModelConverter();
        try (FileReader fileReader = new FileReader(fTM.fileModelToFile(new FileModel(path, fileName)))) {
            char[] buf = new char[256];
            int c;
            while ((c = fileReader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getFileNames(String path, String fileName) {
        //изменить на модель!!
        File file = new File(path, fileName);
        List<String> files = new ArrayList<>();
        if (file.isFile()) {
            String dirPath = file.getAbsolutePath();
            files = Stream.of(new File(dirPath)
                    .listFiles())
                    .map(File::getName)
                    .collect(Collectors.toList());
        }
        return files;
    }

    @Override
    public List<String> getDirectoryNames(String path, String fileName) {
        File file = new File(path, fileName);
        List<String> directories = new ArrayList<>();
        if (file.isDirectory()) {
            String dirPath = file.getAbsolutePath();
            directories = Stream.of(new File(dirPath)
                    .listFiles())
                    .map(File::getName)
                    .collect(Collectors.toList());
        }
        return directories;
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

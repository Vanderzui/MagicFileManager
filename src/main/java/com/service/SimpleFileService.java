package com.service;

import java.io.*;
import java.util.Arrays;

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
        if (file.exists()) {                      //какая-то хрень
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {   //так и в чем отличие
                file.delete();
            }
        } else {
            System.out.println("Такого чего-то не существует");    //???
        }
    }


    @Override
    public void write(String path, String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path, fileName), true))) {
            writer.write(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void open(String path, String fileName) {
        try (FileReader fileReader = new FileReader(new File(path, fileName))) {
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

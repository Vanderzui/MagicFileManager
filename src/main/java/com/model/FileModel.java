package com.model;

public class FileModel {
    String fileName;
    String path;

    public FileModel() {
    }

    public FileModel(String path, String fileName) {
        this.fileName = fileName;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

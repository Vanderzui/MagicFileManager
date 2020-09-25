package com.entities;

import java.io.File;

public class FileEntity {
    String path;
    String name;
    String text;

    public FileEntity() {
    }

    public FileEntity(String text) {
        this.text = text;
    }

    public FileEntity(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public FileEntity(String path, String name, String text) {
        this.path = path;
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return name;
    }
}

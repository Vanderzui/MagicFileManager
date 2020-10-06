package com.model;

import java.util.HashMap;
import java.util.Map;

public class FileModel {
    String text;
    String path;
    String name;


    public FileModel(String text, String path, String name) {
        this.text = text;
        this.path = path;
        this.name = name;
    }

    public FileModel(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

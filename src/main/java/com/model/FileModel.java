package com.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileModel {
    String text;
    String path;
    String name;
    private Map<String, String> notes = new HashMap<>();

    public Map<String, String> getNotes() {
        return notes;
    }

    public FileModel(String text, String path, String name, Map<String, String> notes) {
        this.text = text;
        this.path = path;
        this.name = name;
        this.notes = notes;
    }

    public FileModel(String text) {
        this.text = text;
    }

    public FileModel(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public FileModel(String path, String name, String text) {
        this.path = path;
        this.name = name;
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

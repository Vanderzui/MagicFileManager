package com.entities;

import java.util.HashMap;
import java.util.Map;

public class FileEntity {
    private String path;
    private String name;
    private String text;
    private Map<String, String> notes = new HashMap<>();

    public FileEntity() {
    }

    public Map<String, String> getNotes() {
        return notes;
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

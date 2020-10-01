package com.dto;

import java.util.HashMap;
import java.util.Map;

public class FileDto {
    String path;
    String name;
    String text;
    private Map<String, String> notes = new HashMap<>();
    String icon;

    public Map<String, String> getNotes() {
        return notes;
    }

    public FileDto(String path, String name, String text, Map<String, String> notes) {
        this.path = path;
        this.name = name;
        this.text = text;
        this.notes = notes;
    }

    public FileDto() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public FileDto(String icon) {
        this.icon = icon;
    }

    public FileDto(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public FileDto(String path, String name, String text) {
        this.path = path;
        this.name = name;
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

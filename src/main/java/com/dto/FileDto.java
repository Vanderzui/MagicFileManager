package com.dto;

import com.entities.Note;
import com.model.FileModel;

public class FileDto {
    String path;
    String name;
    String text;

    public FileDto() {
    }

    public FileDto(String name) {
        this.name = name;
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

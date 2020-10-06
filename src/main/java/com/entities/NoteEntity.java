package com.entities;

import java.util.Date;

public class NoteEntity { //надо ваще али нет???
    private String path;
    private String date;
    private String text;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NoteEntity() {
    }

    public NoteEntity(String path, String date, String text) {
        this.path = path;
        this.date = date;
        this.text = text;
    }

    @Override
    public String toString() {
        return  date + " : " + text;
    }
}


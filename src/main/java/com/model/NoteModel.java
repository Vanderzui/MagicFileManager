package com.model;

import com.entities.Note;

public class NoteModel {
    Note note;

    public NoteModel(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}

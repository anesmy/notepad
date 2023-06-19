package com.nesmy.notepad.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noteID;

    private String title;
    private String content;

    public Note() {  }

    public Note(String title, String content) {
        this.setTitle(title);
        this.setContent(content);
    }

    public Note(int id, String title, String content) {
        this.setNoteID(id);
        this.setTitle(title);
        this.setContent(content);
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + noteID +
                ", title='" + title + "'" +
        ", content='" + content + "'" +
        '}';
    }
}

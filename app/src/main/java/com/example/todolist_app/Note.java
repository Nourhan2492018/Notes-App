package com.example.todolist_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String noteTitle;
    private String noteDescription;
    private int notPriority;

    public Note( String noteTitle, String noteDescription, int notPriority) {

        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.notPriority = notPriority;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDiscription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getNotPriority() {
        return notPriority;
    }

    public void setNotPriority(int notPriority) {
        this.notPriority = notPriority;
    }
}

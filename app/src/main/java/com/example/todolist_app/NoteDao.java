package com.example.todolist_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
       void insertNote(Note note);

    @Update
       void updataNote(Note note);

    @Delete
       void deleteNote(Note note);

    @Query("DELETE FROM notes_table")
       void deleteAllNotes();

    @Query("SELECT * FROM notes_table ORDER BY notPriority DESC")
       LiveData<List<Note>>getAllNotes();

}

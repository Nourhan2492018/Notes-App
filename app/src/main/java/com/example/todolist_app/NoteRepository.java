package com.example.todolist_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    public NoteRepository(Application application)
    {
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        noteDao=noteDatabase.noteDao();
        allNotes=noteDao.getAllNotes();
    }
    public void insertNote(Note note)
    {
        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void updataNote(Note note)
    {
      new UpdataNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteNote(Note note)
    {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes()
    {
       new DeleteAllNoteAsyncTask(noteDao).execute();
    }
    public LiveData<List<Note>>getAllNotes()
    {
        return allNotes;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;

        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insertNote(notes[0]);
            return null;
        }
    }


    private static class UpdataNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;

        public UpdataNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.updataNote(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {

        private NoteDao noteDao;

        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>
    {

        private NoteDao noteDao;

        public DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}

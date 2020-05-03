package com.example.todolist_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities ={Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();
    public static synchronized NoteDatabase getInstance(Context context)
    {
      if(instance==null)
      {
          instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,
                  "note_database").fallbackToDestructiveMigration()
                  .addCallback(roomcallback)
                  .build();

      }
      return  instance;

    }
    private static RoomDatabase.Callback roomcallback=new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertNote(new Note("Title1","Description1",1));
            noteDao.insertNote(new Note("Title2","Description2",2));
            noteDao.insertNote(new Note("Title3","Description3",3));

            return null;
        }
    }
}

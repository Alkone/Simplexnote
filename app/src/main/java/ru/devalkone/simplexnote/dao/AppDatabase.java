package ru.devalkone.simplexnote.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.devalkone.simplexnote.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}

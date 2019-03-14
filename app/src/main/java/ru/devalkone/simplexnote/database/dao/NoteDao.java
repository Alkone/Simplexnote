package ru.devalkone.simplexnote.database.dao;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import ru.devalkone.simplexnote.database.entity.Note;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getNoteList();

    @Query("SELECT * FROM note WHERE id = :id")
    Note getNoteById(long id);

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void removeNote(Note note);
}

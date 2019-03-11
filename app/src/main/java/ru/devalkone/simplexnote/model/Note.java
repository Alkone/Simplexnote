package ru.devalkone.simplexnote.model;


import java.io.Serializable;
import java.util.Calendar;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String text;

    private String createTime;

    public Note() {
    }

    public Note(String text) {
        this.text = text;
        this.createTime = Calendar.getInstance().getTime().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

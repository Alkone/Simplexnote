package ru.devalkone.simplexnote.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.dao.AppDatabase;

public class MainActivity extends AppCompatActivity {
    public static MainActivity instance;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        //Database initialization
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database")
                .fallbackToDestructiveMigration()   //Автоматическая миграция схемы на новую версию
                .allowMainThreadQueries()           //CRUD в UI треде (очень плохо)
                .build();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }


}

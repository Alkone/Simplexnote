package ru.devalkone.simplexnote.activity;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }


}

package ru.devalkone.simplexnote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.dao.AppDatabase;
import ru.devalkone.simplexnote.fragment.NoteListFragment;
import ru.devalkone.simplexnote.model.Note;

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
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fragment fragment = new NoteListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data != null) {
            addNewNote(data.getStringExtra("data"));
        }
    }

    public void addNewNote(String data){
        database.noteDao().insert(new Note(data));
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

}

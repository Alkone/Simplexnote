package ru.devalkone.simplexnote.mvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.adapters.NoteRecyclerAdapter;
import ru.devalkone.simplexnote.database.AppDatabase;
import ru.devalkone.simplexnote.database.entity.Note;
import ru.devalkone.simplexnote.mvp.edit.EditActivity;
import ru.devalkone.simplexnote.utils.Constants;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private NoteRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onFabWasClicked();
            }
        });

        mRecyclerView = findViewById(R.id.notes_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new NoteRecyclerAdapter(new NoteRecyclerAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                mPresenter.onRecyclerItemClicked(note);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL)); // add separator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPresenter = new MainPresenter(AppDatabase.getDatabaseInstance(getApplicationContext()).noteDao()); //
        mPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.viewIsReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showEditScreen(Note note) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_CLASS_NOTE, note);
        startActivity(intent);
    }

    @Override
    public void setNotes(List<Note> notes) {
        mRecyclerAdapter.clearItems();
        mRecyclerAdapter.setItems(notes);
    }
}

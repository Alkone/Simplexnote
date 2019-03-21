package ru.devalkone.simplexnote.mvp.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.adapters.NoteRecyclerAdapter;
import ru.devalkone.simplexnote.database.AppDatabase;
import ru.devalkone.simplexnote.database.entity.Note;
import ru.devalkone.simplexnote.mvp.edit.EditActivity;
import ru.devalkone.simplexnote.utils.Constants;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.notes_recyclerview)
    RecyclerView mRecyclerView;

    private MainContract.Presenter mPresenter;
    private NoteRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mFab.setOnClickListener(v -> mPresenter.onFabWasClicked());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new NoteRecyclerAdapter(new NoteRecyclerAdapter.OnNoteClickListener() {
            @Override
            public void onItemClick(Note note) {
                mPresenter.onRecyclerItemClicked(note);
            }

            @Override
            public void onLongItemClick(Note note) {
                mPresenter.onRecyclerItemLongClicked(note);
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

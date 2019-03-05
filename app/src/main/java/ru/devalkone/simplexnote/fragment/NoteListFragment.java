package ru.devalkone.simplexnote.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import ru.devalkone.simplexnote.activity.MainActivity;
import ru.devalkone.simplexnote.adapter.NoteListAdapter;
import ru.devalkone.simplexnote.dao.AppDatabase;
import ru.devalkone.simplexnote.dao.NoteDao;

public class NoteListFragment extends ListFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppDatabase db = MainActivity.getInstance().getDatabase();
        NoteDao noteDao = db.noteDao();

        NoteListAdapter adapter = new NoteListAdapter(this.getContext(), noteDao.getAll());
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

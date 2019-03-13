package ru.devalkone.simplexnote.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.activity.MainActivity;
import ru.devalkone.simplexnote.adapter.NoteRecyclerAdapter;
import ru.devalkone.simplexnote.dao.AppDatabase;
import ru.devalkone.simplexnote.dao.NoteDao;

public class NoteRecyclerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppDatabase db = MainActivity.getInstance().getDatabase();
        NoteDao mNoteDao = db.noteDao();

        View rootView = inflater.inflate(R.layout.fragment_notes_recyclerview, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.notes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL)); // add separator

        NoteRecyclerAdapter mAdapter = new NoteRecyclerAdapter();
        mAdapter.setItems(mNoteDao.getAll());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}

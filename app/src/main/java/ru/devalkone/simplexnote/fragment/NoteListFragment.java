package ru.devalkone.simplexnote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import ru.devalkone.simplexnote.activity.ChangeNoteActivity;
import ru.devalkone.simplexnote.activity.MainActivity;
import ru.devalkone.simplexnote.adapter.NoteListAdapter;
import ru.devalkone.simplexnote.dao.AppDatabase;
import ru.devalkone.simplexnote.dao.NoteDao;
import ru.devalkone.simplexnote.model.Note;

public class NoteListFragment extends ListFragment {


    public static final int CTX_REMOVE = 1;
    public static final int CTX_EDIT = 2;

    private List<Note> mNoteList;
    private NoteDao mNoteDao;
    private NoteListAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppDatabase db = MainActivity.getInstance().getDatabase();
        mNoteDao = db.noteDao();
        mNoteList = mNoteDao.getAll();
        mAdapter = new NoteListAdapter(getContext(), mNoteList);
        setListAdapter(mAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(getListView());
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, CTX_EDIT, Menu.NONE, "Редактировать");
        menu.add(Menu.NONE, CTX_REMOVE, Menu.NONE, "Удалить");
        super.onCreateContextMenu(menu, v, menuInfo);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case CTX_REMOVE:
                //Удаляем из бд
                mNoteDao.delete(mNoteList.get(info.position));
                //Обновляем данные и оповещаем адаптер об изменениях
                updateList();
                mAdapter.notifyDataSetChanged();
                break;
            case CTX_EDIT: {
                Intent intent = new Intent(getContext(), ChangeNoteActivity.class);
                intent.putExtra(ChangeNoteActivity.EXTRA_NOTE, mNoteList.get(info.position));
                startActivity(intent);
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    private void updateList() {
        mNoteList.clear();
        mNoteList.addAll(mNoteDao.getAll());
    }
}

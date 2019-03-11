package ru.devalkone.simplexnote.fragment;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import ru.devalkone.simplexnote.activity.MainActivity;
import ru.devalkone.simplexnote.adapter.NoteListAdapter;
import ru.devalkone.simplexnote.dao.AppDatabase;
import ru.devalkone.simplexnote.dao.NoteDao;
import ru.devalkone.simplexnote.model.Note;

public class NoteListFragment extends ListFragment {
    private static final int CM_DELETE_ID = 1;
    private List<Note> noteList;
    private NoteListAdapter adapter;
    private NoteDao noteDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppDatabase db = MainActivity.getInstance().getDatabase();
        noteDao = db.noteDao();
        noteList = noteDao.getAll();
        adapter = new NoteListAdapter(this.getContext(), noteList);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        registerForContextMenu(getListView());//Регистрируем контекстное меню
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == CM_DELETE_ID){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            // удаляем Map из коллекции, используя позицию пункта в списке
            noteDao.delete(noteList.get(acmi.position));
            // уведомляем, что данные изменились
            updatedDataFromDb();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void updatedDataFromDb(){
        noteList.clear();
        noteList.addAll( noteDao.getAll());
        adapter.notifyDataSetChanged();
    }
}

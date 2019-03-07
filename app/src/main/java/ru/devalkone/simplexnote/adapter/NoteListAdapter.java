package ru.devalkone.simplexnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.model.Note;

public class NoteListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Note> objects;


    public NoteListAdapter(Context context, List<Note> objects) {
        this.context = context;
        this.objects = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.fragment_note_list_item, parent, false);
        }

        Note note = (Note) getItem(position);
        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.note_id)).setText(Long.toString(note.getId()));
        ((TextView) view.findViewById(R.id.note_text)).setText(note.getText());
        return view;
    }


}

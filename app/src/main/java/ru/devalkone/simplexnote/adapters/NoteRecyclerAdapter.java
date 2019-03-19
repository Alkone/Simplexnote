package ru.devalkone.simplexnote.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.database.entity.Note;
import ru.devalkone.simplexnote.utils.DateTools;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder> {
    public interface OnNoteClickListener {
        void onItemClick(Note note);

        void onLongItemClick(Note note);
    }

    private OnNoteClickListener mOnNoteClickListener;
    private List<Note> mNoteList = new ArrayList<>();

    //Constructor
    public NoteRecyclerAdapter(OnNoteClickListener mOnNoteClickListener) {
        this.mOnNoteClickListener = mOnNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_recycler_view, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(mNoteList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void setItems(Collection<Note> notes) {
        mNoteList.addAll(notes);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mNoteList.clear();
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView idTextView;
        private TextView dateTextView;
        private TextView textTextView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.note_item_id);
            dateTextView = itemView.findViewById(R.id.note_item_date);
            textTextView = itemView.findViewById(R.id.note_item_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = mNoteList.get(getLayoutPosition());
                    mOnNoteClickListener.onItemClick(note);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Note note = mNoteList.get(getLayoutPosition());
                    mOnNoteClickListener.onLongItemClick(note);
                    return true;
                }
            });
        }

        public void bind(Note note) {
            idTextView.setText(Long.toString(note.getId()) + "#");

            if (note.getCreateTime() != null) {
                dateTextView.setText(DateTools.getFormattedDate(
                        "EEE MMM dd HH:mm:ss ZZZZZ yyyy", "dd MMM yyyy HH:mm:ss", note.getCreateTime())); //Thu Oct 26 07:31:08 +0000 2017 to 26 Oct 2017 07:31:08
            }

            textTextView.setText(note.getText());
        }

        //Изменяет формат даты

    }
}

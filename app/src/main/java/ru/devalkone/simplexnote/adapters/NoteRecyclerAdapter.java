package ru.devalkone.simplexnote.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.database.entity.Note;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder> {
    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    private OnNoteClickListener mOnNoteClickListener;
    private List<Note> mNoteList = new ArrayList<>();

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
        private static final String TWITTER_RESPONSE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"; // Thu Oct 26 07:31:08 +0000 2017
        private static final String CREATE_DATE_FORMAT = "dd MMM yyyy HH:mm:ss"; // 26 Oct 2017 07:31:08

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
                    mOnNoteClickListener.onNoteClick(note);
                }
            });
        }

        public void bind(Note note) {
            idTextView.setText(Long.toString(note.getId()) + "#");

            if (note.getCreateTime() != null) {
                dateTextView.setText(getFormattedDate(note.getCreateTime()));
            }

            textTextView.setText(note.getText());
        }

        //Изменяет формат даты
        private String getFormattedDate(String rawDate) {
            SimpleDateFormat utcFormat = new SimpleDateFormat(TWITTER_RESPONSE_FORMAT, Locale.ROOT);
            SimpleDateFormat displayedFormat = new SimpleDateFormat(CREATE_DATE_FORMAT, Locale.getDefault());
            try {
                Date date = utcFormat.parse(rawDate);
                return displayedFormat.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

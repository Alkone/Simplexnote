package ru.devalkone.simplexnote.mvp.edit;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeNoteActivity extends AppCompatActivity {
//    public static final String EXTRA_NOTE = "NOTE";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_note);
//
//        final EditText noteText = findViewById(R.id.edit_text);
//        final Button addSaveButton = findViewById(R.id.button_add);
//        final Button cancelButton = findViewById(R.id.button_cancel);
//
//        //Cancel button
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        //Если в передаваемом интенте есть ссылка на существующую записку, то редактируем ее, если нет, то создаем новую.
//        if (getIntent().getSerializableExtra(EXTRA_NOTE) != null) {
//            addSaveButton.setText(R.string.button_save);
//            final Note oldNote = (Note) getIntent().getSerializableExtra(EXTRA_NOTE);
//            noteText.setText(oldNote.getText());
//
//            addSaveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    oldNote.setText(noteText.getText().toString());
//                    updateNote(oldNote);
//                    finish();
//                }
//            });
//        } else {
//            addSaveButton.setText(R.string.button_add);
//            addSaveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Note newNote = new Note(noteText.getText().toString());
//                    createNote(newNote);
//                    finish();
//                }
//            });
//        }
//    }
//
//    private void createNote(final Note note) {
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                MainActivity.getInstance().getDatabase().noteDao().insert(note);
//            }
//        });
//    }
//
//    private void updateNote(final Note note) {
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                MainActivity.getInstance().getDatabase().noteDao().update(note);
//            }
//        });
//    }

}

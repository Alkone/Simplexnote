package ru.devalkone.simplexnote.activity;

import androidx.appcompat.app.AppCompatActivity;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.model.Note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Button addButton = findViewById(R.id.button_add);
        Button cancelButton = findViewById(R.id.button_cancel);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText noteText = findViewById(R.id.edit_text);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                Note note = new Note(noteText.getText().toString());
                intent.putExtra("NEW_NOTE", note);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}

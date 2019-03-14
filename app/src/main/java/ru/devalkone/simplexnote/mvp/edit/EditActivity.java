package ru.devalkone.simplexnote.mvp.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.database.AppDatabase;
import ru.devalkone.simplexnote.database.entity.Note;
import ru.devalkone.simplexnote.utils.Constants;

public class EditActivity extends AppCompatActivity implements EditContract.View {

    private EditContract.Presenter mPresenter;
    private EditText mEditText;
    private Button mSaveButton;
    private Button mCancelButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    private void init() {
        final Note note = (Note) getIntent().getSerializableExtra(Constants.NOTE_TYPE); //???

        mEditText = findViewById(R.id.edit_text_main);
        mSaveButton = findViewById(R.id.button_save);
        mCancelButton = findViewById(R.id.button_cancel);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSaveClicked(note);
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPresenter = new EditPresenter(AppDatabase.getDatabaseInstance(getApplicationContext()).noteDao());
        mPresenter.attachView(this);
        mPresenter.viewIsReady();

    }

    @Override
    public void showText() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

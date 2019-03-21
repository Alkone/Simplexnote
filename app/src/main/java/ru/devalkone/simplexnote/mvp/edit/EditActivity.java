package ru.devalkone.simplexnote.mvp.edit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import ru.devalkone.simplexnote.R;
import ru.devalkone.simplexnote.database.AppDatabase;
import ru.devalkone.simplexnote.database.entity.Note;
import ru.devalkone.simplexnote.utils.Constants;

public class EditActivity extends AppCompatActivity implements EditContract.View {

    private EditContract.Presenter mPresenter;
    @BindView(R.id.edit_text_main)
    EditText mEditText;
    @BindView(R.id.button_save)
    Button mSaveButton;
    @BindView(R.id.button_cancel)
    Button mCancelButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    private void init() {
        final Note note = (Note) getIntent().getSerializableExtra(Constants.INTENT_EXTRA_CLASS_NOTE); //???
        if (note.getId() != 0) {
            mEditText.setText(note.getText());
        }
        mSaveButton.setOnClickListener(v -> {
            mPresenter.onSaveClicked(note, mEditText);
            finish();
        });
        mCancelButton.setOnClickListener(v -> finish());

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

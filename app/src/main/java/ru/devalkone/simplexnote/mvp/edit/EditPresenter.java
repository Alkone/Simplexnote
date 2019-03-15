package ru.devalkone.simplexnote.mvp.edit;

import android.widget.EditText;

import java.util.Calendar;

import ru.devalkone.simplexnote.database.dao.NoteDao;
import ru.devalkone.simplexnote.database.entity.Note;

public class EditPresenter implements EditContract.Presenter {

    private EditContract.View mView;
    private final NoteDao mRepository;

    EditPresenter(NoteDao mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void attachView(EditContract.View v) {

    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveClicked(Note note, EditText editText) {
        note.setText(editText.getText().toString());
        if (note.getId() == 0) {
            note.setCreateTime(Calendar.getInstance().getTime().toString());
            mRepository.insertNote(note);
        } else {
            mRepository.updateNote(note);
        }

    }

    @Override
    public void onCancelClicked() {

    }

}

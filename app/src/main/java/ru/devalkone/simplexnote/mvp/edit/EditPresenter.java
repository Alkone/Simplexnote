package ru.devalkone.simplexnote.mvp.edit;

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
    public void onSaveClicked(Note note) {

    }

    @Override
    public void onCancelClicked() {

    }

}

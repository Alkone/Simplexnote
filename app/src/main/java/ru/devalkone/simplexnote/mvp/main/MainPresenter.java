package ru.devalkone.simplexnote.mvp.main;

import ru.devalkone.simplexnote.database.dao.NoteDao;
import ru.devalkone.simplexnote.database.entity.Note;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private final NoteDao mRepository;

    MainPresenter(NoteDao mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void attachView(MainContract.View v) {
        mView = v;
    }

    @Override
    public void viewIsReady() {
        mView.setNotes(mRepository.getNoteList());
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onFabWasClicked() {
        mView.showEditScreen(new Note());
    }

}

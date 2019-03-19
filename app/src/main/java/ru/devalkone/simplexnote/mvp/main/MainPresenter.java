package ru.devalkone.simplexnote.mvp.main;

import java.util.List;

import ru.devalkone.simplexnote.database.dao.NoteDao;
import ru.devalkone.simplexnote.database.entity.Note;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private final NoteDao mRepository;
    private List<Note> mNoteList;

    MainPresenter(NoteDao mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void attachView(MainContract.View v) {
        mView = v;
    }

    @Override
    public void viewIsReady() {
        updateData();
    }

    @Override
    public void updateData() {
        mRepository.getNoteList();
        mNoteList = mRepository.getNoteList();
        mView.setNotes(mNoteList);
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

    @Override
    public void onRecyclerItemClicked(Note note) {
        mView.showEditScreen(note);
    }

    @Override
    public void onRecyclerItemLongClicked(Note note) {
        mRepository.removeNote(note);
        updateData();
    }

}

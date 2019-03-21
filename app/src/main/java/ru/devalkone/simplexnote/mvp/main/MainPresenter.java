package ru.devalkone.simplexnote.mvp.main;

import android.annotation.SuppressLint;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.devalkone.simplexnote.database.dao.NoteDao;
import ru.devalkone.simplexnote.database.entity.Note;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private final NoteDao mRepository;
    private Disposable mDaoSubscribe;

    MainPresenter(NoteDao mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void attachView(MainContract.View v) {
        mView = v;
    }


    @SuppressLint("CheckResult")
    @Override
    public void viewIsReady() {
        mDaoSubscribe = mRepository.getNoteList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> { //добавить catch
                    mView.setNotes(notes);
                });
    }

    @Override
    public void updateData() {

    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onDestroy() {
        mDaoSubscribe.dispose();
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
    }

}

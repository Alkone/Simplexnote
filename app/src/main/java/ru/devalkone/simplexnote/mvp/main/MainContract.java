package ru.devalkone.simplexnote.mvp.main;

import java.util.List;

import ru.devalkone.simplexnote.database.entity.Note;

public interface MainContract {

    interface View {

        void showEditScreen(Note note);

        void setNotes(List<Note> notes);
    }

    interface Presenter {

        void attachView(View v);

        void viewIsReady();

        void detachView();

        void onDestroy();

        void onFabWasClicked();

        void onRecyclerItemClicked(Note note);
    }
}

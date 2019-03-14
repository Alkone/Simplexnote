package ru.devalkone.simplexnote.mvp;

import java.util.List;

import ru.devalkone.simplexnote.model.Note;

public interface MainContract {
    interface View {
        void showText();
    }

    interface Presenter {
        void attachView(View v);

        void viewIsReady();

        void detachView();

        void destroy();

        void onAddButtonWasClicked();
    }

    interface Repository {
        List<Note> getNoteList();

        boolean addNote(Note note);

        boolean updateNote(Note note);

        boolean removeNote(Long id);
    }
}

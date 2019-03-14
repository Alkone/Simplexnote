package ru.devalkone.simplexnote.mvp.edit;

import ru.devalkone.simplexnote.database.entity.Note;

public interface EditContract {

    interface View {

        void showText();

    }

    interface Presenter {

        void attachView(View v);

        void viewIsReady();

        void detachView();

        void onDestroy();

        void onSaveClicked(Note note);

        void onCancelClicked();

    }
}

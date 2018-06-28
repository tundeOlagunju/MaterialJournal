package com.ajdi.yassin.instajournal.data.source.local;

import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.data.source.NotesDataSource;

import java.util.List;

import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation of a data source as a db.
 */
public class NotesLocalDataSource implements NotesDataSource {

    private static volatile NotesLocalDataSource INSTANCE;

    private NotesDao mNotesDao;

    // Prevent direct instantiation.
    private NotesLocalDataSource(@NonNull NotesDao notesDao) {
        mNotesDao = notesDao;
    }

    public static NotesLocalDataSource getInstance(@NonNull NotesDao notesDao) {
        if (INSTANCE == null) {
            synchronized (NotesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotesLocalDataSource(notesDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Note> notes = mNotesDao.getNotes();
                if (notes.isEmpty()) {
                    // This will be called if the table is new or just empty.
                    callback.onDataNotAvailable();
                } else {
                    callback.onNotesLoaded(notes);
                }
            }
        }).start();
    }

    @Override
    public void getNote(@NonNull final String noteId, @NonNull final GetNoteCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Note note = mNotesDao.getNoteById(noteId);
                if (note != null) {
                    callback.onNoteLoaded(note);
                } else {
                    callback.onDataNotAvailable();
                }
            }
        }).start();
    }

    @Override
    public void saveNote(@NonNull final Note note) {
        checkNotNull(note);
        Timber.d("saveNote");

        new Thread(new Runnable() {
            @Override
            public void run() {
                mNotesDao.insertNote(note);
            }
        }).start();
    }
}

package com.ajdi.yassin.instajournal.ui.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.databinding.ItemNoteBinding;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> mNotes;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNoteBinding itemBinding =
                ItemNoteBinding.inflate(layoutInflater, parent, false);
        return new NoteViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note note = mNotes.get(position);
        NoteViewHolder noteViewHolder = (NoteViewHolder) holder;
        noteViewHolder.bind(note);
    }

    @Override
    public int getItemCount() {
        return mNotes != null ? mNotes.size() : 0;
    }

    public void replaceData(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }


    /**
     * The {@link NoteViewHolder} class.
     * Provides a binding reference to each view in the note cardView.
     */
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteBinding mBinding;

        public NoteViewHolder(final ItemNoteBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bind(Note note) {
            //mBinding.setItem();
            //binding.executePendingBindings();
        }
    }
}

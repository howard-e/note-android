package com.howard.note.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.howard.note.R;
import com.howard.note.models.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Howard.
 */

public class NotePictureAdapter extends RecyclerView.Adapter<NotePictureAdapter.NoteViewHolder> {

    private Context context;
    private ArrayList<Note> noteArrayList;

    private ClickListener clickListener;

    public NotePictureAdapter(Context context, ArrayList<Note> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;

        clickListener = new ClickListener();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_picture, parent, false));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note note = noteArrayList.get(position);
        holder.bind(note);

        holder.mNoteEditButton.setOnClickListener(clickListener);
        holder.mNoteArchiveButton.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    // Need this to differentiate between types of notes, ie: p, t, v, l
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.note_text)
        TextView mNoteText;
        @BindView(R.id.note_last_edit)
        TextView mNoteLastEdit;

        @BindView(R.id.note_picture)
        ImageView mNotePicture;
        @BindView(R.id.note_edit_button)
        ImageView mNoteEditButton;
        @BindView(R.id.note_archive_button)
        ImageView mNoteArchiveButton;

        NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Note note) {
            mNoteText.setText(note.getText());
            mNotePicture.setImageResource(note.getPicture());
            mNoteLastEdit.setText(note.getLastEdit());
        }
    }

    private class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.note_edit_button:
                    Toast.makeText(context, "may i haz edit funkchorarity pweez ser!?", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.note_archive_button:
                    Toast.makeText(context, "may i haz archive funkchorarity pweez ser!?", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    /**
     * Filter Logic
     **/
    public void animateTo(ArrayList<Note> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<Note> newModels) {
        for (int i = noteArrayList.size() - 1; i >= 0; i--) {
            final Note model = noteArrayList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<Note> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Note model = newModels.get(i);
            if (!noteArrayList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<Note> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Note model = newModels.get(toPosition);
            final int fromPosition = noteArrayList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    private Note removeItem(int position) {
        final Note model = noteArrayList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    private void addItem(int position, Note model) {
        noteArrayList.add(position, model);
        notifyItemInserted(position);
    }

    private void moveItem(int fromPosition, int toPosition) {
        final Note model = noteArrayList.remove(fromPosition);
        noteArrayList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}

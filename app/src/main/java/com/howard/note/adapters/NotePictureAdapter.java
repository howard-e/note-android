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
        holder.mNoteText.setText(note.getText());
        holder.mNotePicture.setImageResource(note.getPicture());
        holder.mNoteLastEdit.setText(note.getLastEdit());

        holder.mNoteEditButton.setOnClickListener(clickListener);
        holder.mNoteArchiveButton.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
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

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
}

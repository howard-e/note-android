package com.henterprise.note.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.henterprise.note.R;
import com.henterprise.note.models.Note;
import com.henterprise.note.utils.AppConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Howard.
 */

public class
NoteAdapter extends RecyclerView.Adapter {

    private final static int LAYOUT_NOTE_TEXT = R.layout.item_note_text;
    private final static int LAYOUT_NOTE_PICTURE = R.layout.item_note_picture;
    private final static int LAYOUT_NOTE_LINK = R.layout.item_note_link;
    private final static int LAYOUT_NOTE_VIDEO = R.layout.item_note_video;

    private Context context;
    private ArrayList<Note> noteArrayList;
    private ClickListener clickListener;

    private String noteType;

    public NoteAdapter(Context context, ArrayList<Note> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;

        clickListener = new ClickListener();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (noteType) {
            case AppConstants.NOTE_TEXT:
                return new TextNoteViewHolder(inflater.inflate(LAYOUT_NOTE_TEXT, parent, false));
            case AppConstants.NOTE_PICTURE:
                return new PictureNoteViewHolder(inflater.inflate(LAYOUT_NOTE_PICTURE, parent, false));
            case AppConstants.NOTE_LINK:
                return new LinkNoteViewHolder(inflater.inflate(LAYOUT_NOTE_LINK, parent, false));
            case AppConstants.NOTE_VIDEO:
                return new VideoNoteViewHolder(inflater.inflate(LAYOUT_NOTE_VIDEO, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (noteType == AppConstants.NOTE_TEXT) {
            TextNoteViewHolder mHolder = (TextNoteViewHolder) holder;
            final Note note = noteArrayList.get(position);
            mHolder.bind(note);

            mHolder.mNoteEditButton.setOnClickListener(clickListener);
            mHolder.mNoteArchiveButton.setOnClickListener(clickListener);
        } else if (noteType == AppConstants.NOTE_PICTURE) {
            PictureNoteViewHolder mHolder = (PictureNoteViewHolder) holder;
            final Note note = noteArrayList.get(position);
            mHolder.bind(note);

            mHolder.mNoteEditButton.setOnClickListener(clickListener);
            mHolder.mNoteArchiveButton.setOnClickListener(clickListener);
        } else if (noteType == AppConstants.NOTE_LINK) {
            LinkNoteViewHolder mHolder = (LinkNoteViewHolder) holder;
            final Note note = noteArrayList.get(position);
            mHolder.bind(note);
        }
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    // Need this to differentiate between types of notes, ie: p, t, v, l
    @Override
    public int getItemViewType(int position) {
        noteType = noteArrayList.get(position).getType();
        return position;
    }

    class TextNoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.note_text)
        TextView mNoteText;
        @BindView(R.id.note_last_edit)
        TextView mNoteLastEdit;
        @BindView(R.id.note_edit_button)
        ImageView mNoteEditButton;
        @BindView(R.id.note_archive_button)
        ImageView mNoteArchiveButton;

        TextNoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Note note) {
            mNoteText.setText(note.getTitle());
            mNoteLastEdit.setText(note.getLastEdit());
        }
    }

    class PictureNoteViewHolder extends RecyclerView.ViewHolder {

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

        PictureNoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Note note) {
            mNoteText.setText(note.getTitle());
            mNotePicture.setImageResource(note.getPicture());
            mNoteLastEdit.setText(note.getLastEdit());
        }
    }

    class LinkNoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.info_wrap)
        LinearLayout infoWrap;
        @BindView(R.id.post_content)
        TextView contentTextView;
        @BindView(R.id.image_post)
        ImageView imageView;
        @BindView(R.id.title)
        TextView titleTextView;

        @BindView(R.id.url)
        TextView urlTextView;
        @BindView(R.id.description)
        TextView descriptionTextView;

        LinkNoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Note note) {
            contentTextView.setText(note.getTitle());
        }
    }

    class VideoNoteViewHolder extends RecyclerView.ViewHolder {

        VideoNoteViewHolder(View itemView) {
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

    /**
     * Filter Logic
     * [START]
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
    /**
     * Filter Logic
     * [END]
     */
}

package com.henterprise.note.utils;

import com.henterprise.note.R;
import com.henterprise.note.models.Note;

import java.util.ArrayList;

/**
 * @author Howard.
 */

public class DummyNoteContent {

    public static ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note("129", "Teleporter of an ordinary moon, influence the friendship!", "Paralysis, powerdrain, and sensor.", "Jan 35, 2095", R.drawable.sample_note_image_2, "p"));
        notes.add(new Note("aj2452", "Die wihtout anomaly, and we won’t observe a machine.", "The lunar parasite proudly transfers the ship.", "Jul 35, 2095", "t"));
        notes.add(new Note("eit895f", "Yuck, lively madness!", "Ahoy, command me skiff, ye heavy-hearted shipmate!", "Jul 35, 2095", "t"));
        notes.add(new Note("3292", "Why does the collective warp?", "The dead girl revolutionary feeds the phenomenan.", "Jul 35, 2095", R.drawable.sample_note_image, "p"));

        return notes;
    }
}

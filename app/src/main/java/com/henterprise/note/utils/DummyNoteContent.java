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

        notes.add(new Note("1", "Ubi est secundus cotta?", "Random description", "Jul 35, 2095", R.drawable.sample_note_image, "p"));
        notes.add(new Note("1", "Pes, omnia, et fluctus?", "Random description", "Gaza 35, 2095", R.drawable.sample_note_image_2, "p"));
        notes.add(new Note("1", "Never raid a whale?", "Random description", "Jul 35, 2095", "t"));
        notes.add(new Note("1", "Danista, nomen, et domina?", "Random description", "Aug 35, 2095", R.drawable.sample_note_image_2, "p"));
        notes.add(new Note("1", "Zirbus germanus bromium est?", "Random description", "Fri 35, 2095", R.drawable.sample_note_image, "p"));
        notes.add(new Note("1", "Ho-ho-ho! grace of horror?", "Random description", "Jul 35, 2095", "t"));
        notes.add(new Note("1", "Est salvus mensa, cesaris?", "Random description", "Jan 35, 2095", R.drawable.sample_note_image_2, "p"));
        notes.add(new Note("1", "Lamia placidus accola est?", "Random description", "Jul 35, 2095", R.drawable.sample_note_image, "p"));
        notes.add(new Note("1", "Freebooters travel with love?", "Random description", "Jul 35, 2095", "t"));
        notes.add(new Note("1", "Daggers wave with malaria?", "Random description", "Jul 35, 2095", "t"));

        return notes;
    }
}

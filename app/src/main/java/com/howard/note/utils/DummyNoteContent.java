package com.howard.note.utils;

import com.howard.note.R;
import com.howard.note.models.Note;

import java.util.ArrayList;

/**
 * @author Howard.
 */

public class DummyNoteContent {

    public static ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(new Note("1", "Ubi est secundus cotta?", "Jul 35, 2095", R.drawable.sample_note_image, 'p'));
        notes.add(new Note("1", "Pes, omnia, et fluctus?", "Gaza 35, 2095", R.drawable.sample_note_image_2, 'p'));
        notes.add(new Note("1", "Danista, nomen, et domina?", "Aug 35, 2095", R.drawable.sample_note_image_2, 'p'));
        notes.add(new Note("1", "Zirbus germanus bromium est?", "Fri 35, 2095", R.drawable.sample_note_image, 'p'));
        notes.add(new Note("1", "Est salvus mensa, cesaris?", "Jan 35, 2095", R.drawable.sample_note_image_2, 'p'));
        notes.add(new Note("1", "Lamia placidus accola est?", "Jul 35, 2095", R.drawable.sample_note_image, 'p'));
        notes.add(new Note("1", "Freebooters travel with love?", "Jul 35, 2095", 't'));
        notes.add(new Note("1", "Ho-ho-ho! grace of horror?", "Jul 35, 2095", 't'));
        notes.add(new Note("1", "Never raid a whale?", "Jul 35, 2095", 't'));
        notes.add(new Note("1", "Daggers wave with malaria?", "Jul 35, 2095", 't'));

        return notes;
    }
}

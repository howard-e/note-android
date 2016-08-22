package com.howard.note.models;

/**
 * @author Howard.
 */

public class Note {

    private String id, text, lastEdit;
    private int picture;

    // 't' = text | 'p' = picture | 'l' = link | 'v' = video
    private char type;

    // Accounting for note of picture type
    public Note(String id, String text, String lastEdit, int picture, char type) {
        this.id = id;
        this.text = text;
        this.lastEdit = lastEdit;
        this.picture = picture;
        this.type = type;
    }

    // Accounting for note of text type


    public Note(String id, String text, String lastEdit, char type) {
        this.id = id;
        this.text = text;
        this.lastEdit = lastEdit;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public int getPicture() {
        return picture;
    }

    public char getType() {
        return type;
    }
}

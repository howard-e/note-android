package com.henterprise.note.models;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author Howard.
 */

public class Note extends RealmObject {

    @Index
    @PrimaryKey
    @Required
    private String id;

    @Index
    private String title;
    private String description;

    private String lastEdit;
    private int picture;

    // 't' = title | 'p' = picture | 'l' = link | 'v' = video
    @Required
    private String type;

    public Note() {
    }

    // Accounting for note of picture type
    public Note(String id, String title, String description, String lastEdit, int picture, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lastEdit = lastEdit;
        this.picture = picture;
        this.type = type;
    }

    // Accounting for note of title type
    public Note(String id, String title, String description, String lastEdit, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lastEdit = lastEdit;
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public int getPicture() {
        return picture;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "id:" + id
                + "|title:" + title
                + "|description:" + description
                + "|type:" + type;
    }
}

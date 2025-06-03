package com.trbinc.billboard.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Document(collection="notes")
public class Note {
    @Id
    private String id;
    private String quote;
    private String user;
    private Date creation;
    private Date updation;

    public Note() {
    }

    public Note(String quote, String user, Date creation, Date updation) {
        this.quote = quote;
        this.user = user;
        this.creation = creation;
        this.updation = updation;
    }
}

package com.example.bibafrica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Contact {

    public String namess;
    @Id
    public String emaill;
    public String comment;

    public Contact() {
    }

    public Contact(String namess, String emaill, String comment) {
        this.namess = namess;
        this.emaill = emaill;
        this.comment = comment;
    }

    public String getNamess() {
        return namess;
    }

    public void setNamess(String namess) {
        this.namess = namess;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

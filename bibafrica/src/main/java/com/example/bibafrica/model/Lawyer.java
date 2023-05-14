package com.example.bibafrica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
public class Lawyer {
    @Id
    private Long id;
    public String names;
    public String tel;
    public String emails;
    public String lawyer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;
    public String cases;

    public Lawyer() {
    }

    public Lawyer(Long id, String names, String tel, String emails, String lawyer, Date date, String cases) {
        this.id = id;
        this.names = names;
        this.tel = tel;
        this.emails = emails;
        this.lawyer = lawyer;
        this.date = date;
        this.cases = cases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }
}

package com.example.bibafrica.services;

import com.example.bibafrica.model.Contact;

import java.util.List;

public interface ContactService {
    public Contact saveVontact(Contact contact);
        public List<Contact> GetAllContact();
        public void deleteContact(String email);
}

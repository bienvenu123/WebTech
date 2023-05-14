package com.example.bibafrica.services.implementation;

import com.example.bibafrica.model.Contact;
import com.example.bibafrica.repository.ContactRepository;
import com.example.bibafrica.services.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact saveVontact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> GetAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteContact(String email) {
        contactRepository.deleteById(email);
    }
}

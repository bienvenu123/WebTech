package com.example.bibafrica.repository;

import com.example.bibafrica.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {
}

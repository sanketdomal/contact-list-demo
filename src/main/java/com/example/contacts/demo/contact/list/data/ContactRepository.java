package com.example.contacts.demo.contact.list.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contacts.demo.contact.list.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	

}

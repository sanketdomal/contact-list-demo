package com.example.contacts.demo.contact.list.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.contacts.demo.contact.list.model.Contact;

public interface IContactService {
	
	void save() throws FileNotFoundException;

	List<Contact> fetchContacts() throws FileNotFoundException;
}

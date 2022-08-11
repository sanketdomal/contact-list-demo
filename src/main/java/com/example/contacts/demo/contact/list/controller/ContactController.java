package com.example.contacts.demo.contact.list.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contacts.demo.contact.list.data.ContactRepository;
import com.example.contacts.demo.contact.list.message.ResponseMessage;
import com.example.contacts.demo.contact.list.model.Contact;
import com.example.contacts.demo.contact.list.service.IContactService;

@RestController
public class ContactController {

	@Autowired
	private IContactService contactService;

	@Autowired
	private ContactRepository contactData;

	@Autowired
	Environment env;

	/**
	 * This api is Pagination supported to fetch/get contacts based on page size and
	 * also support sort testing api url - /listPageable?page=0&size=15&sort=name
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping("/listPageable")
	Page<Contact> contactPageable(Pageable pageable) {
		return contactData.findAll(pageable);
	}

	/**
	 * This api poulates data from csv and save into DB
	 * 
	 * @return message sucess or fail
	 */
	@GetMapping("/populateContact")
	public ResponseEntity populateContactsfromCSV() {
		try {
			contactService.save();
			String message = "Contact Populated sucessfully!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (FileNotFoundException e) {
			String message = "Failed to poulate Contacts !";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	/**
	 * Plain get contact api with no pagination support
	 * 
	 * @return list contact
	 */
	@GetMapping("/fetch")
	public ResponseEntity getContactsFromCSV() {
		List<Contact> contacts = null;
		try {
			contacts = contactService.fetchContacts();
			return ResponseEntity.status(HttpStatus.OK).body(contacts);
		} catch (FileNotFoundException e) {
			String message = "Specified File not found: " + env.getProperty("csv_file_path") + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
}

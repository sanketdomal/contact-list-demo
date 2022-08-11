package com.example.contacts.demo.contact.list.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.contacts.demo.contact.list.data.ContactRepository;
import com.example.contacts.demo.contact.list.helper.CSVHelper;
import com.example.contacts.demo.contact.list.model.Contact;
import com.example.contacts.demo.contact.list.service.IContactService;

@Service
public class ContactService implements IContactService {
	
	@Autowired
	ContactRepository repository;

	@Autowired
	private Environment env;
	
	
	
	public List<Contact> fetchContacts() throws FileNotFoundException {
		
		List<Contact> list = new ArrayList<Contact>();		
		File file = ResourceUtils.getFile(env.getProperty("csv_file_path"));
		InputStream targetStream = new FileInputStream(file);
		list = CSVHelper.csvToContacts(targetStream);
		return list;

	}
	
	//test method
public void save() throws FileNotFoundException {
		
		try {
			List<Contact> listContact = fetchContacts();
		      repository.saveAll(listContact);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }

	}

	
}

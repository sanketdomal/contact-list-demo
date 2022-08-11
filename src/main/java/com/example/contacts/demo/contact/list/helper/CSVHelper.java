package com.example.contacts.demo.contact.list.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.web.multipart.MultipartFile;

import com.example.contacts.demo.contact.list.model.Contact;

public class CSVHelper {

	public static String TYPE = "text/csv";
	static String[] HEADERs = { "name", "url" };

	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<Contact> csvToContacts(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new BOMInputStream(is, true)));

				CSVParser parser = new CSVParser(fileReader, CSVFormat.EXCEL.withHeader());) {
			List<Contact> contacts = new ArrayList<Contact>();
			for (final CSVRecord record : parser) {
				final String name = record.get(0);
				final String url = record.get(1);
				Contact contact = new Contact(name, url);
				contacts.add(contact);

			}

			return contacts;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}

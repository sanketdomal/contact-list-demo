package com.example.contacts.demo.contact.list.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	private String name;
	private String photo_url;

	public Contact() {
	}

	public Contact(String name, String photo_url) {
		super();
		this.name = name;
		this.photo_url = photo_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", photo_url=" + photo_url + "]";
	}
	
	

}

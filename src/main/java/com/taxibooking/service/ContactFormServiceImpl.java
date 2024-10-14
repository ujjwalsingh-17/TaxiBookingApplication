package com.taxibooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxibooking.dao.ContactFormCrud;
import com.taxibooking.model.ContactForm;

@Service
public class ContactFormServiceImpl implements ContactFormService {
	
	private ContactFormCrud contactFormCrud;

	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		// TODO Auto-generated method stub
		return contactFormCrud.save(contactForm);
		
		
	}
	
	@Autowired
	public void setContactFormCrud(ContactFormCrud contactFormCrud) {
		this.contactFormCrud = contactFormCrud;
	}

	@Override
	public List<ContactForm> readContactFormService() {
		return contactFormCrud.findAll();
	}

	@Override
	public void deleteContactService(int id) {
		// TODO Auto-generated method stub
		contactFormCrud.deleteById(id);
		
	}

}

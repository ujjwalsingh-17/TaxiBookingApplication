package com.taxibooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxibooking.dao.BookingFormCrud;
import com.taxibooking.model.BookingForm;

@Service
public class BookingFormServiceImpl implements BookingFormService {
	
	private BookingFormCrud bookingFormCrud;

	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}

	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		
		return bookingFormCrud.save(bookingForm);
	}

	@Override
	public List<BookingForm> readAllBookingFormService() {
		return bookingFormCrud.findAll();
	}

	@Override
	public void deleteBookingFormService(int id) {
		
		bookingFormCrud.deleteById(id);
	}

}

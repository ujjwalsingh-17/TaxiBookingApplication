package com.taxibooking.service;

import java.util.List;

import com.taxibooking.model.BookingForm;

public interface BookingFormService {
	
	public BookingForm saveBookingFormService(BookingForm bookingForm);
	public List<BookingForm> readAllBookingFormService();
	public void deleteBookingFormService(int id);

}

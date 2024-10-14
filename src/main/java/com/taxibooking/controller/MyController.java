package com.taxibooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taxibooking.model.BookingForm;
import com.taxibooking.model.ContactForm;
import com.taxibooking.model.ServiceForm;
import com.taxibooking.service.BookingFormService;
import com.taxibooking.service.ContactFormService;
import com.taxibooking.service.ContactFormServiceImpl;
import com.taxibooking.service.ServiceFormService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class MyController {
	
	
	private ContactFormService contactFormService;
	private BookingFormService bookingFormService;
	private ServiceFormService serviceFormService;
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping(path= {"/","home","welcome","index"})
	public String welcomeView(HttpServletRequest req, Model m) {
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage",requestURI);
		m.addAttribute("bookingForm",new BookingForm());
		System.out.println("Request uri "+ requestURI);
		return "index"; //by default adds suffix .html
	}
	
	@GetMapping(path= {"about"})
	public String aboutPageView(HttpServletRequest req, Model m){
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage",requestURI);
		return "about";
	}
	
	@GetMapping(path= {"services"})
	public String servicesPageView(HttpServletRequest req, Model m){
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage",requestURI);
		List<ServiceForm> services = serviceFormService.getServices();
		m.addAttribute("services", services);
		return "services";
	}
	
	@GetMapping(path= {"cars"})
	public String carsPageView(HttpServletRequest req, Model m){
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage",requestURI);
		return "cars";
	}
	
	@GetMapping(path= {"contacts"})
	public String contactsPageView(HttpServletRequest req, Model m){
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage",requestURI);
		m.addAttribute("contactForm",new ContactForm());
		return "contacts";
	}
	
	@GetMapping("/login")
	public String adminLoginView(HttpServletRequest request,Model m) {
		ServletContext servletContext = request.getServletContext();
		Object attribute = servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {
			m.addAttribute("message",attribute);
			servletContext.removeAttribute("logout");
		}
		return "adminlogin";
	}
	
	@PostMapping(path= {"contactForm"})
	public String contactForm(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult ,Model m, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingResult", bindingResult);
			return "contacts";
		}
		
		ContactForm saveContactFormService = contactFormService.saveContactFormService(contactForm);
		if(saveContactFormService!=null) {
			redirectAttributes.addFlashAttribute("message","Message sent successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("message","Something went wrong");
		}
		System.out.println(contactForm);
		return "redirect:/contacts";
	}		
	
	@PostMapping(path= {"bookingForm"})
	public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm, BindingResult bindingResult, Model m, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingResult", bindingResult);
			return "index";
		}
		else if(bookingForm.getAdult()+bookingForm.getChildren()>4) {
			System.out.println("inside else if");
			m.addAttribute("message", "The total number of adult and children cannot exceed 4.");
			return "index";
		}
//		System.out.println(bookingForm);
		BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingForm);
		
		if(saveBookingFormService != null) {
			redirectAttributes.addFlashAttribute("message", "Booking Done Successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Something went wrong");
		}
		
		return "redirect:/index";
	}
}
package com.taxibooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taxibooking.model.BookingForm;
import com.taxibooking.model.ContactForm;
import com.taxibooking.model.ServiceForm;
import com.taxibooking.service.AdminCredentialsService;
import com.taxibooking.service.BookingFormService;
import com.taxibooking.service.ContactFormService;
import com.taxibooking.service.ServiceFormService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {

	private ContactFormService contactFormService;
	private AdminCredentialsService adminCredentialsService;
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
	public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
		this.adminCredentialsService = adminCredentialsService;
	}

	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}

	@GetMapping("readAllContacts")
	public String readAllContacts(HttpServletRequest req, Model m) {
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage", requestURI);
		List<ContactForm> allContacts = contactFormService.readContactFormService();
		m.addAttribute("contacts", allContacts);
		return "admin/readallcontacts";
	}

	@GetMapping("readAllBookings")
	public String readAllBookings(Model m, HttpServletRequest req) {
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage", requestURI);
		List<BookingForm> allBookings = bookingFormService.readAllBookingFormService();
		m.addAttribute("bookings", allBookings);
		return "admin/readallbookings";
	}

	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {
		contactFormService.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message", "Contact Deleted Successfully");
		return "redirect:/admin/readAllContacts";
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id, RedirectAttributes redirectAttributes) {
		
		bookingFormService.deleteBookingFormService(id);
		redirectAttributes.addFlashAttribute("message", "Booking Deleted Successfully");
		return "redirect:/admin/readAllBookings";
		
	}

	@GetMapping("changeCredentials")
	public String changeCredentialsView(Model m, HttpServletRequest req) {
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage", requestURI);
		return "admin/changecredentials";
	}

	// newusername - ujjwal
	// newpassword - System123#

	@GetMapping("addService")
	public String addServiceView(Model m, HttpServletRequest req) {
		String requestURI= req.getRequestURI();
		m.addAttribute("myCurrentPage", requestURI);
		return "admin/addservice";
	}
	
	@GetMapping("deleteService/{id}")
	public String deleteService(@PathVariable int id, RedirectAttributes redirectAtributes) {
		try {
			boolean deleteService = serviceFormService.deleteService(id);
			if(deleteService) {
				redirectAtributes.addFlashAttribute("message", "Service Deleted Successfully.");
			}
		} catch (Exception e) {
			redirectAtributes.addFlashAttribute("message", "Something went wrong.");
		}
		return "redirect:/admin/viewServices";
	}
	
	@GetMapping("viewServices")
	public String viewServices(Model m, HttpServletRequest req) {
		String requestURI = req.getRequestURI();
		m.addAttribute("myCurrentPage",requestURI);
		List<ServiceForm> services = serviceFormService.getServices();
		m.addAttribute("services", services);
		return "admin/viewservices";
	}

	@PostMapping("changeCredentials")
	public String changeCredentials(@RequestParam("oldusername") String oldusername,
			@RequestParam("oldpassword") String oldpassword, @RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword, RedirectAttributes redirectAttributes) {
		String result = adminCredentialsService.checkAdminCredentials(oldusername, oldpassword);
		System.out.println(result);
		if (result.equals("Success")) {
			result = adminCredentialsService.upadteAdminCredentials(newusername, newpassword, oldusername);
			System.out.println(result);
			System.out.println("inside if");
			redirectAttributes.addFlashAttribute("message", result);

		} else {
			System.out.println("inside else");
			redirectAttributes.addFlashAttribute("message", result);

		}

		return "redirect:/admin/dashboard";
	}
	
	@InitBinder
	public void stopBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addService")
	public String addService(@ModelAttribute ServiceForm serviceForm, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		try {
			ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
			if(service!=null) {
				redirectAttributes.addFlashAttribute("msg", "Service added Successfully");
			}
			else {
				redirectAttributes.addFlashAttribute("msg", "Something went wrong");
			}
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Something went wrong");
		}
		return "redirect:/admin/addService";
	}

}

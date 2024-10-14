package com.taxibooking.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.taxibooking.model.ServiceForm;

public interface ServiceFormService  {

	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception;
	public List<ServiceForm> getServices();
	public boolean deleteService(int id) throws Exception;
}

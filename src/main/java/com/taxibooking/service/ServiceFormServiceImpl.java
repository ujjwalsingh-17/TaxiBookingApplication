package com.taxibooking.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taxibooking.dao.ServiceFormCrud;
import com.taxibooking.model.ServiceForm;

import jakarta.transaction.Transactional;

@Service
public class ServiceFormServiceImpl implements ServiceFormService {
	
	private ServiceFormCrud serviceFormCrud;

	@Autowired
	public void setServiceFormCrud(ServiceFormCrud serviceFormCrud) {
		this.serviceFormCrud = serviceFormCrud;
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception {
	
		ServiceForm save =null;
		try {
			save= serviceFormCrud.save(serviceForm);
			if(save!=null) {
				String path="C:\\Users\\hp\\Documents\\workspace-spring-tool-suite-4-4.21.1.RELEASE\\taxibooking\\src\\main\\resources\\static\\myserviceimg\\"+multipartFile.getOriginalFilename();
				byte[] bytes = multipartFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(bytes);
				
			}
		}
		catch(Exception e) {
			save=null;
			throw e;
		}
		
		return save;
	}

	@Override
	public List<ServiceForm> getServices() {
		// TODO Auto-generated method stub
		return serviceFormCrud.findAll();
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public boolean deleteService(int id) throws Exception {
		try {
			Optional<ServiceForm> serviceOptional = serviceFormCrud.findById(id);
			if(serviceOptional.isPresent()) {
				ServiceForm serviceForm = serviceOptional.get();
				String imagePath="C:\\Users\\hp\\Documents\\workspace-spring-tool-suite-4-4.21.1.RELEASE\\taxibooking\\src\\main\\resources\\static\\myserviceimg\\"+
				serviceForm.getImage();
				
				serviceFormCrud.deleteById(id);
				
				File imageFile=new File(imagePath);
				if(imageFile.exists()) {
					imageFile.delete();
				}
				return true;
			}
			else {
				throw new Exception("Service not found with id: "+ id);
			}
		} catch (Exception e) {
			throw new Exception("An error occured while deleting service " +e.getMessage());
		}
		
	}

}

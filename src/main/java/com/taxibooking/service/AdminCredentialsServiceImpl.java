package com.taxibooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taxibooking.dao.AdminDao;
import com.taxibooking.model.Admin;

@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService {
	
	private AdminDao adminDao;
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public String checkAdminCredentials(String oldusername, String oldpassword) {
		// TODO Auto-generated method stub
		
		Optional<Admin> byUsername = adminDao.findByUsername(oldusername);
		if(byUsername.isPresent()) {
			Admin admin = byUsername.get();
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
			if(matches) {
				return "Success";
			}
			else {
				return "Either Old UserName or Old Password is wrong";
			}
		}
		else {
			return "Either Old UserName or Old Password is wrong";
		}
	}

	@Override
	public String upadteAdminCredentials(String newusername, String newpassword, String oldusername) {
		// TODO Auto-generated method stub
		int updateCredentials = adminDao.updateCredentials(newusername, passwordEncoder.encode(newpassword), oldusername);
		if(updateCredentials==1)
			return "Credentials Upated Successfully";
		else {
			return "Failed to Update Credentials";
		}
	}

}

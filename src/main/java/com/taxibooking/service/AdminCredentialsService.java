package com.taxibooking.service;

public interface AdminCredentialsService {

	public String checkAdminCredentials(String oldusername,String oldpassword);
	public String upadteAdminCredentials(String newusername,String newpassword,String oldusername);
}

package com.masai.Impl;

import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.dao.SessionDao;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.LogInDTO;
//import net.bytebuddy.utility.RandomString;
import com.masai.service.LoginService;

import net.bytebuddy.utility.RandomString;

@Service(value = "admin")
public class AdminLoginImpl implements LoginService {

	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private SessionDao sDao;

	@Override
	public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException {

        Admin exsistingAdmin = aDao.findByMobileNo(dto.getMobileNo());
		 
		 if(exsistingAdmin == null) {
			 throw new UserException("Invalid Mobile Number");
		 }
		 
		 Optional<CurrentUserSession> validCustomerSession = sDao.findById(exsistingAdmin.getAdminId()); 
		 
		 if(validCustomerSession.isPresent()) {
			 throw new UserException("User Already loggedin");
		 }
		 
		 if(exsistingAdmin.getPassword().equals(dto.getPassword())) {
			 String key = RandomString.make(6);			 
			 CurrentUserSession cUser = new CurrentUserSession(exsistingAdmin.getAdminId() ,"Admin", key, LocalDateTime.now());
		      sDao.save(cUser);
		      return cUser;
		 }else {
			 throw new UserException("Wrong password..!");
		 }
		 

	}

	@Override
	public String LogoutFromAccount(String key) throws LoginException, UserException {
		
		CurrentUserSession activeAdmin = sDao.findByUuid(key);
		if(activeAdmin == null) {
			 throw new UserException("Please Provide Valid Key");
		}
		else {
			sDao.delete(activeAdmin);
			return activeAdmin.getCurrentUserId() +" Logged Out..!";
		}
		
	
	}
	
	


}

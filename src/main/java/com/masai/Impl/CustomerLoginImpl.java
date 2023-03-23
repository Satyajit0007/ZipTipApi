package com.masai.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.masai.dao.SessionDao;
import com.masai.dao.UserDao;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.LogInDTO;
import com.masai.model.User;
import com.masai.service.LoginService;

import net.bytebuddy.utility.RandomString;


@Service(value = "user")
public class CustomerLoginImpl implements LoginService {
	
	@Autowired
    private UserDao uDao;
	
	@Autowired
	private  SessionDao sDao;

	@Override
	public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException {
		  User exsistingUser = uDao.findByMobileNo(dto.getMobileNo());
			 
			 if(exsistingUser == null) {
				 throw new UserException("Invalid Mobile Number");
			 }
			 
			 Optional<CurrentUserSession> validCustomerSession = sDao.findById(exsistingUser.getUserId()); 
			 
			 if(validCustomerSession.isPresent()) {
				 throw new UserException("User Already loggedin");
			 }
			 
			 if(exsistingUser.getPassword().equals(dto.getPassword())) {
				 String key = RandomString.make(6);
				 CurrentUserSession cUser = new CurrentUserSession(exsistingUser.getUserId() ,"User", key, LocalDateTime.now());
			      sDao.save(cUser);
			      return cUser;
			 }else {
				 throw new UserException("Wrong password..!");
			 }
			 
	}

	@Override
	public String LogoutFromAccount(String key) throws LoginException, UserException {
		CurrentUserSession activeUser = sDao.findByUuid(key);
		if(activeUser == null) {
			 throw new UserException("Please Provide Valid Key");
		}
		else {
			sDao.delete(activeUser);
			return  activeUser.getCurrentUserId() +" Logged Out..!";
		}
	}

}


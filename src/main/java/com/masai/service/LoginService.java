package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.LogInDTO;

public interface LoginService  {

	public CurrentUserSession logIntoAccount(LogInDTO dto) throws LoginException, UserException;
	
	public String LogoutFromAccount(String key) throws LoginException, UserException;
}

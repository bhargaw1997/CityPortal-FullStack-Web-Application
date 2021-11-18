package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.LoginDAO;
import com.ui.model.User;

@RestController
public class LoginController {
	@Autowired
	LoginDAO loginDAO;
	
	User user;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "checkAdminLogin", method = RequestMethod.POST)
	public List<User> checkAdminLogin(String email, String password, HttpSession session)
	{
		logger.info("Inside Check Admin Login Controller");

		List<User> user = loginDAO.checkAdminLogin(email, password);
		
		if(user.size() != 0)
		{
			session.setMaxInactiveInterval(30*60);	//Set Session Time Out time is 30 Minutes
			
			session.setAttribute("useridadmin", user.get(0).getUserId());
			session.setAttribute("showname", user.get(0).getFirstName() +" "+user.get(0).getLastName());
			session.setAttribute("emailid", user.get(0).getEmail());
		}

		return user;
	}
	
	

}
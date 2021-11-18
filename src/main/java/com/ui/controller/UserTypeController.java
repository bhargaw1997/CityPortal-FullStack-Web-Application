package com.ui.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.UserTypeDAO;
import com.ui.model.UserType;

@RestController
public class UserTypeController
{
	@Autowired
	UserTypeDAO userTypeDAO;
	
	UserType userType;

	private static final Logger logger = LoggerFactory.getLogger(UserTypeController.class);
	
	@RequestMapping(value="/getUserTypes", method= RequestMethod.GET, produces = "application/json")
	public List<UserType> getUserTypes(HttpServletRequest request)
	{
		logger.info("Inside Get All User Type Controller");
		
		List<UserType> d = userTypeDAO.getAllUserTypes();
		
		return d;
	}
	
	@RequestMapping(value="/getUserTypesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<UserType> getUserTypesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All User Type Controller");
		
		List<UserType> d = userTypeDAO.getAllUserTypesByPage(pagesize, startindex);
		
		return d;
	}
	

	
	@RequestMapping(value="addUserType", method= RequestMethod.POST)
	public String addUserType(String usertypename, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add User Type Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		userType = new UserType(usertypename, s, id, IpAddress);
		userTypeDAO.addUserType(userType);

		return "";
	}
	
	
	@RequestMapping(value="editUserType", method= RequestMethod.POST)
	public String editUserType(int usertypeid, String usertypename, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit User Type Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		userType = new UserType(usertypeid, usertypename, id, IpAddress);
		userTypeDAO.editUserType(userType);
		
		return "";
	}
	
	@RequestMapping(value="deleteUserType", method= RequestMethod.DELETE)
	public void delete(int usertypeid)
	{
		logger.info("Inside Delete User Type Controller...");

		userTypeDAO.deleteUserType(usertypeid);
	}		
		

}
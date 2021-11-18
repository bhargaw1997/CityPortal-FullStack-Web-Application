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

import com.ui.dao.UserDAO;
import com.ui.model.User;

@RestController
public class UserController
{
	@Autowired
	UserDAO userDAO;
	
	User user;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/getUsers", method= RequestMethod.GET, produces = "application/json")
	public List<User> getUsers(HttpServletRequest request)
	{
		logger.info("Inside Get All User Controller");
		
		List<User> user = userDAO.getAllUsers();
		
		return user;
	}
	
	@RequestMapping(value="/getUsersByPage", method= RequestMethod.GET, produces = "application/json")
	public List<User> getUsersByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All User By Page Controller");
		
		List<User> user = userDAO.getAllUsersByPage(pagesize, startindex);
		
		return user;
	}

	
	@RequestMapping(value="/getUserByUserTypeId", method= RequestMethod.GET, produces = "application/json")
	public List<User> getUserByUserTypeId(int usertypeid, HttpServletRequest request)
	{
		logger.info("Inside Get User By User Type Id Controller");
		
		List<User> user = userDAO.getUserByUserTypeId(usertypeid);
		
		return user;
	}
	
	@RequestMapping(value="/getUsersByAreaId", method= RequestMethod.GET, produces = "application/json")
	public List<User> getUsersByAreaId(int areaid, HttpServletRequest request)
	{
		logger.info("Inside Get User By State Id Controller");
		
		List<User> user = userDAO.getUserByAreaId(areaid);
		
		return user;
	}
	
	@RequestMapping(value="/getUserByUserId", method= RequestMethod.GET, produces = "application/json")
	public List<User> getUserByUserId(int userid, HttpServletRequest request)
	{
		logger.info("Inside Get User By User Id Controller");
		
		List<User> user = userDAO.getUserByUserId(userid);
		
		return user;
	}
	
	
	@RequestMapping(value="addUser", method= RequestMethod.POST)
	public String addUser(String firstname, String middlename, String lastname, int usertypename, String address1, String address2, int areaname, String pincode, String mobilenumber1,String mobilenumber2, String email, String password, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add User Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		user = new User(firstname, middlename, lastname, address1, address2, areaname, pincode, mobilenumber1,mobilenumber2, email, password, usertypename, s, id, IpAddress);
		userDAO.addUser(user);
		
		return "";
	}
	
	
	@RequestMapping(value="editUser", method= RequestMethod.POST)
	public String editUser(int userid, String firstname, String middlename, String lastname, int usertypename, String address1, String address2, int areaname, String pincode, String mobilenumber1,String mobilenumber2, String email, String password, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit User Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		user = new User(userid, firstname, middlename, lastname, address1, address2,  areaname, pincode, mobilenumber1,mobilenumber2, email, password, usertypename, id, IpAddress);
		userDAO.editUser(user);
		
		return "";
	}
	
	@RequestMapping(value="deleteUser", method= RequestMethod.DELETE)
	public void delete(int userid)
	{
		logger.info("Inside Delete User Controller...");

		userDAO.deleteUser(userid);
	}
	
	@RequestMapping(value = "checkEmailAddress", method = RequestMethod.POST)
	public String checkEmailAddress(HttpSession session, String email)
	{
		logger.info("Inside Check Email Address Controller");
		
		session.setMaxInactiveInterval(30*60);	//Set Session Time Out time is 30 Minutes
		
		List<User> user = userDAO.getAllUsers();
		
		for(int i = 0; i < user.size(); i++)
		{
			if(user.get(i).getEmail().equals(email))
			{
				return "Exist";
			}
		}
		
		return null;
	}
		


}
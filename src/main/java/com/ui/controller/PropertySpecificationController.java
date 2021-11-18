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

import com.ui.dao.PropertySpecificationDAO;
import com.ui.model.PropertySpecification;

@RestController
public class PropertySpecificationController
{
	@Autowired
	PropertySpecificationDAO propertyspecificationDAO;
	
	PropertySpecification propertyspecification;

	private static final Logger logger = LoggerFactory.getLogger(PropertySpecificationController.class);
	
	@RequestMapping(value="/getPropertySpecification", method= RequestMethod.GET, produces = "application/json")
	public List<PropertySpecification> getPropertySpecification(HttpServletRequest request)
	{
		logger.info("Inside Get All Specification Controller");
		
		List<PropertySpecification> propertyspecification = propertyspecificationDAO.getAllPropertySpecification();
		
		return propertyspecification;
	}
	
	@RequestMapping(value="/getPropertySpecificationByPage", method= RequestMethod.GET, produces = "application/json")
	public List<PropertySpecification> getPropertySpecificationByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Specification By Page Controller");
		
		List<PropertySpecification> propertyspecification = propertyspecificationDAO.getAllPropertySpecificationByPage(pagesize, startindex);
		
		return propertyspecification;
	}
		
	@RequestMapping(value="addPropertySpecification", method= RequestMethod.POST)
	public String addPropertySpecification(String specificationname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Specification Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		propertyspecification = new PropertySpecification(specificationname, s, id, IpAddress);
		propertyspecificationDAO.addPropertySpecification(propertyspecification);

		return "";
	}
	
	@RequestMapping(value="editPropertySpecification", method= RequestMethod.POST)
	public String editPropertySpecification(int specificationid, String specificationname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Specification Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		propertyspecification = new PropertySpecification(specificationid, specificationname, id, IpAddress);
		propertyspecificationDAO.editPropertySpecification(propertyspecification);
		
		return "";
	}
	
	@RequestMapping(value="deletePropertySpecification", method= RequestMethod.DELETE)
	public void delete(int specificationid)
	{
		logger.info("Inside Delete Specification Controller...");

		propertyspecificationDAO.deletePropertySpecification(specificationid);
	}		
		

}
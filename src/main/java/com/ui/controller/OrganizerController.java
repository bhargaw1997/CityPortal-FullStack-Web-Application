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

import com.ui.dao.OrganizerDAO;
import com.ui.model.Organizer;

@RestController
public class OrganizerController
{
	@Autowired
	OrganizerDAO organizerDAO;
	
	Organizer organizer;

	private static final Logger logger = LoggerFactory.getLogger(OrganizerController.class);
	
	@RequestMapping(value="/getOrganizers", method= RequestMethod.GET, produces = "application/json")
	public List<Organizer> getOrganizers(HttpServletRequest request)
	{
		logger.info("Inside Get All Organizer Controller");
		
		List<Organizer> organizer = organizerDAO.getAllOrganizers();
		
		return organizer;
	}
	
	@RequestMapping(value="/getOrganizersByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Organizer> getOrganizersByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Organizer By Page Controller");
		
		List<Organizer> organizer = organizerDAO.getAllOrganizersByPage(pagesize, startindex);
		
		return organizer;
	}
	
	@RequestMapping(value="addOrganizer", method= RequestMethod.POST)
	public String addOrganizer(String organizername, String mobilenumber, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Organizer Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		organizer = new Organizer(organizername, mobilenumber,s, id, IpAddress);
		organizerDAO.addOrganizer(organizer);

		return "";
	}
	
	@RequestMapping(value="editOrganizer", method= RequestMethod.POST)
	public String editOrganizer(int organizerid, String organizername, String mobilenumber, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Organizer Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		organizer = new Organizer(organizerid, organizername,mobilenumber, id, IpAddress);
		organizerDAO.editOrganizer(organizer);
		
		return "";
	}
	
	@RequestMapping(value="deleteOrganizer", method= RequestMethod.DELETE)
	public void delete(int organizerid)
	{
		logger.info("Inside Delete Organizer Controller...");

		organizerDAO.deleteOrganizer(organizerid);
	}		
		

}
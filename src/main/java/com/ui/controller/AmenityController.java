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

import com.ui.dao.AmenityDAO;
import com.ui.model.Amenity;

@RestController
public class AmenityController
{
	@Autowired
	AmenityDAO amenityDAO;
	
	Amenity amenity;

	private static final Logger logger = LoggerFactory.getLogger(AmenityController.class);
	
	@RequestMapping(value="/getAmenities", method= RequestMethod.GET, produces = "application/json")
	public List<Amenity> getAmenities(HttpServletRequest request)
	{
		logger.info("Inside Get All Amenity Controller");
		
		List<Amenity> amenity = amenityDAO.getAllAmenities();
		
		return amenity;
	}
	
	@RequestMapping(value="/getAmenitiesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Amenity> getAmenitiesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Amenity By Page Controller");
		
		List<Amenity> amenity = amenityDAO.getAllAmenitiesByPage(pagesize, startindex);
		
		return amenity;
	}
		
	@RequestMapping(value="addAmenity", method= RequestMethod.POST)
	public String addAmenity(String amenityname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Amenity Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		amenity = new Amenity(amenityname, s, id, IpAddress);
		amenityDAO.addAmenity(amenity);

		return "";
	}
	
	@RequestMapping(value="editAmenity", method= RequestMethod.POST)
	public String editAmenity(int amenityid, String amenityname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Amenity Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		amenity = new Amenity(amenityid, amenityname, id, IpAddress);
		amenityDAO.editAmenity(amenity);
		
		return "";
	}
	
	@RequestMapping(value="deleteAmenity", method= RequestMethod.DELETE)
	public void delete(int amenityid)
	{
		logger.info("Inside Delete Amenity Controller...");

		amenityDAO.deleteAmenity(amenityid);
	}		
		

}
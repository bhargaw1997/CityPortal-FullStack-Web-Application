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

import com.ui.dao.CityDAO;
import com.ui.model.City;

@RestController
public class CityController
{
	@Autowired
	CityDAO cityDAO;
	
	City city;

	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
	
	@RequestMapping(value="/getCities", method= RequestMethod.GET, produces = "application/json")
	public List<City> getCities(HttpServletRequest request)
	{
		logger.info("Inside Get All City Controller");
		
		List<City> city = cityDAO.getAllCities();
		
		return city;
	}
	
	@RequestMapping(value="/getCitiesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<City> getCitiesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All City By Page Controller");
		
		List<City> city = cityDAO.getAllCitiesByPage(pagesize, startindex);
		
		return city;
	}
	
	@RequestMapping(value="/searchCities", method= RequestMethod.GET, produces = "application/json")
	public List<City> searchCities(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search City Controller");
		
		List<City> city = cityDAO.searchCities(keyword);
		
		return city;
	}
	
	@RequestMapping(value="/getCityByStateId", method= RequestMethod.GET, produces = "application/json")
	public List<City> getCityByStateId(int stateid)
	{
		logger.info("Inside Get City By State Id");
	
		List<City> a = cityDAO.getCityByStateId(stateid);
		
		return a;
	}
	
	@RequestMapping(value="addCity", method= RequestMethod.POST)
	public String addCity(String cityname, int statename, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add City Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		
		city = new City(cityname, statename, status, id, IpAddress);
		cityDAO.addCity(city);

		return "";
	}
	
	@RequestMapping(value="editCity", method= RequestMethod.POST)
	public String editCity(int cityid, String cityname, int statename, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit City Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		city = new City(cityid, cityname, statename, id, IpAddress);
		cityDAO.editCity(city);
		
		return "";
	}
	
	@RequestMapping(value="deleteCity", method= RequestMethod.DELETE)
	public void delete(int cityid)
	{
		logger.info("Inside Delete City Controller...");

		cityDAO.deleteCity(cityid);
	}		
		

}
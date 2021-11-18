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

import com.ui.dao.AreaDAO;
import com.ui.model.Area;

@RestController
public class AreaController
{
	@Autowired
	AreaDAO areaDAO;
	
	Area area;

	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@RequestMapping(value="/getAreas", method= RequestMethod.GET, produces = "application/json")
	public List<Area> getAreas(HttpServletRequest request)
	{
		logger.info("Inside Get All Area Controller");
		
		List<Area> area = areaDAO.getAllAreas();
		
		return area;
	}
	
	@RequestMapping(value="/getAreasByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Area> getAreasByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Area By Page Controller");
		
		List<Area> area = areaDAO.getAllAreasByPage(pagesize, startindex);
		
		return area;
	}
	
	@RequestMapping(value="/searchAreas", method= RequestMethod.GET, produces = "application/json")
	public List<Area> searchAreas(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search Area Controller");
		
		List<Area> area = areaDAO.searchAreas(keyword);
		
		return area;
	}
	
	@RequestMapping(value="/getAreaByCityId", method= RequestMethod.GET, produces = "application/json")
	public List<Area> getAreaByCityId(int cityid)
	{
		logger.info("Inside Get Area By City Id");
	
		List<Area> a = areaDAO.getAreaByCityId(cityid);
		
		return a;
	}
	
	@RequestMapping(value="/getAreaByAreaId", method= RequestMethod.GET, produces = "application/json")
	public List<Area> getAreaByAreaId(int areaid)
	{
		logger.info("Inside Get Area By Area Id");
	
		List<Area> a = areaDAO.getAreaByAreaId(areaid);
		
		return a;
	}
	
	@RequestMapping(value="addArea", method= RequestMethod.POST)
	public String addArea(String areaname, int cityname, String areacode, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Area Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		
		area = new Area(areaname, cityname, areacode, status, id, IpAddress);
		areaDAO.addArea(area);

		return "";
	}
	
	@RequestMapping(value="editArea", method= RequestMethod.POST)
	public String editArea(int areaid, String areaname, int cityname, String areacode, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Area Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		area = new Area(areaid, areaname, cityname, areacode, id, IpAddress);
		areaDAO.editArea(area);
		
		return "";
	}
	
	@RequestMapping(value="deleteArea", method= RequestMethod.DELETE)
	public void delete(int areaid)
	{
		logger.info("Inside Delete Area Controller...");

		areaDAO.deleteArea(areaid);
	}		
		

}
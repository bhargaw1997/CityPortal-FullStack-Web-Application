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

import com.ui.dao.TheatreDAO;
import com.ui.model.Theatre;
import com.ui.model.ScreenNumber;


@RestController
public class TheatreController
{
	@Autowired
	TheatreDAO theatreDAO;
	
	Theatre theatre1;

	ScreenNumber screenNumber;

	private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);
	
	@RequestMapping(value="/getTheatres", method= RequestMethod.GET, produces = "application/json")
	public List<Theatre> getTheatres(HttpServletRequest request)
	{
		logger.info("Inside Get All Theatre Controller");
		
		List<Theatre> theatre = theatreDAO.getAllTheatres();
		
		return theatre;
	}
	
	@RequestMapping(value="/getTheatresByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Theatre> getTheatresByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Theatres By Page Controller");
		
		List<Theatre> theatre = theatreDAO.getAllTheatresByPage(pagesize, startindex);
		
		return theatre;
	}
	
	
	@RequestMapping(value="/getScreenNumber", method= RequestMethod.GET, produces = "application/json")
	public List<ScreenNumber> getScreenNumber(HttpServletRequest request)
	{
		logger.info("Inside Get All Screen Number Controller");
		
		List<ScreenNumber> t = theatreDAO.getAllScreenNumber();
		
		return t;
	}
	
	@RequestMapping(value="/getScreenNumberByTheatreId", method= RequestMethod.GET, produces = "application/json")
	public List<ScreenNumber> getScreenNumberByTheatreId(int theatreid, HttpServletRequest request)
	{
		logger.info("Inside Get Screen Number By Theatre Id Controller");
		
		List<ScreenNumber> screennumber = theatreDAO.getScreenNumberByTheatreId(theatreid);
		
				
		return screennumber;
	}
	
	@RequestMapping(value = "addScreenNumber", method = RequestMethod.POST)
	public String addScreenNumber(String screennumber,String noofseats, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Add Screen Number Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int theatreid = theatreDAO.getLastTheatreId();
		
		screenNumber = new ScreenNumber(screennumber,noofseats, theatreid, id, IpAddress);
		theatreDAO.addScreenNumber(screenNumber);

		return "";
	}
	
	@RequestMapping(value="addTheatre", method= RequestMethod.POST)
	public String addTheatre(String theatrename, String theatreaddress,String theatredescription,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Theatre Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		theatre1 = new Theatre(theatrename,theatreaddress, theatredescription,s, id, IpAddress);
		theatreDAO.addTheatre(theatre1);

		return "";
	}
	
	@RequestMapping(value="/deleteScreenNumber",method= RequestMethod.DELETE)
	public void deleteScreenNumber(int screennumberid)
	{
		logger.info("Inside Delete Screen Number Controller");
		
		theatreDAO.deleteScreenNumber(screennumberid);
	}
		
	@RequestMapping(value="editTheatre", method= RequestMethod.POST)
	public String editTheatre(int theatreid, String theatrename,String theatreaddress, String theatredescription,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Theatre Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		theatre1 = new Theatre(theatreid,theatrename,theatreaddress,theatredescription,id, IpAddress);
		theatreDAO.editTheatre(theatre1);

		return "";
	}
	
	@RequestMapping(value = "editScreenNumber", method = RequestMethod.POST)
	public String editScreenNumber(int theatreid, String screennumber, String noofseats, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit Screen Number Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		screenNumber = new ScreenNumber(screennumber,noofseats, theatreid, id, IpAddress);
		theatreDAO.addScreenNumber(screenNumber);

		return "";
	}
	
	@RequestMapping(value="deleteTheatre", method= RequestMethod.DELETE)
	public void delete(int theatreid)
	{
		logger.info("Inside Delete Theatre Controller...");

		theatreDAO.deleteTheatre(theatreid);
	}
}
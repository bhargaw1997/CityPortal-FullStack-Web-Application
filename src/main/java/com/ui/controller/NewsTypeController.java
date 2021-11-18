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

import com.ui.dao.NewsTypeDAO;
import com.ui.model.NewsType;

@RestController
public class NewsTypeController
{
	@Autowired
	NewsTypeDAO newstypeDAO;
	
	NewsType newstype;

	private static final Logger logger = LoggerFactory.getLogger(OrganizerController.class);
	
	@RequestMapping(value="/getNewsTypes", method= RequestMethod.GET, produces = "application/json")
	public List<NewsType> getNewsTypes(HttpServletRequest request)
	{
		logger.info("Inside Get All Newstype Controller");
		
		List<NewsType> newstype = newstypeDAO.getAllNewsTypes();
		
		return newstype;
	}
	
	@RequestMapping(value="/getNewsTypesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<NewsType> getNewsTypesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get NewsType By Page Controller");
		
		List<NewsType> newstype = newstypeDAO.getAllNewsTypesByPage(pagesize, startindex);
		
		return newstype;
	}
	
	@RequestMapping(value="addNewsType", method= RequestMethod.POST)
	public String addNewsType(String newstypename, String newsdescription, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add NewsType Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		newstype = new NewsType(newstypename,newsdescription,s, id, IpAddress);
		newstypeDAO.addNewsType(newstype);

		return "";
	}
	
	@RequestMapping(value="editNewsType", method= RequestMethod.POST)
	public String editNewsType(int newstypeid, String newstypename, String newsdescription, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit newstype Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		newstype = new NewsType(newstypeid, newstypename,newsdescription, id, IpAddress);
		newstypeDAO.editNewsType(newstype);
		
		return "";
	}
	
	@RequestMapping(value="deleteNewsType", method= RequestMethod.DELETE)
	public void delete(int newstypeid)
	{
		logger.info("Inside Delete NewsType Controller...");

		newstypeDAO.deleteNewsType(newstypeid);
	}		
		

}
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

import com.ui.dao.ProducttaxDAO;
import com.ui.model.Producttax;

@RestController
public class ProducttaxController
{
	@Autowired
	ProducttaxDAO producttaxDAO;
	
	Producttax producttax;

	private static final Logger logger = LoggerFactory.getLogger(ProducttaxController.class);
	
	@RequestMapping(value="/getProducttaxes", method= RequestMethod.GET, produces = "application/json")
	public List<Producttax> getProducttaxes(HttpServletRequest request)
	{
		logger.info("Inside Get All Producttax Controller");
		
		List<Producttax> producttax = producttaxDAO.getAllProducttaxes();
		
		return producttax;
	}
	
	@RequestMapping(value="/getProducttaxesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Producttax> getProducttaxesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Producttax By Page Controller");
		
		List<Producttax> producttax = producttaxDAO.getAllProducttaxesByPage(pagesize, startindex);
		
		return producttax;
	}
	
	@RequestMapping(value="addProducttax", method= RequestMethod.POST)
	public String addProducttax(String taxname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Producttax Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		producttax = new Producttax(taxname, s, id, IpAddress);
		producttaxDAO.addProducttax(producttax);

		return "";
	}
	
	@RequestMapping(value="editProducttax", method= RequestMethod.POST)
	public String editProducttax(int taxid, String taxname,  HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Producttax Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		producttax = new Producttax(taxid, taxname, id, IpAddress);
		producttaxDAO.editProducttax(producttax);
		
		return "";
	}
	
	@RequestMapping(value="deleteProducttax", method= RequestMethod.DELETE)
	public void delete(int taxid)
	{
		logger.info("Inside Delete Producttax Controller...");

		producttaxDAO.deleteProducttax(taxid);
	}		
		

}
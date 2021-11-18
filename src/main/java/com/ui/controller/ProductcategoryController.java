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

import com.ui.dao.ProductcategoryDAO;
import com.ui.model.Productcategory;

@RestController
public class ProductcategoryController
{
	@Autowired
	ProductcategoryDAO productcategoryDAO;
	
	Productcategory productcategory;

	private static final Logger logger = LoggerFactory.getLogger(ProductcategoryController.class);
	
	@RequestMapping(value="/getProductcategories", method= RequestMethod.GET, produces = "application/json")
	public List<Productcategory> getProductcategories(HttpServletRequest request)
	{
		logger.info("Inside Get All Productcategory Controller");
		
		List<Productcategory> productcategory = productcategoryDAO.getAllProductcategories();
		
		return productcategory;
	}
	
	@RequestMapping(value="/getProductcategoriesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Productcategory> getProductcategoriesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Productcategory By Page Controller");
		
		List<Productcategory> productcategory = productcategoryDAO.getAllProductcategoriesByPage(pagesize, startindex);
		
		return productcategory;
	}
	
	
	
	@RequestMapping(value="addProductcategory", method= RequestMethod.POST)
	public String addProductcategory(String categoryname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Productcategory Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		productcategory = new Productcategory(categoryname, s, id, IpAddress);
		productcategoryDAO.addProductcategory(productcategory);

		return "";
	}
	
	@RequestMapping(value="editProductcategory", method= RequestMethod.POST)
	public String editProductcategory(int categoryid, String categoryname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Productcategory Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		productcategory = new Productcategory(categoryid, categoryname, id, IpAddress);
		productcategoryDAO.editProductcategory(productcategory);
		
		return "";
	}
	
	@RequestMapping(value="deleteProductcategory", method= RequestMethod.DELETE)
	public void delete(int categoryid)
	{
		logger.info("Inside Delete Productcategory Controller...");

		productcategoryDAO.deleteProductcategory(categoryid);
	}		
		

}
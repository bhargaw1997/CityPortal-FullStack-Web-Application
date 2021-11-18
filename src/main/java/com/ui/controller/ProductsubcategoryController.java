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

import com.ui.dao.ProductsubcategoryDAO;
import com.ui.model.Productsubcategory;

@RestController
public class ProductsubcategoryController
{
	@Autowired
	ProductsubcategoryDAO productsubcategoryDAO;
	
	Productsubcategory productsubcategory;

	private static final Logger logger = LoggerFactory.getLogger(ProductsubcategoryController.class);
	
	@RequestMapping(value="/getProductsubcategories", method= RequestMethod.GET, produces = "application/json")
	public List<Productsubcategory> getProductsubcategories(HttpServletRequest request)
	{
		logger.info("Inside Get All Productsubcategory Controller");
		
		List<Productsubcategory> productsubcategory = productsubcategoryDAO.getAllProductsubcategories();
		
		return productsubcategory;
	}
	
	@RequestMapping(value="/getProductsubcategoriesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Productsubcategory> getProductsubcategoriesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Productsubcategory By Page Controller");
		
		List<Productsubcategory> productsubcategory = productsubcategoryDAO.getAllProductsubcategoriesByPage(pagesize, startindex);
		
		return productsubcategory;
	}
	
	
	@RequestMapping(value="/getproductsubcategoryByproductcategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Productsubcategory> getProductsubcategoryByProductcategoryId(int categoryid)
	{
		logger.info("Inside Get Productsubcategory By Productcategory Id");
	
		List<Productsubcategory> s = productsubcategoryDAO.getProductsubcategoryByProductcategoryId(categoryid);
		
		return s;
	}
	
	@RequestMapping(value="addProductsubcategory", method= RequestMethod.POST)
	public String addProductsubcategory(String subcategoryname, int categoryname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Productsubcategory Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		
		productsubcategory = new Productsubcategory(subcategoryname, categoryname, status, id, IpAddress);
		productsubcategoryDAO.addProductsubcategory(productsubcategory);

		return "";
	}
	
	@RequestMapping(value="editProductsubcategory", method= RequestMethod.POST)
	public String editProductsubcategory(int subcategoryid, String subcategoryname, int categoryname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Productsubcategory Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		productsubcategory = new Productsubcategory(subcategoryid, subcategoryname, categoryname, id, IpAddress);
		productsubcategoryDAO.editProductsubcategory(productsubcategory);
		
		return "";
	}
	
	@RequestMapping(value="deleteProductsubcategory", method= RequestMethod.DELETE)
	public void delete(int productsubcategoryid)
	{
		logger.info("Inside Delete Productsubcategory Controller...");

		productsubcategoryDAO.deleteProductsubcategory(productsubcategoryid);
	}		
		

}
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

import com.ui.dao.SpecificationDAO;
import com.ui.model.Specification;

@RestController
public class SpecificationController
{
	@Autowired
	SpecificationDAO specificationDAO;
	
	Specification specification;

	private static final Logger logger = LoggerFactory.getLogger(SpecificationController.class);
	
	@RequestMapping(value="/getSpecifications", method= RequestMethod.GET, produces = "application/json")
	public List<Specification> getSpecifications(HttpServletRequest request)
	{
		logger.info("Inside Get All Specification Controller");
		
		List<Specification> specification = specificationDAO.getAllSpecifications();
		
		return specification;
	}
	
	@RequestMapping(value="/getSpecificationsByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Specification> getSpecificationsByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Specification By Page Controller");
		
		List<Specification> specification = specificationDAO.getAllSpecificationsByPage(pagesize, startindex);
		
		return specification;
	}
	
	
	@RequestMapping(value="/getSpecificationByProductsubcategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Specification> getSpecificationByStateId(int subcategoryid)
	{
		logger.info("Inside Get Specification By Sub Category Id");
	
		List<Specification> a = specificationDAO.getSpecificationByProductsubcategoryId(subcategoryid);
		
		return a;
	}
	
	@RequestMapping(value="addSpecification", method= RequestMethod.POST)
	public String addSpecification(String specificationname, int subcategoryname, int categoryname , HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Specification Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		
		specification = new Specification(specificationname, subcategoryname, categoryname, status, id, IpAddress);
		specificationDAO.addSpecification(specification);

		return "";
	}
	
	@RequestMapping(value="editSpecification", method= RequestMethod.POST)
	public String editSpecification(int specificationid, String specificationname, int subcategoryname, int categoryname, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Specification Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		specification = new Specification(specificationid, specificationname, subcategoryname, categoryname, id, IpAddress);
		specificationDAO.editSpecification(specification);
		
		return "";
	}
	
	@RequestMapping(value="deleteSpecification", method= RequestMethod.DELETE)
	public void delete(int specificationid)
	{
		logger.info("Inside Delete Specification Controller...");

		specificationDAO.deleteSpecification(specificationid);
	}		
		

}
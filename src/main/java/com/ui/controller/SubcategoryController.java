package com.ui.controller;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.SubcategoryDAO;
import com.ui.model.Subcategory;

@RestController
public class SubcategoryController
{
	@Autowired
	SubcategoryDAO subcategoryDAO;
	
	Subcategory subcategory;

	private static final Logger logger = LoggerFactory.getLogger(SubcategoryController.class);
	
	@RequestMapping(value="/getSubcategories", method= RequestMethod.GET, produces = "application/json")
	public List<Subcategory> getSubcategories(HttpServletRequest request)
	{
		logger.info("Inside Get All Subcategory Controller");
		
		List<Subcategory> subcategory = subcategoryDAO.getAllSubcategories();
		
		return subcategory;
	}
	
	@RequestMapping(value="/getSubcategoriesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Subcategory> getSubcategoriesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Subcategory By Page Controller");
		
		List<Subcategory> subcategory = subcategoryDAO.getAllSubcategoriesByPage(pagesize, startindex);
		
		return subcategory;
	}
	
/*	@RequestMapping(value="/searchSubcategories", method= RequestMethod.GET, produces = "application/json")
	public List<Subcategory> searchSubcategories(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search Subcategories Controller");
		
		List<Subcategory> subcategory = subcategoryDAO.searchSubcategories(keyword);
		
		return subcategory;
	}*/
	
	@RequestMapping(value="/getSubCategoriesByCategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Subcategory> getSubCategoriesByCategoryId(int categoryid, HttpServletRequest request)
	{
		logger.info("Inside Get All Subcategory By Category Id Controller");
		
		List<Subcategory> subcategory = subcategoryDAO.getSubcategoriesByCategoryId(categoryid);
		
		return subcategory;
	}
	
	@RequestMapping(value="addSubcategory", method= RequestMethod.POST)
	public String addSubcategory(@RequestParam(value="image", required=false) MultipartFile image, int categoryname, String subcategoryname, String subcategorycode, String description, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Subcategory Controller");
		
		String s0 = subcategoryname.replace("$","&");
		String s1 = s0.replace("~","#");
		String s2 = s1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		String image1 = "";
		
		try
		{
			if (image != null)
			{
				try
				{
					byte[] bytes =  image.getBytes();
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "subcategory");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/subcategory/");
			        File uploadfile = new File(path+File.separator+image.getOriginalFilename());
			            
			        /********* Today Start **********/
			            
			        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        try
			        {
			        	BufferedImage img = ImageIO.read(in);
		        		
		        		Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
		                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
		                Graphics2D drawer = SubImgage.createGraphics();
		                drawer.setComposite(AlphaComposite.Src);
		                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                drawer.drawImage(scaledImage, valuew, valueh, null);
		                drawer.dispose();
		                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		                ImageIO.write(SubImgage, "jpg", buffer);
		                bytes = buffer.toByteArray();
			        }
			        catch (IOException e)
			        {
			        	//throw new ApplicationException("IOException in scale");
			        }		            
			        /********* Today End **********/
			            
			        System.out.println("*********************Path"+path);         
			        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			        bufferedOutputStream.write(bytes);
			        bufferedOutputStream.close();			            
			            
			        //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/subcategory/"+image.getOriginalFilename();
			        image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/subcategory/"+image.getOriginalFilename();
			        
			        subcategory = new Subcategory(s2, subcategorycode, image1, description, categoryname, s, id, IpAddress);
					subcategoryDAO.addSubcategory(subcategory);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					subcategory = new Subcategory(s2, subcategorycode, image1, description, categoryname, s, id, IpAddress);
					subcategoryDAO.addSubcategory(subcategory);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			subcategory = new Subcategory(s2, subcategorycode, image1, description, categoryname, s, id, IpAddress);
			subcategoryDAO.addSubcategory(subcategory);
			
			return "";
		}
		
		subcategory = new Subcategory(s2, subcategorycode, image1, description, categoryname, s, id, IpAddress);
		subcategoryDAO.addSubcategory(subcategory);
		
		return "";
	}
	
	
	@RequestMapping(value="editSubcategory", method= RequestMethod.POST)
	public String editSubcategory(@RequestParam(value="image", required=false) MultipartFile image, int subcategoryid, int categoryname, String subcategoryname, String subcategorycode, String description, String subcategoryimage, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Subcategory Controller");
		
		String s = subcategoryname.replace("$","&");
		String s1 = s.replace("~","#");
		String s2 = s1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String image1 = "";

		try
		{
			if (image != null)
			{
				try
				{
					byte[] bytes =  image.getBytes();
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "subcategory");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/subcategory/");
			        File uploadfile = new File(path+File.separator+image.getOriginalFilename());
			            
			        /********* Today Start **********/
			            
			        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        try
			        {
			        	BufferedImage img = ImageIO.read(in);
		        		
		        		Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
		                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
		                Graphics2D drawer = SubImgage.createGraphics();
		                drawer.setComposite(AlphaComposite.Src);
		                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                drawer.drawImage(scaledImage, valuew, valueh, null);
		                drawer.dispose();
		                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		                ImageIO.write(SubImgage, "jpg", buffer);
		                bytes = buffer.toByteArray();
			        }
			        catch (IOException e)
			        {
			        	//throw new ApplicationException("IOException in scale");
			        }		            
			        /********* Today End **********/
			            
			        System.out.println("*********************Path"+path);         
			        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			        bufferedOutputStream.write(bytes);
			        bufferedOutputStream.close();
			            
			        //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/subcategory/"+image.getOriginalFilename();
			        image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/images/subcategory/"+image.getOriginalFilename();
			        
			        subcategory = new Subcategory(subcategoryid, s2, subcategorycode, image1, description, categoryname, id, IpAddress);
					subcategoryDAO.editSubcategory(subcategory);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					subcategory = new Subcategory(subcategoryid, s2, subcategorycode, subcategoryimage, description, categoryname, id, IpAddress);
					subcategoryDAO.editSubcategory(subcategory);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			subcategory = new Subcategory(subcategoryid, s2, subcategorycode, subcategoryimage, description, categoryname, id, IpAddress);
			subcategoryDAO.editSubcategory(subcategory);
			
			return "";
		}
		
		subcategory = new Subcategory(subcategoryid, s2, subcategorycode, subcategoryimage, description, categoryname, id, IpAddress);
		subcategoryDAO.editSubcategory(subcategory);
		
		return "";
	}
	
	@RequestMapping(value="deleteSubcategory", method= RequestMethod.DELETE)
	public void delete(int subcategoryid)
	{
		logger.info("Inside Delete Subcategory Controller...");

		subcategoryDAO.deleteSubcategory(subcategoryid);
	}		
		

}
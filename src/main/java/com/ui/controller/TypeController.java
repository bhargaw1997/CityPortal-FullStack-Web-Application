package com.ui.controller;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
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

import com.ui.dao.TypeDAO;
import com.ui.model.Type;

@RestController
public class TypeController
{
	@Autowired
	TypeDAO typeDAO;
	
	Type type;

	private static final Logger logger = LoggerFactory.getLogger(TypeController.class);
	
	@RequestMapping(value="/getTypes", method= RequestMethod.GET, produces = "application/json")
	public List<Type> getTypes(HttpServletRequest request)
	{
		logger.info("Inside Get All Type Controller");
		
		List<Type> type = typeDAO.getAllTypes();
		
		return type;
	}
	
	@RequestMapping(value="/getTypesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Type> getTypesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Type By Page Controller");
		
		List<Type> type = typeDAO.getAllTypesByPage(pagesize, startindex);
		
		return type;
	}
/*	
	@RequestMapping(value="/searchCategories", method= RequestMethod.GET, produces = "application/json")
	public List<Category> searchCategories(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search Categories Controller");
		
		List<Category> category = categoryDAO.searchCategories(keyword);
		
		return category;
	}*/
	
/*	@RequestMapping(value="/getCategoriesForFrontEnd", method= RequestMethod.GET, produces = "application/json")
	public List<Category> getCategoriesForFrontEnd(HttpServletRequest request)
	{
		logger.info("Inside Get All Category For Front End Controller");
		
		List<Category> category = categoryDAO.getAllCategoriesForFrontEnd();
		
		return category;
	}*/
	
/*	@RequestMapping(value="/getFeaturedCategory", method= RequestMethod.GET, produces = "application/json")
	public List<Category> getFeaturedCategory(HttpServletRequest request)
	{
		logger.info("Inside Get Featured Category Controller");
		
		List<Category> category = categoryDAO.getFeaturedCategory();
		
		return category;
	}*/
	
	@RequestMapping(value="addType", method= RequestMethod.POST)
	public String addType(@RequestParam(value="image", required=false) MultipartFile image, String typename, String typecode, String description,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Type Controller");
		
		String c = typename.replace("$","&");
		String c1 = c.replace("~","#");
		String c2 = c1.replace("!","%");
		
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
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "type");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/type/");
			        File uploadfile = new File(path+File.separator+image.getOriginalFilename());
			            
			        /********* Today Start **********/
			            
			        int height=480, width=700;
			            
			        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        try
			        {
			        	BufferedImage img = ImageIO.read(in);
			        		
			        	int original_width = img.getWidth();
			        	int original_height = img.getHeight();
			        	int bound_width = 700;
			        	int bound_height = 480;
			        	int new_width = original_width;
			        	int new_height = original_height;
			        		
			        	if (original_height/bound_height > original_width/bound_width) {
			        		bound_width = (int) (bound_height * original_width / original_height);
			        	} else {
			        		bound_height = (int) (bound_width * original_height / original_width);
			        	}
			        		
			        	Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
			        		
			        	BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		        			
			        	Graphics2D drawer = imageBuff.createGraphics() ;
			        	drawer.setBackground(Color.WHITE);
			        	//Color c = Color.decode(color1);
		        		//drawer.setBackground(c);
			        	drawer.clearRect(0,0,width,height);
		        			
			        	imageBuff.getGraphics().drawImage(scaledImage, (width-bound_width)/2, (height-bound_height)/2, new Color(0,0,0), null);
			        		
			        	ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			        	ImageIO.write(imageBuff, "jpg", buffer);

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
			            
			        //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/type/"+image.getOriginalFilename();
			        image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/type/"+image.getOriginalFilename();
			        
			        type = new Type(c2, typecode, image1, description,s, id, IpAddress);
					typeDAO.addType(type);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					type = new Type(c2, typecode, image1, description,s, id, IpAddress);
					typeDAO.addType(type);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			type = new Type(c2, typecode, image1, description,s, id, IpAddress);
			typeDAO.addType(type);
			
			return "";
		}
		
		type = new Type(c2, typecode, image1, description, s, id, IpAddress);
		typeDAO.addType(type);
		
		return "";
	}
	
	@RequestMapping(value="editType", method= RequestMethod.POST)
	public String editType(@RequestParam(value="image", required=false) MultipartFile image, int typeid, String typename, String typecode, String description, String typeimage, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Type Controller");
		
		String c = typename.replace("$","&");
		String c1 = c.replace("~","#");
		String c2 = c1.replace("!","%");
		
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
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "type");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/type/");
			        File uploadfile = new File(path+File.separator+image.getOriginalFilename());
			            
			        /********* Today Start **********/
			            
			        int height=480, width=700;
			            
			        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        try
			        {
			        	BufferedImage img = ImageIO.read(in);
			        		
			        	int original_width = img.getWidth();
			        	int original_height = img.getHeight();
			        	int bound_width = 700;
			        	int bound_height = 480;
			        	int new_width = original_width;
			        	int new_height = original_height;
			        		
			        	if (original_height/bound_height > original_width/bound_width) {
			        		bound_width = (int) (bound_height * original_width / original_height);
			        	} else {
			        		bound_height = (int) (bound_width * original_height / original_width);
			        	}
			        		
			        	Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
			        		
			        	BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		        			
			        	Graphics2D drawer = imageBuff.createGraphics() ;
			        	drawer.setBackground(Color.WHITE);
			        	//Color c = Color.decode(color1);
		        		//drawer.setBackground(c);
			        	drawer.clearRect(0,0,width,height);
		        			
			        	imageBuff.getGraphics().drawImage(scaledImage, (width-bound_width)/2, (height-bound_height)/2, new Color(0,0,0), null);
			        		
			        	ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			        	ImageIO.write(imageBuff, "jpg", buffer);

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
			            
			        //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/type/"+image.getOriginalFilename();
			        image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/type/"+image.getOriginalFilename();
			        
			        type = new Type(typeid, c2, typecode, image1, description, id, IpAddress);
					typeDAO.editType(type);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					type = new Type(typeid, c2, typecode, typeimage, description, id, IpAddress);
					typeDAO.editType(type);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			type = new Type(typeid, c2, typecode, typeimage, description, id, IpAddress);
			typeDAO.editType(type);
			
			return "";
		}
		
		type = new Type(typeid, c2, typecode, typeimage, description, id, IpAddress);
		typeDAO.editType(type);
		
		return "";
	}
	
	@RequestMapping(value="deleteType", method= RequestMethod.DELETE)
	public void delete(int typeid)
	{
		logger.info("Inside Delete Type Controller...");

		typeDAO.deleteType(typeid);
	}		
		

}
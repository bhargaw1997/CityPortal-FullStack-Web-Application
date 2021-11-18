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

import com.ui.dao.PropertyDAO;
import com.ui.model.Property;
import com.ui.model.PropertyImage;
import com.ui.model.Amenity;
import com.ui.model.PropertySpecification;
import com.ui.model.Review;

@RestController
public class PropertyController
{
	@Autowired
	PropertyDAO propertyDAO;
	
	
	Property property;
	PropertyImage propertyImage;
	
	Amenity amenity;
	PropertySpecification propertyspecification;
	Review review;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="/getProperty", method= RequestMethod.GET, produces = "application/json")
	public List<Property> getProperties(HttpServletRequest request)
	{
		logger.info("Inside Get All Properties Controller");
		
		List<Property> property = propertyDAO.getAllProperties();
		
		return property;
	}
	
	@RequestMapping(value="/getPropertiesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Property> getPropertiesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Properties By Page Controller");
		
		List<Property> property = propertyDAO.getAllPropertiesByPage(pagesize, startindex);
		
		return property;
	}
	
	
	
	@RequestMapping(value="/getPropertiesWithOneImageByPropertId", method= RequestMethod.GET, produces = "application/json")
	public List<Property> getPropertiesWithOneImageByPropertyId(int propertyid, HttpServletRequest request)
	{
		logger.info("Inside Get Properties By Property Id Controller");
		
		List<Property> property = propertyDAO.getPropertiesWithOneImageByPropertyId(propertyid);
		
		return property;
	}
	
	
	@RequestMapping(value="/getPropertyImageByPropertyId", method= RequestMethod.GET, produces = "application/json")
	public List<PropertyImage> getPropertyImageByPropertyId(int propertyid, HttpServletRequest request)
	{
		logger.info("Inside Get Property Image By Property Id Controller");
		
		List<PropertyImage> propertyimage = propertyDAO.getPropertyImageByPropertyId(propertyid);
		
		return propertyimage;
	}
	

	
	@RequestMapping(value="/getReviewByPropertyId", method= RequestMethod.GET, produces = "application/json")
	public List<Review> getReviewByPropertyId(int propertyid, HttpServletRequest request)
	{
		logger.info("Inside Get review By Property Id Controller");
		
		List<Review> review = propertyDAO.getReviewByPropertyId(propertyid);
		
		return review;
	}
	
	@RequestMapping(value="/getPropertyAmenitiesByPropertyId", method= RequestMethod.GET, produces = "application/json")
	public List<Amenity> getPropertyAmenitiesByPropertyId(int propertyid, HttpServletRequest request)
	{
		logger.info("Inside Get amenities By Property Id Controller");
		
		List<Amenity> amenity = propertyDAO.getPropertyAmenitiesByPropertyId(propertyid);
		
		return amenity;
	}
	
	@RequestMapping(value="/getPropertySpecification1ByPropertyId", method= RequestMethod.GET, produces = "application/json")
	public List<PropertySpecification> getPropertySpecification1ByPropertyId(int propertyid, HttpServletRequest request)
	{
		logger.info("Inside Get PropertySpecification By Property Id Controller");
		
		List<PropertySpecification> propertyspecification = propertyDAO.getPropertySpecification1ByPropertyId(propertyid);
		
		return propertyspecification;
	}
	
	
	@RequestMapping(value="addProperty", method= RequestMethod.POST)
	public String addProperty(String propertyname,String propertycondition,String projecttype,String propertytype,String projectsubtype, Float price,String location, int sequence, String description, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Property Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		property = new Property(propertyname,propertycondition,projecttype,propertytype,projectsubtype,price,location,sequence, description, s, id, IpAddress);
		propertyDAO.addProperty(property);

		return "";
	}
	
	@RequestMapping(value="addPropertyImage", method= RequestMethod.POST)
	public String addPropertyImage(@RequestParam(value="image", required=false) MultipartFile[] image, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Product Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		int propertyid = propertyDAO.getLastPropertyId();
		
		String image1 = "";
		
		try
		{           
			for(int i=0; i<image.length; i++)
			{
				if(image[i].getOriginalFilename() != null && image[i].getOriginalFilename() != "")
				{
					try
					{			
						byte[] bytes =  image[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "property");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/property/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            //int height=525, width=560;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		if(valuex[i] < 0 || valuey[i] < 0)
			        		{
			        			valuex[i] = 0;
			        			valuey[i] = 0;
			        			valuew[i] = 650;
			        			valueh[i] = 500;
			        		}
			        		
			        		Image scaledImage = img.getScaledInstance(valuew[i]-1, valueh[i], Image.SCALE_SMOOTH);
			                BufferedImage SubImgage = img.getSubimage(valuex[i], valuey[i], valuew[i]-1, valueh[i]);
			                Graphics2D drawer = SubImgage.createGraphics();
			                drawer.setComposite(AlphaComposite.Src);
			                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			                drawer.drawImage(scaledImage, valuew[i]-1, valueh[i], null);
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/property/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/property/"+image[i].getOriginalFilename();
			            
			            propertyImage = new PropertyImage(imagesequence[i], imagename[i], image1, propertyid, id, IpAddress);
			    		propertyDAO.addPropertyImage(propertyImage);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}
		
	@RequestMapping(value = "addReview", method = RequestMethod.POST)
	public String addReview(int reviewid,String reviewername, String review1, HttpServletRequest request,HttpSession session)
	{
		logger.info("Inside Add Review Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int propertyid = propertyDAO.getLastPropertyId();
		
		review = new Review(reviewid,reviewername, review1, propertyid, id, IpAddress);
		propertyDAO.addReview(review);

		return "";
	}
	
	@RequestMapping(value = "addPropertyAmenities", method = RequestMethod.POST)
	public String addPropertyAmenities(int amenitiesid, String value, HttpServletRequest request,HttpSession session)
	{
		logger.info("Inside Add Amenities Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int propertyid = propertyDAO.getLastPropertyId();
		
		amenity = new Amenity(amenitiesid, value, propertyid, id, IpAddress);
		propertyDAO.addPropertyAmenities(amenity);

		return "";
	}
	
	@RequestMapping(value = "addPropertySpecification1", method = RequestMethod.POST)
	public String addPropertySpecification1(int propertyspecificationid, String value, HttpServletRequest request,HttpSession session)
	{
		logger.info("Inside Add PropertySpecification Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int propertyid = propertyDAO.getLastPropertyId();
		
		propertyspecification = new PropertySpecification(propertyspecificationid, value, propertyid, id, IpAddress);
		propertyDAO.addPropertySpecification1(propertyspecification);

		return "";
	}
	
	@RequestMapping(value="/deletePropertyImage",method= RequestMethod.DELETE)
	public void deletePropertyImage(int propertyid)
	{
		logger.info("Inside Delete Property Image Controller");
		
		propertyDAO.deletePropertyImage(propertyid);
	}
	
	
	@RequestMapping(value="/deleteReview",method= RequestMethod.DELETE)
	public void deleteReview(int propertyid)
	{
		logger.info("Inside Delete Review Controller");
		
		propertyDAO.deleteReview(propertyid);
	}
	
	@RequestMapping(value="/deleteAmenities",method= RequestMethod.DELETE)
	public void deleteAmenities(int propertyid)
	{
		logger.info("Inside Delete Amenities Controller");
		
		propertyDAO.deleteAmenities(propertyid);
	}
	
	@RequestMapping(value="/deletePropertySpecification1",method= RequestMethod.DELETE)
	public void deletePropertySpecification1(int propertyid)
	{
		logger.info("Inside Delete PropertySpecification Controller");
		
		propertyDAO.deletePropertySpecification1(propertyid);
	}
	
	@RequestMapping(value="editProperty", method= RequestMethod.POST)
	public String editProduct(int propertyid,String propertyname,String propertycondition,String projecttype,String propertytype,String projectsubtype,Float price,String location,String description,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Property Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		property = new Property(propertyid, propertyname,propertycondition,projecttype,propertytype,projectsubtype,price,location,description,id, IpAddress);
		propertyDAO.editProperty(property);

		return "";
	}
	
	@RequestMapping(value="editPropertyImage", method= RequestMethod.POST)
	public String editPropertyImage(@RequestParam(value="image", required=false) MultipartFile[] image, int propertyid, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Property Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String image1 = "";
		
		try
		{           
			for(int i=0; i<image.length; i++)
			{
				if(image[i].getOriginalFilename() != null && image[i].getOriginalFilename() != "")
				{
					try
					{			
						byte[] bytes =  image[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "property");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/property/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            //int height=525, width=560;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		if(valuex[i] < 0 || valuey[i] < 0)
			        		{
			        			valuex[i] = 0;
			        			valuey[i] = 0;
			        			valuew[i] = 650;
			        			valueh[i] = 500;
			        		}
			        			
			        		Image scaledImage = img.getScaledInstance(valuew[i]-1, valueh[i], Image.SCALE_SMOOTH);
			                BufferedImage SubImgage = img.getSubimage(valuex[i], valuey[i], valuew[i]-1, valueh[i]);
			                Graphics2D drawer = SubImgage.createGraphics();
			                drawer.setComposite(AlphaComposite.Src);
			                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			                drawer.drawImage(scaledImage, valuew[i]-1, valueh[i], null);
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/property/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/property/"+image[i].getOriginalFilename();
			            
			            propertyImage = new PropertyImage(imagesequence[i], imagename[i], image1, propertyid, id, IpAddress);
			    		propertyDAO.addPropertyImage(propertyImage);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}
	
	@RequestMapping(value="addPropertyImageOld", method= RequestMethod.POST)
	public String addPropertyImageOld(int propertyid, int sequence, String imagename, String image, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Property Image Old Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		propertyImage = new PropertyImage(sequence, imagename, image, propertyid, id, IpAddress);
		propertyDAO.addPropertyImage(propertyImage);

		return "";
	}
		
	@RequestMapping(value = "editReview", method = RequestMethod.POST)
	public String editReview(int propertyid, int reviewid,String reviewername, String review1, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit REview Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		review = new Review(reviewid,reviewername, review1, propertyid, id, IpAddress);
		propertyDAO.addReview(review);

		return "";
	}
	
	@RequestMapping(value = "editPropertyAmenities", method = RequestMethod.POST)
	public String editPropertyAmenities(int propertyid, int amenitiesid, String value, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit Amenities Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		amenity = new Amenity(amenitiesid,value, propertyid, id, IpAddress);
		propertyDAO.addPropertyAmenities(amenity);

		return "";
	}
	
	@RequestMapping(value = "editPropertySpecification1", method = RequestMethod.POST)
	public String editPropertySpecification1(int propertyid, int propertyspecificationid, String value, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit PropertySpecification Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		propertyspecification = new PropertySpecification(propertyspecificationid, value, propertyid, id, IpAddress);
		propertyDAO.addPropertySpecification1(propertyspecification);

		return "";
	}
	
	@RequestMapping(value="deleteProperty", method= RequestMethod.DELETE)
	public void delete(int propertyid)
	{
		logger.info("Inside Delete Property Controller...");

		propertyDAO.deleteProperty(propertyid);
	}
	
}
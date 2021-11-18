
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

import com.ui.dao.CityguideDAO;
import com.ui.model.Cityguide;
import com.ui.model.CityguideImage;

@RestController
public class CityguideController
{
	@Autowired
	CityguideDAO cityguideDAO;
	
	Cityguide cityguide;
	CityguideImage cityguideImage;
	
	private static final Logger logger = LoggerFactory.getLogger(CityguideController.class);
	
	@RequestMapping(value="/getCityguides", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguides(HttpServletRequest request)
	{
		logger.info("Inside Get All Cityguides Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getAllCityguides();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Cityguides By Page Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getAllCityguidesByPage(pagesize, startindex);
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesWithOneImageByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesWithOneImageByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Cityguide with Image By Page Controller");
		
		List<Cityguide> cityguide =cityguideDAO.getCityguidesWithOneImageByPage(pagesize, startindex);
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesLifestyle", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesLifestyle(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesLifestyle();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesFashion", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesFashion(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesFashion();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesEatdrink", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesEatdrink(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesEatdrink();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesPlacetovisit", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesPlacetovisit(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesPlacetovisit();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesOfficialmatters", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesOfficialmatters(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesOfficialmatters();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesTransportation", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesTransportation(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesTransportation();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesHealth", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesHealth(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesHealth();
		
		return cityguide;
	}
	
	@RequestMapping(value="/getCityguidesHelplines", method= RequestMethod.GET, produces = "application/json")
	public List<Cityguide> getCityguidesHelplines(HttpServletRequest request)
	{
		logger.info("Inside Get Cityguides Lifestyle Controller");
		
		List<Cityguide> cityguide = cityguideDAO.getCityguidesHelplines();
		
		return cityguide;
	}
	
	
	@RequestMapping(value="/getCityguideImageByCityguideId", method= RequestMethod.GET, produces = "application/json")
	public List<CityguideImage> getCityguideImageByCityguideId(int cityguideid, HttpServletRequest request)
	{
		logger.info("Inside Get Cityguide Image By Cityguide Id Controller");
		
		List<CityguideImage> cityguideimage = cityguideDAO.getCityguideImageByCityguideId(cityguideid);
		
		return cityguideimage;
	}


	@RequestMapping(value="addCityguide", method= RequestMethod.POST)
	public String addCityguide(String placename, String locationurl, String description, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Cityguide Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		cityguide = new Cityguide(placename, locationurl,description, s, id, IpAddress);
		cityguideDAO.addCityguide(cityguide);

		return "";
	}
	
	@RequestMapping(value="addCityguideImage", method= RequestMethod.POST)
	public String addCityguideImage(@RequestParam(value="image", required=false) MultipartFile[] image, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Cityguide Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		int cityguideid = cityguideDAO.getLastCityguideId();
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "cityguide");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/cityguide/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/bms/resources/admin/images/cityguide/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/cityguide/"+image[i].getOriginalFilename();
			            
			            cityguideImage = new CityguideImage(imagesequence[i], imagename[i], image1, cityguideid, id, IpAddress);
			    		cityguideDAO.addCityguideImage(cityguideImage);
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
	
	
	@RequestMapping(value="/deleteCityguideImage",method= RequestMethod.DELETE)
	public void deleteCityguideImage(int imageid)
	{
		logger.info("Inside Delete Cityguide Image Controller");
		
		cityguideDAO.deleteCityguideImage(imageid);
	}
	
		
	@RequestMapping(value="editCityguide", method= RequestMethod.POST)
	public String editCityguide(int cityguideid, String placename,  String locationurl,String description, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Cityguide Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		cityguide = new Cityguide(cityguideid, placename,locationurl, description, id, IpAddress);
		cityguideDAO.editCityguide(cityguide);

		return "";
	}
	
	@RequestMapping(value="editCityguideImage", method= RequestMethod.POST)
	public String editCityguideImage(@RequestParam(value="image", required=false) MultipartFile[] image, int cityguideid, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Cityguide Image Controller");
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "cityguide");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/cityguide/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/cityguide/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/cityguide/"+image[i].getOriginalFilename();
			            
			            cityguideImage = new CityguideImage(imagesequence[i], imagename[i], image1, cityguideid, id, IpAddress);
			    		cityguideDAO.addCityguideImage(cityguideImage);
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
	
	@RequestMapping(value="addCityguideImageOld", method= RequestMethod.POST)
	public String addCityguideImageOld(int cityguideid, int sequence, String imagename, String image, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Cityguide Image Old Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		cityguideImage = new CityguideImage(sequence, imagename, image, cityguideid, id, IpAddress);
		cityguideDAO.addCityguideImage(cityguideImage);

		return "";
	}
	
	@RequestMapping(value="deleteCityguide", method= RequestMethod.DELETE)
	public void delete(int cityguideid)
	{
		logger.info("Inside Delete Cityguide Controller...");

		cityguideDAO.deleteCityguide(cityguideid);
	}
	


}
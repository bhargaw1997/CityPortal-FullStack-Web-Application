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

import com.ui.dao.NewsTypeDAO;
import com.ui.dao.NewsDAO;
import com.ui.model.News;
import com.ui.model.NewsImage;

@RestController
public class NewsController
{
	@Autowired
	NewsDAO newsDAO;
	
	@Autowired
	NewsTypeDAO newstypeDAO;
	
	
	News news;
	NewsImage newsImage;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="/getNews", method= RequestMethod.GET, produces = "application/json")
	public List<News> getNews(HttpServletRequest request)
	{
		logger.info("Inside Get All News Controller");
		
		List<News> news = newsDAO.getAllNews();
		
		return news;
	}
	
	@RequestMapping(value="/getNewsByNewsId", method= RequestMethod.GET, produces = "application/json")
	public News getNewsByNewsId(int newsid, HttpServletRequest request)
	{
		logger.info("Inside Get All News Controller");
		
		News news = newsDAO.getNewsByNewsId(newsid);
		
		return news;
	}
	@RequestMapping(value="/getNewsByPage", method= RequestMethod.GET, produces = "application/json")
	public List<News> getNewsByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All News By Page Controller");
		
		List<News> news = newsDAO.getAllNewsByPage(pagesize, startindex);
		
		return news;
	}
	
	@RequestMapping(value="/getNewsWithOneImageByPage", method= RequestMethod.GET, produces = "application/json")
	public List<News> getNewsWithOneImageByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All News By Page Controller");
		
		List<News> news = newsDAO.getAllNewsWithOneImageByPage(pagesize, startindex);
		
		return news;
	}
	
	@RequestMapping(value="/getNewsWithOneImageByNewsId", method= RequestMethod.GET, produces = "application/json")
	public List<News> getNewsWithOneImageByNewsId(int newsid, HttpServletRequest request)
	{
		logger.info("Inside Get News By News Id Controller");
		
		List<News> news = newsDAO.getNewsWithOneImageByNewsId(newsid);
		
		return news;
	}
		
	@RequestMapping(value="/getNewsImageByNewsId", method= RequestMethod.GET, produces = "application/json")
	public List<NewsImage> getNewsImageByNewsId(int newsid, HttpServletRequest request)
	{
		logger.info("Inside Get News Image By News Id Controller");
		
		List<NewsImage> newsimage = newsDAO.getNewsImageByNewsId(newsid);
		
		return newsimage;
	}
	
	@RequestMapping(value="/getLastFourNewsWithOneImage", method= RequestMethod.GET, produces = "application/json")
	public List<News> getLastFourNewsWithOneImage(HttpServletRequest request)
	{
		logger.info("Inside Get Last Four News With One Image Controller");
		
		List<News> news = newsDAO.getLastFourNewsWithOneImage();
		
		return news;
	}
	
	@RequestMapping(value="/getLastNewsWithOneImage", method= RequestMethod.GET, produces = "application/json")
	public List<News> getLastNewsWithOneImage(HttpServletRequest request)
	{
		logger.info("Inside Get Last News With One Image Controller");
		
		List<News> news = newsDAO.getLastNewsWithOneImage();
		
		return news;
	}
	@RequestMapping(value="/getSecondLastFourNewsWithOneImage", method= RequestMethod.GET, produces = "application/json")
	public List<News> getSecondLastFourNewsWithOneImage(HttpServletRequest request)
	{
		logger.info("Inside Get  Second Last News With One Image Controller");
		
		List<News> news = newsDAO.getSecondLastFourNewsWithOneImage();
		
		return news;
	}

	@RequestMapping(value="addNews", method= RequestMethod.POST)
	public String addNews(int newstypename, String newstitle,String newssubtitle,String newsdescription, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add News Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		news = new News(newstitle,newssubtitle,newsdescription, newstypename,s, id, IpAddress);
		newsDAO.addNews(news);

		return "";
	}
	
	@RequestMapping(value="addNewsImage", method= RequestMethod.POST)
	public String addNewsImage(@RequestParam(value="image", required=false) MultipartFile[] image, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add News Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		int newsid = newsDAO.getLastNewsId();
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "news");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/news/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/news/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/news/"+image[i].getOriginalFilename();
			            
			            newsImage = new NewsImage(imagesequence[i], imagename[i], image1, newsid, id, IpAddress);
			            newsDAO.addNewsImage(newsImage);
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
	

	@RequestMapping(value="/deleteNewsImage",method= RequestMethod.DELETE)
	public void deleteNewsImage(int imageid)
	{
		logger.info("Inside Delete News Image Controller");
		
		newsDAO.deleteNewsImage(imageid);
	}
	
	@RequestMapping(value="editNews", method= RequestMethod.POST)
	public String editNews(int newsid, int newstypename, String newstitle,String newssubtitle, String newsdescription, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit News Controller");
		
	
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		news = new News(newsid, newstitle,newssubtitle, newsdescription, newstypename,id, IpAddress);
		newsDAO.editNews(news);

		return "";
	}
	
	@RequestMapping(value="editNewsImage", method= RequestMethod.POST)
	public String editNewsImage(@RequestParam(value="image", required=false) MultipartFile[] image, int newsid, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit News Image Controller");
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "news");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/news/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/news/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/news/"+image[i].getOriginalFilename();
			            
			            newsImage = new NewsImage(imagesequence[i], imagename[i], image1, newsid, id, IpAddress);
			    		newsDAO.addNewsImage(newsImage);
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
	
	@RequestMapping(value="addNewsImageOld", method= RequestMethod.POST)
	public String addNewsImageOld(int newsid, int sequence, String imagename, String image, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Product Image Old Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		newsImage = new NewsImage(sequence, imagename, image, newsid, id, IpAddress);
		newsDAO.addNewsImage(newsImage);

		return "";
	}
	
	
	@RequestMapping(value="deleteNews", method= RequestMethod.DELETE)
	public void delete(int newsid)
	{
		logger.info("Inside Delete News Controller...");

		newsDAO.deleteNews(newsid);
	}
	
}
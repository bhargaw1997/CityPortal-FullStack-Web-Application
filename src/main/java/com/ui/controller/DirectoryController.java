package com.ui.controller;


import java.awt.AlphaComposite;
import java.awt.Color;
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

import com.ui.dao.DirectoryDAO;
import com.ui.model.Category;
import com.ui.model.Directory;
import com.ui.model.DirectoryImage;
import com.ui.model.Event;
import com.ui.model.Product;


@RestController
public class DirectoryController
{
	@Autowired
	DirectoryDAO directoryDAO;
	
	Directory directory;
	DirectoryImage directoryImage;


	private static final Logger logger = LoggerFactory.getLogger(DirectoryController.class);
	
	@RequestMapping(value="/getDirectories", method= RequestMethod.GET, produces = "application/json")
	public List<Directory> getDirectories(HttpServletRequest request)
	{
		logger.info("Inside Get All Directory Controller");
		
		List<Directory> directory = directoryDAO.getAllDirectories();
		
		return directory;
	}
	
	@RequestMapping(value="/getDirectoriesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Directory> getDirectoriesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Directory By Page Controller");
		
		List<Directory> directory = directoryDAO.getAllDirectoriesByPage(pagesize, startindex);
		
		return directory;
	}
	
	@RequestMapping(value="/getDirectoriesWithOneImageByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Directory> getDirectoriesWithOneImageByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Directory By Page Controller");
		
		List<Directory> directory = directoryDAO.getAllDirectoriesWithOneImageByPage(pagesize, startindex);
		
		return directory;
	}
	
	@RequestMapping(value="/getDirectoryByDirectoryId", method= RequestMethod.GET, produces = "application/json")
	public Directory getDirectoryByDirectoryId(int directoryid, HttpServletRequest request)
	{
		logger.info("Inside Get All Directory by id Controller");
		
		Directory directory = directoryDAO.getDirectoryByDirectoryId(directoryid);
		
		return directory;
	}
	
	@RequestMapping(value="/getDirectoryImageByDirectoryId", method= RequestMethod.GET, produces = "application/json")
	public List<DirectoryImage> getDirectoryImageByDirectoryId(int directoryid, HttpServletRequest request)
	{
		logger.info("Inside Get Directory Image By Directory Id Controller");
		
		List<DirectoryImage> directoryimage = directoryDAO.getDirectoryImageByDirectoryId(directoryid);
		
		return directoryimage;
	}
	
	@RequestMapping(value="/searchDirectories", method= RequestMethod.GET, produces = "application/json")
	public List<Directory> searchDirectories(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search Directories Controller");
		
		List<Directory> directory = directoryDAO.searchDirectories(keyword);
		
		return directory;
	}
	
	@RequestMapping(value="/getDirectorybySearch", method= RequestMethod.GET, produces = "application/json")
	public List<Directory> getDirectorybySearch(String keyword, HttpServletRequest request)
	{
		logger.info("Inside get Directories by search Controller");
		
		List<Directory> directory = directoryDAO.getDirectorybySearch(keyword);
		
		return directory;
	}
	
	@RequestMapping(value="addDirectory", method= RequestMethod.POST)
	public String addDirector(@RequestParam(value="bimage", required=false) MultipartFile bimage, int categoryname,int subcategoryname,int typename,String businessname,String address1, String address2, int areaname, String pincode, String mobilenumber1,String mobilenumber2,String landlinenumber,String keyword,String description,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Category Controller");
		
		String c = businessname.replace("$","&");
		String c1 = c.replace("~","#");
		String c2 = c1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		String bimage1 = "";

		try
		{
			if (bimage != null)
			{
				try
				{
					byte[] bytes =  bimage.getBytes();
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "directory");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/directory/");
			        File uploadfile = new File(path+File.separator+bimage.getOriginalFilename());
			            
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
			            
			        //bimage1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/directory/"+bimage.getOriginalFilename();
			        bimage1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/directory/"+bimage.getOriginalFilename();
			        
			        directory = new Directory(businessname,bimage1,address1, address2, areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description, categoryname,subcategoryname,typename,s, id, IpAddress);
					directoryDAO.addDirectory(directory);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					directory = new Directory(businessname,bimage1,address1, address2, areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description, categoryname,subcategoryname,typename,s, id, IpAddress);
					directoryDAO.addDirectory(directory);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			directory = new Directory(businessname,bimage1,address1, address2, areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description, categoryname,subcategoryname,typename,s, id, IpAddress);
			directoryDAO.addDirectory(directory);
			
			return "";
		}
		
		directory = new Directory(businessname,bimage1,address1, address2, areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description, categoryname,subcategoryname,typename,s, id, IpAddress);
		directoryDAO.addDirectory(directory);
		
		return "";
	}
	
	/*@RequestMapping(value="addDirectory", method= RequestMethod.POST)
	public String addDirectory(int categoryname,int subcategoryname,int typename,String businessname,String address1, String address2, int areaname, String pincode, String mobilenumber1,String mobilenumber2,String description,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Directory Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		directory = new Directory(businessname,address1, address2, areaname, pincode, mobilenumber1,mobilenumber2,description, categoryname,subcategoryname,typename,s, id, IpAddress);
		directoryDAO.addDirectory(directory);

		return "";
	}*/
	
	
	@RequestMapping(value="addDirectoryImage", method= RequestMethod.POST)
	public String addDirectoryImage(@RequestParam(value="image", required=false) MultipartFile[] image, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Directory Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		//String s = "y";
		
		int directoryid = directoryDAO.getLastDirectoryId();
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "directory");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/directory/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/directory/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/directory/"+image[i].getOriginalFilename();
			            
			            directoryImage = new DirectoryImage(imagesequence[i], imagename[i], image1, directoryid, id, IpAddress);
			    		directoryDAO.addDirectoryImage(directoryImage);
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
		
	@RequestMapping(value="/deleteDirectoryImage",method= RequestMethod.DELETE)
	public void deleteDirectoryImage(int imageid)
	{
		logger.info("Inside Delete Directory Image Controller");
		
		directoryDAO.deleteDirectoryImage(imageid);
	}
	
	@RequestMapping(value="editDirectory", method= RequestMethod.POST)
	public String editDirectory(@RequestParam(value="bimage", required=false) MultipartFile bimage, int directoryid, int categoryname,int subcategoryname,int typename, String businessname, String directoryimage,String address1, String address2, int areaname, String pincode, String mobilenumber1,String mobilenumber2,String landlinenumber,String keyword,String description,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Category Controller");
		
		String c = businessname.replace("$","&");
		String c1 = c.replace("~","#");
		String c2 = c1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String bimage1 = "";

		try
		{
			if (bimage != null)
			{
				try
				{
					byte[] bytes =  bimage.getBytes();
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "directory");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/directory/");
			        File uploadfile = new File(path+File.separator+bimage.getOriginalFilename());
			            
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
			            
			        //bimage1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/directory/"+bimage.getOriginalFilename();
			        bimage1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/directory/"+bimage.getOriginalFilename();
			        
			        directory = new Directory(directoryid,businessname,bimage1,address1, address2,  areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description,categoryname,subcategoryname,typename,id, IpAddress);
					directoryDAO.editDirectory(directory);

					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					directory = new Directory(directoryid,businessname,directoryimage,address1, address2,  areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description,categoryname,subcategoryname,typename,id, IpAddress);
					directoryDAO.editDirectory(directory);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			directory = new Directory(directoryid,businessname,directoryimage,address1, address2,  areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description,categoryname,subcategoryname,typename,id, IpAddress);
			directoryDAO.editDirectory(directory);
			
			return "";
		}
		
		directory = new Directory(directoryid,businessname,directoryimage,address1, address2,  areaname, pincode, mobilenumber1,mobilenumber2,landlinenumber,keyword,description,categoryname,subcategoryname,typename,id, IpAddress);
		directoryDAO.editDirectory(directory);
		
		return "";
	}
		
	/*@RequestMapping(value="editDirectory", method= RequestMethod.POST)
	public String editDirectory(int directoryid, int categoryname,int subcategoryname,int typename, String businessname,String address1, String address2, int areaname, String pincode, String mobilenumber1,String mobilenumber2,String description,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Directory Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		directory = new Directory(directoryid,businessname,address1, address2,  areaname, pincode, mobilenumber1,mobilenumber2,description,categoryname,subcategoryname,typename,id, IpAddress);
		directoryDAO.editDirectory(directory);

		return "";
	}*/
	
	@RequestMapping(value="editDirectoryImage", method= RequestMethod.POST)
	public String editDirectoryImage(@RequestParam(value="image", required=false) MultipartFile[] image, int directoryid, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Directory Image Controller");
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "directory");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/directory/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/directory/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/directory/"+image[i].getOriginalFilename();
			            
			            directoryImage = new DirectoryImage(imagesequence[i], imagename[i], image1, directoryid, id, IpAddress);
			    		directoryDAO.addDirectoryImage(directoryImage);
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

	@RequestMapping(value="deleteDirectory", method= RequestMethod.DELETE)
	public void delete(int directoryid)
	{
		logger.info("Inside Delete Directory Controller...");

		directoryDAO.deleteDirectory(directoryid);
	}
}
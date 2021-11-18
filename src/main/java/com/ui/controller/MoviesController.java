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

import com.ui.dao.MoviesDAO;
import com.ui.model.Movie;
import com.ui.model.Theatre;
import com.ui.model.TimeSlot;

@RestController
public class MoviesController
{
	@Autowired
	MoviesDAO movieDAO;
	
	Movie movie;
	TimeSlot timeSlot;

	private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);
	
	@RequestMapping(value="/getMovies", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> getMovies(HttpServletRequest request)
	{
		logger.info("Inside Get All Movie Controller");
		
		List<Movie> movie = movieDAO.getAllMovies();
		
		return movie;
	}
	
	@RequestMapping(value="/getMovieByMovieId", method= RequestMethod.GET, produces = "application/json")
	public Movie getMovieByMovieId(int movieid, HttpServletRequest request)
	{
		logger.info("Inside Get movie by id Controller");
		
		Movie movie = movieDAO.getMovieByMovieId(movieid);
		
		return movie;
	}
	
	@RequestMapping(value="/getLastSixMovies", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> getLastSixMovies(HttpServletRequest request)
	{
		logger.info("Inside Get last six Movie Controller");
		
		List<Movie> movie = movieDAO.getLastSixMovies();
		
		return movie;
	}
	
/*	@RequestMapping(value="/searchMovies", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> searchMovies(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search Movies Controller");
		
		List<Movie> movie = movieDAO.searchMovies(keyword);
		
		return movie;
	}*/
	@RequestMapping(value="/getMoviesByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> getMoviesByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Movie By Page Controller");
		
		List<Movie> movie = movieDAO.getAllMoviesByPage(pagesize, startindex);
		
		return movie;
	}
/*	
	@RequestMapping(value="/searchMovies", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> searchMovies(String keyword, HttpServletRequest request)
	{
		logger.info("Inside Search Movies Controller");
		
		List<Movie> movie = movieDAO.searchMovies(keyword);
		
		return movie;
	}*/
	
/*	@RequestMapping(value="/getMoviesForFrontEnd", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> getMoviesForFrontEnd(HttpServletRequest request)
	{
		logger.info("Inside Get All Movie For Front End Controller");
		
		List<Movie> movie = movieDAO.getAllMoviesForFrontEnd();
		
		return movie;
	}*/
	
	/*@RequestMapping(value="/getFeaturedMovie", method= RequestMethod.GET, produces = "application/json")
	public List<Movie> getFeaturedMovie(HttpServletRequest request)
	{
		logger.info("Inside Get Featured Movie Controller");
		
		List<Movie> movie = movieDAO.getFeaturedMovie();
		
		return movie;
	}*/
	
	@RequestMapping(value="/getTimeSlot", method= RequestMethod.GET, produces = "application/json")
	public List<TimeSlot> getTimeSlot(HttpServletRequest request)
	{
		logger.info("Inside Get All Time Slot Controller");
		
		List<TimeSlot> t = movieDAO.getAllTimeSlot();
		
		return t;
	}
	
	
	
	@RequestMapping(value="/getTimeSlotByMovieId", method= RequestMethod.GET, produces = "application/json")
	public List<TimeSlot> getTimeSlotByMovieId(int movieid, HttpServletRequest request)
	{
		logger.info("Inside Get Time Slot By Movie Id Controller");
		
		List<TimeSlot> timeslot = movieDAO.getTimeSlotByMovieId(movieid);
				
		return timeslot;
	}
	
	@RequestMapping(value="/getTheatreByMovieId", method= RequestMethod.GET, produces = "application/json")
	public List<Theatre> getTheatreByMovieId(int movieid, HttpServletRequest request)
	{
		logger.info("Inside Get Theatre By Movie Id Controller");
		
		List<Theatre> theatre = movieDAO.getTheatreByMovieId(movieid);
				
		return theatre;
	}
	
	@RequestMapping(value = "addTimeSlot", method = RequestMethod.POST)
	public String addTimeSlot(int screennumberid,String showtime, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Add TimeSlot Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int movieid = movieDAO.getLastMovieId();
		
		timeSlot = new TimeSlot(screennumberid,showtime, movieid, id, IpAddress);
		movieDAO.addTimeSlot(timeSlot);

		return "";
	}
	
	@RequestMapping(value="/deleteTimeSlot",method= RequestMethod.DELETE)
	public void deleteTimeSlot(int timeslotid)
	{
		logger.info("Inside Delete Time Slot Controller");
		
		movieDAO.deleteTimeSlot(timeslotid);
	}
	
	
	
	@RequestMapping(value="addMovie", method= RequestMethod.POST)
	public String addMovie(@RequestParam(value="image", required=false) MultipartFile image, String moviename, String releasedate,String movietrailer, String cbfc, String moviegenre,String movieduration, String movielanguage, String movieview,  String description, String rating, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Movie Controller");
		
		String c = moviename.replace("$","&");
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
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "movie");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/movie/");
			        File uploadfile = new File(path+File.separator+image.getOriginalFilename());
			            
			        /********* Today Start **********/
			            
			        int height=185, width=230;
			            
			        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        try
			        {
			        	BufferedImage img = ImageIO.read(in);
			        		
			        	int original_width = img.getWidth();
			        	int original_height = img.getHeight();
			        	int bound_width = 230;
			        	int bound_height = 185;
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
			            
			        //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/movie/"+image.getOriginalFilename();
			        image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/movie/"+image.getOriginalFilename();
			        
			        movie = new Movie(c2, releasedate, image1,rating, movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description,  s, id, IpAddress);
					movieDAO.addMovie(movie);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					movie = new Movie(c2, releasedate, image1,rating,movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description, s, id, IpAddress);
					movieDAO.addMovie(movie);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			movie = new Movie(c2, releasedate, image1,rating,movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description, s, id, IpAddress);
			movieDAO.addMovie(movie);
			
			return "";
		}
		
		movie = new Movie(c2, releasedate, image1, movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description, rating, s, id, IpAddress);
		movieDAO.addMovie(movie);
		
		return "";
	}
	
	@RequestMapping(value = "editTimeSlot", method = RequestMethod.POST)
	public String editTimeSlot(int movieid, int screennumberid,String showtime, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit Time Slot Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		timeSlot = new TimeSlot(screennumberid,showtime, movieid, id, IpAddress);
		movieDAO.addTimeSlot(timeSlot);

		return "";
	}
	
	@RequestMapping(value="editMovie", method= RequestMethod.POST)
	public String editMovie(@RequestParam(value="image", required=false) MultipartFile image, int movieid, String moviename, String releasedate,String movieimage, String rating,String movietrailer, String cbfc, String moviegenre,String movieduration, String movielanguage, String movieview, String description, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Movie Controller");
		
		String c = moviename.replace("$","&");
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
						
					File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "movie");
		    		if (!dir.exists()) 
		    			dir.mkdirs();
		    			
		    		String path = request.getRealPath("/resources/admin/img/movie/");
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
			            
			        //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/movie/"+image.getOriginalFilename();
			        image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/movie/"+image.getOriginalFilename();
			        
			        movie = new Movie(movieid, c2, releasedate, image1, rating,movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description, id, IpAddress);
					movieDAO.editMovie(movie);
					
					return "";
				}
				catch(Exception e)
				{
					e.printStackTrace();
					movie = new Movie(movieid, c2, releasedate, movieimage, rating,movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description, id, IpAddress);
					movieDAO.editMovie(movie);
					
					return "";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			movie = new Movie(movieid, c2, releasedate, movieimage, rating,movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview, description, id, IpAddress);
			movieDAO.editMovie(movie);
			
			return "";
		}
		
		movie = new Movie(movieid, c2, releasedate, movieimage, rating, movietrailer, cbfc, moviegenre, movieduration, movielanguage, movieview,description, id, IpAddress);
		movieDAO.editMovie(movie);
		
		return "";
	}
	
	@RequestMapping(value="deleteMovie", method= RequestMethod.DELETE)
	public void delete(int movieid)
	{
		logger.info("Inside Delete Movie Controller...");

		movieDAO.deleteMovie(movieid);
	}		
		

}
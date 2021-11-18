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

import com.ui.dao.EventDAO;
import com.ui.model.Event;
import com.ui.model.EventImage;
import com.ui.model.News;
import com.ui.model.EventAgenda;


@RestController
public class EventController
{
	@Autowired
	EventDAO eventDAO;
	
	Event event1;
	EventImage eventImage;

	EventAgenda eventAgenda;

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@RequestMapping(value="/getEvents", method= RequestMethod.GET, produces = "application/json")
	public List<Event> getEvents(HttpServletRequest request)
	{
		logger.info("Inside Get All Event Controller");
		
		List<Event> event = eventDAO.getAllEvents();
		
		return event;
	}
	@RequestMapping(value="/getEventByEventId", method= RequestMethod.GET, produces = "application/json")
	public Event getEventByEventId(int eventid, HttpServletRequest request)
	{
		logger.info("Inside Get All Events by id Controller");
		
		Event event = eventDAO.getEventByEventId(eventid);
		
		return event;
	}
	
	@RequestMapping(value="/getEventByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Event> getEventsByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Events By Page Controller");
		
		List<Event> event = eventDAO.getAllEventsByPage(pagesize, startindex);
		
		return event;
	}
	
	@RequestMapping(value="/getEventsWithOneImageByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Event> getEventsWithOneImageByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Events with Image By Page Controller");
		
		List<Event> event = eventDAO.getEventsWithOneImageByPage(pagesize, startindex);
		
		return event;
	}
	
	@RequestMapping(value="/getEventsWithOneImageByEventId", method= RequestMethod.GET, produces = "application/json")
	public List<Event> getEventsWithOneImageByEventId(int eventid, HttpServletRequest request)
	{
		logger.info("Inside Get Event By Event Id Controller");
		
		List<Event> event = eventDAO.getEventsWithOneImageByEventId(eventid);
		
		return event;
	}
	
	@RequestMapping(value="/getEventImageByEventId", method= RequestMethod.GET, produces = "application/json")
	public List<EventImage> getEventImageByEventId(int eventid, HttpServletRequest request)
	{
		logger.info("Inside Get Event Image By Event Id Controller");
		
		List<EventImage> eventimage = eventDAO.getEventImageByEventId(eventid);
		
		return eventimage;
	}
	
	@RequestMapping(value="/getEventAgenda", method= RequestMethod.GET, produces = "application/json")
	public List<EventAgenda> getEventAgenda(HttpServletRequest request)
	{
		logger.info("Inside Get All Event Agenda Controller");
		
		List<EventAgenda> t = eventDAO.getAllEventAgenda();
		
		return t;
	}
	
	@RequestMapping(value="/getEventAgendaByEventId", method= RequestMethod.GET, produces = "application/json")
	public List<EventAgenda> getEventAgendaByEventId(int eventid, HttpServletRequest request)
	{
		logger.info("Inside Get Event Agenda By Event Id Controller");
		
		List<EventAgenda> eventagenda = eventDAO.getEventAgendaByEventId(eventid);
		
				
		return eventagenda;
	}
	
	@RequestMapping(value="/getLastEightEventWithOneImage", method= RequestMethod.GET, produces = "application/json")
	public List<Event> getLastEightEventWithOneImage(HttpServletRequest request)
	{
		logger.info("Inside Get Last Eight event With One Image Controller");
		
		List<Event> event = eventDAO.getLastEightEventWithOneImage();
		
		return event;
	}
	@RequestMapping(value = "addEventAgenda", method = RequestMethod.POST)
	public String addEventAgenda(String eventagenda, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Add Event Agenda Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int eventid = eventDAO.getLastEventId();
		
		eventAgenda = new EventAgenda(eventagenda, eventid, id, IpAddress);
		eventDAO.addEventAgenda(eventAgenda);

		return "";
	}
	
	@RequestMapping(value="addEvent", method= RequestMethod.POST)
	public String addEvent(int organizername,String eventname, String eventvenue,String registrationfees,String eventstartdate,String eventenddate,String eventdescription,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Event Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		event1 = new Event(eventname,eventvenue,registrationfees,eventstartdate,eventenddate, eventdescription, organizername,s, id, IpAddress);
		eventDAO.addEvent(event1);

		return "";
	}
	
	
	@RequestMapping(value="addEventImage", method= RequestMethod.POST)
	public String addEventImage(@RequestParam(value="image", required=false) MultipartFile[] image, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Event Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		//String s = "y";
		
		int eventid = eventDAO.getLastEventId();
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "event");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/event/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/event/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/event/"+image[i].getOriginalFilename();
			            
			            eventImage = new EventImage(imagesequence[i], imagename[i], image1, eventid, id, IpAddress);
			    		eventDAO.addEventImage(eventImage);
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
		
	@RequestMapping(value="/deleteEventImage",method= RequestMethod.DELETE)
	public void deleteEventImage(int imageid)
	{
		logger.info("Inside Delete Event Image Controller");
		
		eventDAO.deleteEventImage(imageid);
	}
	
	@RequestMapping(value="/deleteEventAgenda",method= RequestMethod.DELETE)
	public void deleteEventAgenda(int eventagendaid)
	{
		logger.info("Inside Delete Event Agenda Controller");
		
		eventDAO.deleteEventAgenda(eventagendaid);
	}
		
	@RequestMapping(value="editEvent", method= RequestMethod.POST)
	public String editEvent(int eventid, int organizername, String eventname,String eventvenue,String registrationfees, String eventstartdate,String eventenddate, String eventdescription,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Event Controller");
		
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		event1 = new Event(eventid,eventname,eventvenue,registrationfees,eventstartdate,eventenddate,eventdescription,organizername,id, IpAddress);
		eventDAO.editEvent(event1);

		return "";
	}
	
	@RequestMapping(value="editEventImage", method= RequestMethod.POST)
	public String editEventImage(@RequestParam(value="image", required=false) MultipartFile[] image, int eventid, int imagesequence[], String imagename[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Event Image Controller");
		
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
						
						File dir = new File(request.getRealPath("")+"/resources/admin/img/" + File.separator + "event");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/img/event/");
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
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/cityportal/resources/admin/img/event/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/img/event/"+image[i].getOriginalFilename();
			            
			            eventImage = new EventImage(imagesequence[i], imagename[i], image1, eventid, id, IpAddress);
			    		eventDAO.addEventImage(eventImage);
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
	
	@RequestMapping(value = "editEventAgenda", method = RequestMethod.POST)
	public String editEventAgenda(int eventid, String eventagenda, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit Event Agenda Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		eventAgenda = new EventAgenda(eventagenda, eventid, id, IpAddress);
		eventDAO.addEventAgenda(eventAgenda);

		return "";
	}
	
	@RequestMapping(value="addEventImageOld", method= RequestMethod.POST)
	public String addEventImageOld(int eventid, int sequence, String imagename, String image, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Event Image Old Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		eventImage = new EventImage(sequence, imagename, image, eventid, id, IpAddress);
		eventDAO.addEventImage(eventImage);

		return "";
	}
	
	@RequestMapping(value="deleteEvent", method= RequestMethod.DELETE)
	public void delete(int eventid)
	{
		logger.info("Inside Delete Event Controller...");

		eventDAO.deleteEvent(eventid);
	}
}
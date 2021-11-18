package com.ui.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ui.dao.NewsDAO;
import com.ui.dao.EventDAO;
import com.ui.dao.DirectoryDAO;

@Controller
public class MainController {

	HttpSession ses;
	
	@Autowired
	NewsDAO newsDAO;
	
	@Autowired
	EventDAO eventDAO;
	
	@Autowired
	DirectoryDAO directoryDAO;
	
	/*private Facebook facebook;
	private ConnectionRepository connectionRepository;
	
	@Configuration
	public class SocialConfig implements SocialConfigurer {

	    @Override
	    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
	        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
	            env.getProperty("271387050410843"),
	            env.getProperty("24df1fd3e1aa37de1aa017eac1c01cc4")));
	    }

		@Override
		public UserIdSource getUserIdSource() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}
*/	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	/*@RequestMapping(value = "/postfb", method = RequestMethod.GET)
	public String postfb(Locale locale, Model model, HttpSession session, SitePreference sitePreference) {
		//System.out.println("Site Preference-----"+sitePreference);
		session.setAttribute("sitepreference", sitePreference);
		if(connectionRepository.getPrimaryConnection(Facebook.class)==null) {
			return "redirect:/connect/facebook";
		}
		facebook.feedOperations().updateStatus("Posting through Spring Application.. !!");
		return "postsuccess";
	}*/
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexfront(Locale locale, Model model, HttpSession session, SitePreference sitePreference) {
		model.addAttribute("news", newsDAO.getLastFourNewsWithOneImage());
		//System.out.println("Site Preference-----"+sitePreference);
		session.setAttribute("sitepreference", sitePreference);
		return "index";
	}
	
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String News(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "news";
	}
	
	@RequestMapping(value = "/news_detail", method = RequestMethod.GET)
	public String Newsdetail(int newsid,Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		model.addAttribute("newsimage", newsDAO.getNewsImageByNewsId(newsid));
		return "news_detail";
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String Event(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "event";
	}
	
	@RequestMapping(value = "/event_detail", method = RequestMethod.GET)
	public String Eventdetail(int eventid,Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		model.addAttribute("eventimage", eventDAO.getEventImageByEventId(eventid));
		return "event_detail";
	}
	
	@RequestMapping(value = "/cityguide", method = RequestMethod.GET)
	public String Cityguide(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "cityguide";
	}
	
	@RequestMapping(value = "/lifestyle", method = RequestMethod.GET)
	public String Lifestyle(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "lifestyle";
	}
	
	@RequestMapping(value = "/fashion", method = RequestMethod.GET)
	public String Fashion(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "fashion";
	}
	
	@RequestMapping(value = "/eatdrink", method = RequestMethod.GET)
	public String Eatdrink(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "eatdrink";
	}
	
	@RequestMapping(value = "/placetovisit", method = RequestMethod.GET)
	public String Placetovisit(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "placetovisit";
	}
	
	@RequestMapping(value = "/officialmatters", method = RequestMethod.GET)
	public String Officialmatters(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "officialmatters";
	}
	
	@RequestMapping(value = "/transportation", method = RequestMethod.GET)
	public String Transportation(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "transportation";
	}
	
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String Health(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "health";
	}
	
	@RequestMapping(value = "/helplines", method = RequestMethod.GET)
	public String Helplines(Locale locale, Model model, HttpSession session,SitePreference sitePreference) {
		session.setAttribute("sitepreference", sitePreference);
		return "helplines";
	}
	
	@RequestMapping(value = "/onlinestore", method = RequestMethod.GET)
	public String OnlineStore(Locale locale, Model model, HttpSession session) {
		
		return "onlinestore";
	}
	
	@RequestMapping(value = "/directory", method = RequestMethod.GET)
	public String Directory(Locale locale, Model model, HttpSession session) {
		
		return "directory";
	}
	
	@RequestMapping(value = "/directory_detail", method = RequestMethod.GET)
	public String Directorydetail(int directoryid,Locale locale, Model model, HttpSession session) {
		model.addAttribute("directoryimage", directoryDAO.getDirectoryImageByDirectoryId(directoryid));
		return "directory_detail";
	}
	
	@RequestMapping(value = "/business_directory", method = RequestMethod.GET)
	public String BusinessDirectory(Locale locale, Model model, HttpSession session) {
		
		return "business_directory";
	}
	
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public String movies(Locale locale, Model model, HttpSession session) {
		
		return "movies";
	}
	
	@RequestMapping(value = "/movie_detail", method = RequestMethod.GET)
	public String Moviedetail(int movieid,Locale locale, Model model, HttpSession session) {
		return "movie_detail";
	}
	
	@RequestMapping(value = "/movie_showlist"
			+ "", method = RequestMethod.GET)
	public String Theatredetail(int movieid,Locale locale, Model model, HttpSession session) {
		return "movie_showlist";
	}
	
	
	@RequestMapping(value = "/managecityportal", method = RequestMethod.GET)
	public String indexadmin(Locale locale, Model model, HttpSession session) {
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		
		return "admin/index";
	}

	@RequestMapping(value = "/managecityportal/home", method = RequestMethod.GET)
	public String adminhome(Locale locale, Model model, HttpSession session) {
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/home";
	}
	
	@RequestMapping(value = "/managecityportal/logoutadmin", method = RequestMethod.GET)
	public String logoutadmin(Locale locale, Model model, HttpSession session)
	{
		logger.info("Admin Logout");
		try
		{
			if(session.getAttribute("useridadmin")!=null || Integer.parseInt(session.getAttribute("useridadmin").toString()) != 0)
			{
				session.setAttribute("useridadmin", null);
				session.setAttribute("shownameadmin", null);
				session.setAttribute("usertypeidadmin", null);
				session.setAttribute("emailidadmin", null);
				session.setAttribute("mobilenumberadmin", null);
			    return "admin/index";
			}
		}
		catch(Exception e)
		{
			return "admin/index";
		}
		return "admin/index";
	}
	

	@RequestMapping(value = "/managecityportal/manage_country", method = RequestMethod.GET)
	public String manage_country(Locale locale, Model model, HttpSession session) {
		logger.info("Country Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_country";
	}

	@RequestMapping(value = "/managecityportal/manage_state", method = RequestMethod.GET)
	public String manage_state(Locale locale, Model model, HttpSession session) {
		logger.info("State Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_state";
	}

	@RequestMapping(value = "/managecityportal/manage_city", method = RequestMethod.GET)
	public String manage_city(Locale locale, Model model, HttpSession session) {
		logger.info("City Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_city";
	}

	@RequestMapping(value = "/managecityportal/manage_area", method = RequestMethod.GET)
	public String manage_area(Locale locale, Model model, HttpSession session) {
		logger.info("Area Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_area";
	}

	@RequestMapping(value = "/managecityportal/manage_user", method = RequestMethod.GET)
	public String manage_user(Locale locale, Model model, HttpSession session) {
		logger.info("User Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_user";
	}

	@RequestMapping(value = "/managecityportal/manage_newstype", method = RequestMethod.GET)
	public String manage_newstype(Locale locale, Model model, HttpSession session) {
		logger.info("NewsType Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_newstype";
	}

	@RequestMapping(value = "/managecityportal/manage_news", method = RequestMethod.GET)
	public String manage_news(Locale locale, Model model, HttpSession session) {
		logger.info("News Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_news";
	}

	@RequestMapping(value = "/managecityportal/manage_cityguide", method = RequestMethod.GET)
	public String manage_cityguide(Locale locale, Model model, HttpSession session) {
		logger.info("Cityguide Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_cityguide";
	}

	@RequestMapping(value = "/managecityportal/manage_category", method = RequestMethod.GET)
	public String manage_category(Locale locale, Model model, HttpSession session) {
		logger.info("Listing category Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_category";
	}

	@RequestMapping(value = "/managecityportal/manage_subcategory", method = RequestMethod.GET)
	public String manage_subcategory(Locale locale, Model model, HttpSession session) {
		logger.info("Listing subcategory Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_subcategory";
	}
	
	@RequestMapping(value = "/managecityportal/manage_type", method = RequestMethod.GET)
	public String manage_type(Locale locale, Model model, HttpSession session) {
		logger.info("Listing type Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_type";
	}
	
	@RequestMapping(value = "/managecityportal/manage_directory", method = RequestMethod.GET)
	public String manage_directory(Locale locale, Model model, HttpSession session) {
		logger.info("Listing directory Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_directory";
	}
	
	@RequestMapping(value = "/managecityportal/manage_productcategory", method = RequestMethod.GET)
	public String manage_productcategory(Locale locale, Model model, HttpSession session) {
		logger.info("Product Category Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_productcategory";
	}

	@RequestMapping(value = "/managecityportal/manage_productsubcategory", method = RequestMethod.GET)
	public String manage_productsubcategory(Locale locale, Model model, HttpSession session) {
		logger.info("Product Sub Category Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_productsubcategory";
	}

	@RequestMapping(value = "/managecityportal/manage_producttax", method = RequestMethod.GET)
	public String manage_producttax(Locale locale, Model model, HttpSession session) {
		logger.info("Product Tax Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_producttax";
	}

	@RequestMapping(value = "/managecityportal/manage_product", method = RequestMethod.GET)
	public String manage_product(Locale locale, Model model, HttpSession session) {
		logger.info("Product  Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_product";
	}

	@RequestMapping(value = "/managecityportal/manage_specification", method = RequestMethod.GET)
	public String manage_specification(Locale locale, Model model, HttpSession session) {
		logger.info("Specification Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_specification";
	}

	@RequestMapping(value = "/managecityportal/manage_amenities", method = RequestMethod.GET)
	public String manage_amenities(Locale locale, Model model, HttpSession session) {
		logger.info("Amenities Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_amenities";
	}

	@RequestMapping(value = "/managecityportal/manage_propertyspecification", method = RequestMethod.GET)
	public String manage_propertyspecification(Locale locale, Model model, HttpSession session) {
		logger.info("Specification Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_propertyspecification";
	}
	
	@RequestMapping(value = "/managecityportal/manage_property", method = RequestMethod.GET)
	public String manage_property(Locale locale, Model model, HttpSession session) {
		logger.info("Property Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_property";
	}

	@RequestMapping(value = "/managecityportal/manage_organizer", method = RequestMethod.GET)
	public String manage_organizer(Locale locale, Model model, HttpSession session) {
		logger.info("Organizer Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_organizer";
	}

	@RequestMapping(value = "/managecityportal/manage_event", method = RequestMethod.GET)
	public String manage_event(Locale locale, Model model, HttpSession session) {
		logger.info("Event Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_event";
	}
	
	@RequestMapping(value = "/managecityportal/manage_movies", method = RequestMethod.GET)
	public String manage_movies(Locale locale, Model model, HttpSession session) {
		logger.info("Movies Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_movies";
	}
	
	@RequestMapping(value = "/managecityportal/manage_theatre", method = RequestMethod.GET)
	public String manage_theatre(Locale locale, Model model, HttpSession session) {
		logger.info("Theatre Page");
		
		if(session.getAttribute("useridadmin")==null || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0)
		{
		    return "admin/index";
		}
		return "admin/manage_theatre";
	}
}

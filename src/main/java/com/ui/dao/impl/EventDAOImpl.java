package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.EventDAO;
import com.ui.model.Event;
import com.ui.model.EventImage;
import com.ui.model.EventAgenda;

public class EventDAOImpl implements EventDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);
	
	@Override
	public List<Event> getAllEvents()
	{
		logger.info("Inside Get All Event Impl");
		List<Event> sta = new ArrayList<Event>();
		String s = "y";
		String sql = "select e.event_id,e.event_name,e.event_venue, e.registration_fees,e.event_start_date,e.event_end_date, e.event_description, e.organizer_id,e.status, e.created_by, e.created_date, e.ip_address,o.organizer_name from event e, organizer_details o where e.organizer_id=o.organizer_id and e.status=? order by event_id desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Event event = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				event = new Event(
                rs.getInt("event_id"),
                rs.getString("event_name"),
                rs.getString("event_venue"),
                rs.getString("registration_fees"),
                rs.getString("event_start_date"),
                rs.getString("event_end_date"),
                rs.getString("event_description"),
                rs.getInt("organizer_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("organizer_name")
				);
				
				sta.add(event);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public Event getEventByEventId(int eventid)
	{
		logger.info("Inside Get All Event by id Impl");
		String s = "y";
		String sql = "select e.event_id,e.event_name,e.event_venue, e.registration_fees,e.event_start_date,e.event_end_date, e.event_description, e.organizer_id,e.status, e.created_by, e.created_date, e.ip_address,o.organizer_name from event e, organizer_details o where e.organizer_id=o.organizer_id and e.status=? and e.event_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, eventid);

			Event event = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				event = new Event(
                rs.getInt("event_id"),
                rs.getString("event_name"),
                rs.getString("event_venue"),
                rs.getString("registration_fees"),
                rs.getString("event_start_date"),
                rs.getString("event_end_date"),
                rs.getString("event_description"),
                rs.getInt("organizer_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("organizer_name")
				);
				
           }
           rs.close();
           ps.close();
          
           return event;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Event> getAllEventsByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Event By Page Impl");
		List<Event> sta = new ArrayList<Event>();
		String s = "y";
		String sql = "select e.event_id,e.event_name,e.event_venue, e.registration_fees,e.event_start_date,e.event_end_date, e.event_description, e.organizer_id,e.status, e.created_by, e.created_date, e.ip_address, o.organizer_name from event e, organizer_details o where e.organizer_id=o.organizer_id and e.status=? order by event_id desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Event event = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				event = new Event(
						rs.getInt("event_id"),
		                rs.getString("event_name"),
		                rs.getString("event_venue"),
		                rs.getString("registration_fees"),
		                rs.getString("event_start_date"),
		                rs.getString("event_end_date"),
		                rs.getString("event_description"),
		                rs.getInt("organizer_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("organizer_name")
				);
				
				sta.add(event);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Event> getEventsWithOneImageByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Event With one Image By Page Impl");
		List<Event> sta = new ArrayList<Event>();
		String s = "y";
		String sql = "select p.event_id, p.event_name,p.event_venue,p.registration_fees,p.event_start_date,p.event_end_date, p.event_description, p.organizer_id, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from event p left join event_image pi on p.event_id=pi.event_id where p.status=? group by pi.event_id order by p.event_start_date desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Event event = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				event = new Event(
						rs.getInt("event_id"),
		                rs.getString("event_name"),
		                rs.getString("event_venue"),
		                rs.getString("registration_fees"),
		                rs.getString("event_start_date"),
		                rs.getString("event_end_date"),
		                rs.getString("event_description"),
		                rs.getInt("organizer_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(event);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Event> getEventsWithOneImageByEventId(int eventid)
	{
		logger.info("Inside Get Event By Event Id Impl");
		List<Event> sta = new ArrayList<Event>();
		String s = "y";
		String sql = "select p.event_id, p.event_name,p.event_venue,p.registration_fees,p.event_start_date,p.event_end_date, p.event_description, p.organizer_id, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from event p left join event_image pi on p.event_id=pi.event_id where p.status=? and p.event_id=? group by pi.event_id order by p.event_start_date desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, s);
			ps.setInt(3, eventid);

			Event event = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				event = new Event(
						rs.getInt("event_id"),
		                rs.getString("event_name"),
		                rs.getString("event_venue"),
		                rs.getString("registration_fees"),
		                rs.getString("event_start_date"),
		                rs.getString("event_end_date"),
		                rs.getString("event_description"),
		                rs.getInt("organizer_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(event);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Event> getLastEightEventWithOneImage()
	{
		logger.info("Inside Get Last Eight Event With One Image Impl");
		List<Event> sta = new ArrayList<Event>();
		String s = "y";
		String sql = "select p.event_id, p.event_name,p.event_venue,p.registration_fees,p.event_start_date,p.event_end_date, p.event_description, p.organizer_id, p.status, p.created_by, p.created_date, p.ip_address,t.organizer_name, pi.image_name, pi.image from event p left join event_image pi on p.event_id=pi.event_id, organizer_details t where p.organizer_id=t.organizer_id and p.status=? group by p.event_name order by p.event_start_date desc limit 0,8";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Event event = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				event = new Event(
						rs.getInt("event_id"),
		                rs.getString("event_name"),
		                rs.getString("event_venue"),
		                rs.getString("registration_fees"),
		                rs.getString("event_start_date"),
		                rs.getString("event_end_date"),
		                rs.getString("event_description"),
		                rs.getInt("organizer_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("organizer_name"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(event);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<EventImage> getEventImageByEventId(int eventid)
	{
		logger.info("Inside Get Event Image By Event Id Impl");
		List<EventImage> sta = new ArrayList<EventImage>();
		String sql = "select event_image_id,sequence, image_name, image, event_id, created_by, created_date, ip_address from event_image where event_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, eventid);

			EventImage eventImage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				eventImage = new EventImage(
                rs.getInt("event_image_id"),
                rs.getInt("sequence"),
                rs.getString("image_name"),
                rs.getString("image"),
                rs.getInt("event_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(eventImage);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<EventAgenda> getAllEventAgenda()
	{
		logger.info("Inside Get All Event Agenda Impl");
		List<EventAgenda> sta = new ArrayList<EventAgenda>();
		String sql = "select event_agenda_id, event_agenda, created_by, created_date, ip_address from eventagenda order by event_agenda";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			

			EventAgenda eventagenda = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				eventagenda = new EventAgenda(
                rs.getInt("event_agenda_id"),
                rs.getString("event_agenda"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(eventagenda);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<EventAgenda> getEventAgendaByEventId(int eventid)
	{
		logger.info("Inside Get Event Agenda By Event Id Impl");
		List<EventAgenda> sta = new ArrayList<EventAgenda>();
		String sql = "select event_agenda_id, event_agenda, event_id, created_by, created_date, ip_address from eventagenda where event_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, eventid);

			EventAgenda eventAgenda = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				eventAgenda= new EventAgenda(
                rs.getInt("event_agenda_id"),
                rs.getString("event_agenda"),
                rs.getInt("event_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(eventAgenda);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
		
	@Override
	public void addEvent(Event p)
	{
		logger.info("Inside Add Event Impl");
		
		String sql = "insert into event (event_name,event_venue,registration_fees,event_start_date,event_end_date, event_description, organizer_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getEventName());
			ps.setString(2, p.getEventVenue());
			ps.setString(3, p.getRegistrationFees());
			ps.setString(4, p.getEventStartdate());
			ps.setString(5, p.getEventEnddate());
			ps.setString(6, p.getEventdescription());
			ps.setInt(7, p.getOrganizerId());
			ps.setString(8, p.getStatus());
			ps.setInt(9, p.getCreatedBy());
			ps.setString(10, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public int getLastEventId()
	{
		logger.info("Inside Get Last Event Id Impl");
		
		String sql = "select event_id from event order by event_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("event_id");
			}
            rs.close();
            ps.close();
            return id;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void addEventImage(EventImage p)
	{
		logger.info("Inside Add Event Image Impl");
		
		String sql = "insert into event_image (sequence, image_name, image, event_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getSequence());
			ps.setString(2, p.getImageName());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getEventId());
			ps.setInt(5, p.getCreatedBy());
			ps.setString(6, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void addEventAgenda(EventAgenda p)
	{
		logger.info("Inside Add Event Agenda Impl");
		
		String sql = "insert into eventagenda (event_agenda, event_id, created_by, ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getEventAgenda());
			ps.setInt(2, p.getEventId());
			ps.setInt(3, p.getCreatedBy());
			ps.setString(4, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	
	@Override
	public void deleteEventImage(int imageid)
	{
		logger.info("Inside Delete Event Image Impl");
		
		String sql = "delete from event_image where event_image_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, imageid);
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
		
	@Override
	public void deleteEventAgenda(int eventagendaid)
	{
		logger.info("Inside Delete Event Agenda Impl");
		
		String sql = "delete from eventagenda where event_agenda_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, eventagendaid);
			
			ps.executeUpdate();
		}
		
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void editEvent(Event p)
	{		
		logger.info("Inside Edit Event Impl");
		
		String sql = "update event set event_name=?,event_venue=?,registration_fees=?,event_start_date=?,event_end_date=?,event_description=?, organizer_id=?, created_by=?, ip_address=? where event_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getEventName());
			ps.setString(2, p.getEventVenue());
			ps.setString(3, p.getRegistrationFees());
			ps.setString(4, p.getEventStartdate());
			ps.setString(5, p.getEventEnddate());
			ps.setString(6, p.getEventdescription());
			ps.setInt(7, p.getOrganizerId());
			ps.setInt(8, p.getCreatedBy());
			ps.setString(9, p.getIpAddress());
			ps.setInt(10, p.getEventId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void deleteEvent(int eventid)
	{
		logger.info("Inside Delete Event Impl");
		
		String status="n";
		
		String sql = "update event set status=? where event_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, eventid);
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
}

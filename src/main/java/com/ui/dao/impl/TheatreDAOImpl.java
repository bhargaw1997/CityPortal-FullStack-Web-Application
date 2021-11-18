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

import com.ui.dao.TheatreDAO;
import com.ui.model.Theatre;
import com.ui.model.ScreenNumber;

public class TheatreDAOImpl implements TheatreDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(TheatreDAOImpl.class);
	
	@Override
	public List<Theatre> getAllTheatres()
	{
		logger.info("Inside Get All Theatre Impl");
		List<Theatre> sta = new ArrayList<Theatre>();
		String s = "y";
		String sql = "select e.theatre_id,e.theatre_name,e.theatre_address, e.theatre_description,e.status, e.created_by, e.created_date, e.ip_address from theatre e where e.status=? order by theatre_id desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Theatre theatre = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				theatre = new Theatre(
                rs.getInt("theatre_id"),
                rs.getString("theatre_name"),
                rs.getString("theatre_address"),
                rs.getString("theatre_description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(theatre);
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
	public List<Theatre> getAllTheatresByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Theatre By Page Impl");
		List<Theatre> sta = new ArrayList<Theatre>();
		String s = "y";
		String sql = "select e.theatre_id,e.theatre_name,e.theatre_address,e.theatre_description,e.status, e.created_by, e.created_date, e.ip_address  from theatre e  where e.status=? order by theatre_id desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Theatre theatre = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				theatre = new Theatre(
						rs.getInt("theatre_id"),
		                rs.getString("theatre_name"),
		                rs.getString("theatre_address"),
		                rs.getString("theatre_description"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(theatre);
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
	public List<ScreenNumber> getAllScreenNumber()
	{
		logger.info("Inside Get All Screen Number Impl");
		List<ScreenNumber> sta = new ArrayList<ScreenNumber>();
		String sql = "select theatre_screennumber_id, screen_number,no_of_seats, created_by, created_date, ip_address from screennumber order by screen_number";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			

			ScreenNumber screennumber = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				screennumber = new ScreenNumber(
                rs.getInt("theatre_screennumber_id"),
                rs.getString("screen_number"),
                rs.getString("no_of_seats"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(screennumber);
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
	public List<ScreenNumber> getScreenNumberByTheatreId(int theatreid)
	{
		logger.info("Inside Get Screeen Number By Theatre Id Impl");
		List<ScreenNumber> sta = new ArrayList<ScreenNumber>();
		String sql = "select theatre_screennumber_id, screen_number,no_of_seats, theatre_id, created_by, created_date, ip_address from screennumber where theatre_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, theatreid);

			ScreenNumber screenNumber = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				screenNumber= new ScreenNumber(
                rs.getInt("theatre_screennumber_id"),
                rs.getString("screen_number"),
                rs.getString("no_of_seats"),
                rs.getInt("theatre_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(screenNumber);
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
	public void addTheatre(Theatre p)
	{
		logger.info("Inside Add Theatre Impl");
		
		String sql = "insert into theatre (theatre_name,theatre_address,theatre_description, status, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTheatreName());
			ps.setString(2, p.getTheatreAddress());
			ps.setString(3, p.getTheatredescription());
			ps.setString(4, p.getStatus());
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
	public int getLastTheatreId()
	{
		logger.info("Inside Get Last Theatre Id Impl");
		
		String sql = "select theatre_id from theatre order by theatre_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("theatre_id");
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
	public void addScreenNumber(ScreenNumber p)
	{
		logger.info("Inside Add Screen Number Impl");
		
		String sql = "insert into screennumber (screen_number, no_of_seats, theatre_id, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getScreenNumber());
			ps.setString(2, p.getNoofSeats());
			ps.setInt(3, p.getTheatreId());
			ps.setInt(4, p.getCreatedBy());
			ps.setString(5, p.getIpAddress());
			
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
	public void deleteScreenNumber(int screennumberid)
	{
		logger.info("Inside Delete Screen Number Impl");
		
		String sql = "delete from screennumber where theatre_screennumber_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, screennumberid);
			
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
	public void editTheatre(Theatre p)
	{		
		logger.info("Inside Edit Theatre Impl");
		
		String sql = "update theatre set theatre_name=?,theatre_address=?,theatre_description=?, created_by=?, ip_address=? where theatre_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getTheatreName());
			ps.setString(2, p.getTheatreAddress());
			ps.setString(3, p.getTheatredescription());
			ps.setInt(4, p.getCreatedBy());
			ps.setString(5, p.getIpAddress());
			ps.setInt(6, p.getTheatreId());
			
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
	public void deleteTheatre(int theatreid)
	{
		logger.info("Inside Delete Theatre Impl");
		
		String status="n";
		
		String sql = "update theatre set status=? where theatre_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, theatreid);
			
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

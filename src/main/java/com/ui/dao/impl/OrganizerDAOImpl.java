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

import com.ui.dao.OrganizerDAO;
import com.ui.model.Organizer;

public class OrganizerDAOImpl implements OrganizerDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(OrganizerDAOImpl.class);
	
	@Override
	public List<Organizer> getAllOrganizers()
	{
		logger.info("Inside Get All Organizer Impl");
		List<Organizer> sta = new ArrayList<Organizer>();
		String s = "y";
		String sql = "select organizer_id, organizer_name, mobile_number, status, created_by, created_date, ip_address from organizer_details where status=? order by organizer_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Organizer organizer = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				organizer = new Organizer(
                rs.getInt("organizer_id"),
                rs.getString("organizer_name"),
                rs.getString("mobile_number"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(organizer);
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
	public List<Organizer> getAllOrganizersByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Organizer By Page Impl");
		List<Organizer> sta = new ArrayList<Organizer>();
		String s = "y";
		String sql = "select organizer_id, organizer_name, mobile_number, status, created_by, created_date, ip_address from organizer_details where status=? order by organizer_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Organizer organizer = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				organizer = new Organizer(
                rs.getInt("organizer_id"),
                rs.getString("organizer_name"),
                rs.getString("mobile_number"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(organizer);
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
	public void addOrganizer(Organizer c)
	{
		logger.info("Inside Add Organizer Impl");
		
		String sql = "insert into organizer_details (organizer_name, mobile_number, status, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getOrganizerName());
			ps.setString(2, c.getMobileNumber());
			ps.setString(3, c.getStatus());
			ps.setInt(4, c.getCreatedBy());
			ps.setString(5, c.getIpAddress());
			
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
	public void editOrganizer(Organizer c)
	{		
		logger.info("Inside Edit Organizer Impl");
		
		String sql = "update organizer_details set organizer_name=?, mobile_number=?, created_by=?, ip_address=? where organizer_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getOrganizerName());
			ps.setString(2, c.getMobileNumber());
			ps.setInt(3, c.getCreatedBy());
			ps.setString(4, c.getIpAddress());
			ps.setInt(5, c.getOrganizerId());
			
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
	public void deleteOrganizer(int organizerid)
	{
		logger.info("Inside Delete Organizer Impl");
		
		String status="n";
		
		String sql = "update organizer_details set status=? where organizer_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, organizerid);
			
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

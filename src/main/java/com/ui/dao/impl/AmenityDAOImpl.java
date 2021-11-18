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

import com.ui.dao.AmenityDAO;
import com.ui.model.Amenity;

public class AmenityDAOImpl implements AmenityDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(AmenityDAOImpl.class);
	
	@Override
	public List<Amenity> getAllAmenities()
	{
		logger.info("Inside Get All Amenity Impl");
		List<Amenity> sta = new ArrayList<Amenity>();
		String s = "y";
		String sql = "select amenities_id, amenities_name, status, created_by, created_date, ip_address from amenities where status=? order by amenities_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Amenity amenity = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				amenity = new Amenity(
                rs.getInt("amenities_id"),
                rs.getString("amenities_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(amenity);
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
	public List<Amenity> getAllAmenitiesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Amenity By Page Impl");
		List<Amenity> sta = new ArrayList<Amenity>();
		String s = "y";
		String sql = "select amenities_id, amenities_name, status, created_by, created_date, ip_address from amenities where status=? order by amenities_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Amenity amenity = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				amenity = new Amenity(
                rs.getInt("amenities_id"),
                rs.getString("amenities_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(amenity);
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
	public void addAmenity(Amenity c)
	{
		logger.info("Inside Add Amenity Impl");
		
		String sql = "insert into amenities (amenities_name,status, created_by, ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getAmenityName());
			ps.setString(2, c.getStatus());
			ps.setInt(3, c.getCreatedBy());
			ps.setString(4, c.getIpAddress());
			
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
	public void editAmenity(Amenity c)
	{		
		logger.info("Inside Edit Amenity Impl");
		
		String sql = "update amenities set amenities_name=?,created_by=?, ip_address=? where amenities_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getAmenityName());
			ps.setInt(2, c.getCreatedBy());
			ps.setString(3, c.getIpAddress());
			ps.setInt(4, c.getAmenityId());
			
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
	public void deleteAmenity(int amenityid)
	{
		logger.info("Inside Delete Amenity Impl");
		
		String status="n";
		
		String sql = "update amenities set status=? where amenities_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, amenityid);
			
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

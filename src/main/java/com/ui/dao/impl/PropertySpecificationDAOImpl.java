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

import com.ui.dao.PropertySpecificationDAO;
import com.ui.model.PropertySpecification;

public class PropertySpecificationDAOImpl implements PropertySpecificationDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(PropertySpecificationDAOImpl.class);
	
	@Override
	public List<PropertySpecification> getAllPropertySpecification()
	{
		logger.info("Inside Get All Specification Impl");
		List<PropertySpecification> sta = new ArrayList<PropertySpecification>();
		String s = "y";
		String sql = "select specification_id, specification_name, status, created_by, created_date, ip_address from property_specification where status=? order by specification_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			PropertySpecification propertyspecification = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				propertyspecification = new PropertySpecification(
                rs.getInt("specification_id"),
                rs.getString("specification_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(propertyspecification);
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
	public List<PropertySpecification> getAllPropertySpecificationByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Specification By Page Impl");
		List<PropertySpecification> sta = new ArrayList<PropertySpecification>();
		String s = "y";
		String sql = "select specification_id, specification_name, status, created_by, created_date, ip_address from property_specification where status=? order by specification_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			PropertySpecification propertyspecification = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				propertyspecification = new PropertySpecification(
                rs.getInt("specification_id"),
                rs.getString("specification_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(propertyspecification);
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
	public void addPropertySpecification(PropertySpecification c)
	{
		logger.info("Inside Add Specification Impl");
		
		String sql = "insert into property_specification (specification_name,status, created_by, ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getSpecificationName());
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
	public void editPropertySpecification(PropertySpecification c)
	{		
		logger.info("Inside Edit Specification Impl");
		
		String sql = "update property_specification set specification_name=?,created_by=?, ip_address=? where specification_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getSpecificationName());
			ps.setInt(2, c.getCreatedBy());
			ps.setString(3, c.getIpAddress());
			ps.setInt(4, c.getSpecificationId());
			
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
	public void deletePropertySpecification(int specificationid)
	{
		logger.info("Inside Delete Specification Impl");
		
		String status="n";
		
		String sql = "update property_specification set status=? where specification_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, specificationid);
			
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

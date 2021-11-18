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

import com.ui.dao.UserTypeDAO;
import com.ui.model.UserType;

public class UserTypeDAOImpl implements UserTypeDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(UserTypeDAOImpl.class);
	
	@Override
	public List<UserType> getAllUserTypes()
	{
		logger.info("Inside Get All User Types Impl");
		List<UserType> sta = new ArrayList<UserType>();
		String s = "y";
		String sql = "select user_type_id, user_type_name, status, created_by, created_date, ip_address from user_type where status=? order by user_type_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			UserType userType = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				userType = new UserType(
                rs.getInt("user_type_id"),
                rs.getString("user_type_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(userType);
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
	public List<UserType> getAllUserTypesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All User Types Impl");
		List<UserType> sta = new ArrayList<UserType>();
		String s = "y";
		String sql = "select user_type_id, user_type_name, status, created_by, created_date, ip_address from user_type where status=? order by user_type_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			UserType userType = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				userType = new UserType(
                rs.getInt("user_type_id"),
                rs.getString("user_type_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(userType);
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
	public void addUserType(UserType d)
	{
		logger.info("Inside Add User Type Impl");
		
		String sql = "insert into user_type (user_type_name, status, created_by, ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, d.getUserTypeName());
			ps.setString(2, d.getStatus());
			ps.setInt(3, d.getCreatedBy());
			ps.setString(4, d.getIpAddress());
			
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
	public void editUserType(UserType d)
	{		
		logger.info("Inside Edit User Type Impl");
		
		String sql = "update user_type set user_type_name=?, created_by=?, ip_address=? where user_type_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, d.getUserTypeName());
			ps.setInt(2, d.getCreatedBy());
			ps.setString(3, d.getIpAddress());
			ps.setInt(4, d.getUserTypeId());
			
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
	public void deleteUserType(int usertypeid)
	{
		logger.info("Inside Delete User Type Impl");
		
		String status="n";
		
		String sql = "update user_type set status=? where user_type_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, usertypeid);
			
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

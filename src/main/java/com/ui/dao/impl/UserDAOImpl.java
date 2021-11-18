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

import com.ui.dao.UserDAO;
import com.ui.model.User;

public class UserDAOImpl implements UserDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Override
	public List<User> getAllUsers()
	{
		logger.info("Inside Get All Users Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select u.user_id, u.first_name, u.middle_name, u.last_name, u.address1, u.address2,  u.area_id, u.pincode, u.mobile_number1,u.mobile_number2 ,u.email, u.password, u.user_type_id, u.status, u.created_by, u.created_date, u.ip_address, ut.user_type_name from user u, user_type ut where u.user_type_id=ut.user_type_id and u.status=? order by u.first_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			User user = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				user = new User(
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("middle_name"),
                rs.getString("last_name"),
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getInt("area_id"),
                rs.getString("pincode"),
                rs.getString("mobile_number1"),
                rs.getString("mobile_number2"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("user_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("user_type_name")
				);
				
				sta.add(user);
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
	public List<User> getAllUsersByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All User By Page Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select u.user_id, u.first_name, u.middle_name, u.last_name, u.address1, u.address2, u.area_id, u.pincode, u.mobile_number1,u.mobile_number2, u.email, u.password, u.user_type_id, u.status, u.created_by, u.created_date, u.ip_address, ut.user_type_name from user u, user_type ut where u.user_type_id=ut.user_type_id and u.status=? order by u.first_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			User user = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				user = new User(
						rs.getInt("user_id"),
		                rs.getString("first_name"),
		                rs.getString("middle_name"),
		                rs.getString("last_name"),
		                rs.getString("address1"),
		                rs.getString("address2"),
		                rs.getInt("area_id"),
		                rs.getString("pincode"),
		                rs.getString("mobile_number1"),
		                rs.getString("mobile_number2"),
		                rs.getString("email"),
		                rs.getString("password"),
		                rs.getInt("user_type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("user_type_name")
				);
				
				sta.add(user);
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
	public List<User> getUserByUserTypeId(int usertypeid)
	{
		logger.info("Inside Get User By User Type Id Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select u.user_id, u.first_name, u.middle_name, u.last_name, u.address1, u.address2,u.area_id, u.pincode, u.mobile_number1,u.mobile_number2, u.email, u.password, u.user_type_id, u.status, u.created_by, u.created_date, u.ip_address, ut.user_type_name from user u, user_type ut where u.user_type_id=ut.user_type_id and u.status=? and u.user_type_id=? order by u.first_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, usertypeid);

			User user = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				user = new User(
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("middle_name"),
                rs.getString("last_name"),
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getInt("area_id"),
                rs.getString("pincode"),
                rs.getString("mobile_number1"),
                rs.getString("mobile_number2"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("user_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("user_type_name")
				);
				
				sta.add(user);
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
	public List<User> getUserByAreaId(int areaid)
	{
		logger.info("Inside Get User By State Id Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select u.user_id, u.first_name, u.middle_name, u.last_name, u.address1, u.address2, u.area_id, u.pincode, u.mobile_number1,u.mobile_number2, u.email, u.password, u.user_type_id, u.status, u.created_by, u.created_date, u.ip_address, ut.user_type_name from user u, user_type ut where u.user_type_id=ut.user_type_id and u.status=? and u.area_id=? order by u.first_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, areaid);

			User user = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				user = new User(
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("middle_name"),
                rs.getString("last_name"),
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getInt("area_id"),
                rs.getString("pincode"),
                rs.getString("mobile_number1"),
                rs.getString("mobile_number2"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("user_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("user_type_name")
				);
				
				sta.add(user);
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
	public List<User> getUserByUserId(int userid)
	{
		logger.info("Inside Get User By User Id Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select u.user_id, u.first_name, u.middle_name, u.last_name, u.address1, u.address2,  u.area_id, u.pincode, u.mobile_number1,u.mobile_number2, u.email, u.password, u.user_type_id, u.status, u.created_by, u.created_date, u.ip_address, ut.user_type_name from user u, user_type ut where u.user_type_id=ut.user_type_id and u.status=? and u.user_id=? order by u.first_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, userid);

			User user = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				user = new User(
                rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("middle_name"),
                rs.getString("last_name"),
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getInt("area_id"),
                rs.getString("pincode"),
                rs.getString("mobile_number1"),
                rs.getString("mobile_number2"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("user_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("user_type_name")
				);
				
				sta.add(user);
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
	public void addUser(User u)
	{
		logger.info("Inside Add User Impl");
		
		String sql = "insert into user (first_name, middle_name, last_name, address1, address2, area_id, pincode, mobile_number1, mobile_number2, email, password, user_type_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getMiddleName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getAddress1());
			ps.setString(5, u.getAddress2());
			ps.setInt(6, u.getAreaId());
			ps.setString(7, u.getPincode());
			ps.setString(8, u.getMobileNumber1());
			ps.setString(9, u.getMobileNumber2());
			ps.setString(10, u.getEmail());
			ps.setString(11, u.getPassword());
			ps.setInt(12, u.getUserTypeId());
			ps.setString(13, u.getStatus());
			ps.setInt(14, u.getCreatedBy());
			ps.setString(15, u.getIpAddress());
			
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
	public void editUser(User u)
	{		
		logger.info("Inside Edit User Impl");
		
		String sql = "update user set first_name=?, middle_name=?, last_name=?, address1=?, address2=?, area_id=?, pincode=?, mobile_number1=?,mobile_number2=?, email=?, password=?, user_type_id=?, created_by=?, ip_address=? where user_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getMiddleName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getAddress1());
			ps.setString(5, u.getAddress2());
			ps.setInt(6, u.getAreaId());
			ps.setString(7, u.getPincode());
			ps.setString(8, u.getMobileNumber1());
			ps.setString(9, u.getMobileNumber2());
			ps.setString(10, u.getEmail());
			ps.setString(11, u.getPassword());
			ps.setInt(12, u.getUserTypeId());
			ps.setInt(13, u.getCreatedBy());
			ps.setString(14, u.getIpAddress());
			ps.setInt(15, u.getUserId());
			
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
	public void deleteUser(int userid)
	{
		logger.info("Inside Delete User Impl");
		
		String status="n";
		
		String sql = "update user set status=? where user_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, userid);
			
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

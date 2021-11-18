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

import com.ui.dao.TypeDAO;
import com.ui.model.Type;

public class TypeDAOImpl implements TypeDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(TypeDAOImpl.class);
	
	@Override
	public List<Type> getAllTypes()
	{
		logger.info("Inside Get All Types Impl");
		List<Type> sta = new ArrayList<Type>();
		String s = "y";
		String sql = "select type_id, type_name, type_code, image, description, status, created_by, created_date, ip_address from type where status=? order by type_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Type type = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				type = new Type(
                rs.getInt("type_id"),
                rs.getString("type_name"),
                rs.getString("type_code"),
                rs.getString("image"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(type);
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
	public List<Type> getAllTypesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Type By Page Impl");
		List<Type> sta = new ArrayList<Type>();
		String s = "y";
		String sql = "select type_id, type_name, type_code, image, description,status, created_by, created_date, ip_address from type where status=? order by type_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Type type = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				type = new Type(
                rs.getInt("type_id"),
                rs.getString("type_name"),
                rs.getString("type_code"),
                rs.getString("image"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(type);
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
	
/*	@Override
	public List<Category> searchCategories(String keyword)
	{
		logger.info("Inside Search Categories Impl");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, featured, status, created_by, created_date, ip_address from category where status=? and Concat(category_name, '', category_code) like ?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, '%'+keyword+'%');

			Category category = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				category = new Category(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("category_code"),
                rs.getString("image"),
                rs.getString("description"),
                rs.getString("featured"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(category);
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
	public List<Category> getAllCategoriesForFrontEnd()
	{
		logger.info("Inside Get All Categories For Front End Impl");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, featured, status, created_by, created_date, ip_address from category where status=? order by category_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Category category = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				category = new Category(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("category_code"),
                rs.getString("image"),
                rs.getString("description"),
                rs.getString("featured"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(category);
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
	}*/
	
/*	@Override
	public List<Category> getFeaturedCategory()
	{
		logger.info("Inside Get Featured Category Impl");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, featured, status, created_by, created_date, ip_address from category where featured=? and status=? order by category_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, s);

			Category category = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				category = new Category(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("category_code"),
                rs.getString("image"),
                rs.getString("description"),
                rs.getString("featured"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(category);
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
	}*/
	
	@Override
	public void addType(Type c)
	{
		logger.info("Inside Add Type Impl");
		
		String sql = "insert into type (type_name, type_code, image, description,status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getTypeName());
			ps.setString(2, c.getTypeCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getStatus());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getIpAddress());
			
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
	public void editType(Type c)
	{		
		logger.info("Inside Edit Type Impl");
		
		String sql = "update type set type_name=?, type_code=?, image=?, description=?, created_by=?, ip_address=? where type_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getTypeName());
			ps.setString(2, c.getTypeCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setInt(5, c.getCreatedBy());
			ps.setString(6, c.getIpAddress());
			ps.setInt(7, c.getTypeId());
			
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
	public void deleteType(int typeid)
	{
		logger.info("Inside Delete Type Impl");
		
		String status="n";
		
		String sql = "update type set status=? where type_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, typeid);
			
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

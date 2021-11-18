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

import com.ui.dao.CategoryDAO;
import com.ui.model.Category;

public class CategoryDAOImpl implements CategoryDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	@Override
	public List<Category> getAllCategories()
	{
		logger.info("Inside Get All Categories Impl");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, featured, status, created_by, created_date, ip_address from category where status=? order by category_name";
		
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
	}
	
	@Override
	public List<Category> getAllCategoriesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Category By Page Impl");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, featured, status, created_by, created_date, ip_address from category where status=? order by category_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

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
	public List<Category> searchCategories(String keyword)
	{
		logger.info("Inside Search Categories Impl");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name from category where status = ? and category_name like ? group by category_name ";
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
                rs.getString("category_name")
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
	
/*	@Override
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
	
	@Override
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
	}
	
	@Override
	public void addCategory(Category c)
	{
		logger.info("Inside Add Category Impl");
		
		String sql = "insert into category (category_name, category_code, image, description, featured, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getCategoryName());
			ps.setString(2, c.getCategoryCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getFeatured());
			ps.setString(6, c.getStatus());
			ps.setInt(7, c.getCreatedBy());
			ps.setString(8, c.getIpAddress());
			
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
	public void editCategory(Category c)
	{		
		logger.info("Inside Edit Category Impl");
		
		String sql = "update category set category_name=?, category_code=?, image=?, description=?, featured=?, created_by=?, ip_address=? where category_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getCategoryName());
			ps.setString(2, c.getCategoryCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getFeatured());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getIpAddress());
			ps.setInt(8, c.getCategoryId());
			
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
	public void deleteCategory(int categoryid)
	{
		logger.info("Inside Delete Category Impl");
		
		String status="n";
		
		String sql = "update category set status=? where category_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, categoryid);
			
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

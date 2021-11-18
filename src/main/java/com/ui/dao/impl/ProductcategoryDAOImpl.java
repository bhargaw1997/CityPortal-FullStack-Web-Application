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

import com.ui.dao.ProductcategoryDAO;
import com.ui.model.Productcategory;

public class ProductcategoryDAOImpl implements ProductcategoryDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(ProductcategoryDAOImpl.class);
	
	@Override
	public List<Productcategory> getAllProductcategories()
	{
		logger.info("Inside Get All Productcategory Impl");
		List<Productcategory> sta = new ArrayList<Productcategory>();
		String s = "y";
		String sql = "select category_id, category_name, status, created_by, created_date, ip_address from product_category where status=? order by category_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Productcategory productcategory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productcategory = new Productcategory(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(productcategory);
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
	public List<Productcategory> getAllProductcategoriesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Productcategory By Page Impl");
		List<Productcategory> sta = new ArrayList<Productcategory>();
		String s = "y";
		String sql = "select category_id, category_name, status, created_by, created_date, ip_address from product_category where status=? order by category_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Productcategory productcategory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productcategory = new Productcategory(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(productcategory);
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
	public void addProductcategory(Productcategory c)
	{
		logger.info("Inside Add Productcategory Impl");
		
		String sql = "insert into productcategory (category_name, status, created_by, ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getProductcategoryName());
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
	public void editProductcategory(Productcategory c)
	{		
		logger.info("Inside Edit Productcategory Impl");
		
		String sql = "update Productcategory set category_name=?, created_by=?, ip_address=? where category_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getProductcategoryName());
			ps.setInt(2, c.getCreatedBy());
			ps.setString(3, c.getIpAddress());
			ps.setInt(4, c.getProductcategoryId());
			
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
	public void deleteProductcategory(int productcategoryid)
	{
		logger.info("Inside Delete Productcategory Impl");
		
		String status="n";
		
		String sql = "update productcategory set status=? where category_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, productcategoryid);
			
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

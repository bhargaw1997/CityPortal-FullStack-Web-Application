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

import com.ui.dao.ProductsubcategoryDAO;
import com.ui.model.Productsubcategory;

public class ProductsubcategoryDAOImpl implements ProductsubcategoryDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(ProductsubcategoryDAOImpl.class);
	
	@Override
	public List<Productsubcategory> getAllProductsubcategories()
	{
		logger.info("Inside Get All Productsubcategory Impl");
		List<Productsubcategory> sta = new ArrayList<Productsubcategory>();
		String s = "y";
		String sql = "select s.subcategory_id, s.subcategory_name, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from product_subcategory s, product_category c where s.category_id=c.category_id and s.status=? order by s.subcategory_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Productsubcategory productsubcategory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productsubcategory = new Productsubcategory(
                rs.getInt("subcategory_id"),
                rs.getString("subcategory_name"),
                rs.getInt("category_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("category_name")
				);
				
				sta.add(productsubcategory);
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
	public List<Productsubcategory> getAllProductsubcategoriesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Productsubcategory Impl");
		List<Productsubcategory> sta = new ArrayList<Productsubcategory>();
		String s = "y";
		String sql = "select s.subcategory_id, s.subcategory_name, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from product_subcategory s, product_category c where s.category_id=c.category_id and s.status=? order by s.subcategory_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Productsubcategory productsubcategory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productsubcategory = new Productsubcategory(
                rs.getInt("subcategory_id"),
                rs.getString("subcategory_name"),
                rs.getInt("category_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("category_name")
				);
				
				sta.add(productsubcategory);
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
	public List<Productsubcategory> getProductsubcategoryByProductcategoryId(int productcategoryId)
	{
		logger.info("Inside Get Productsubcategory By Productcategory Id Impl");
		
		List<Productsubcategory> Productsubcategory = new ArrayList<Productsubcategory>();
		
		String s = "y";
		
		String sql = "select s.subcategory_id, s.subcategory_name, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from product_subcategory s, product_category c where s.category_id=c.category_id and s.status=? and s.category_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setLong(2, productcategoryId);

			Productsubcategory productsubcategory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productsubcategory = new Productsubcategory(
                rs.getInt("subcategory_id"),
                rs.getString("subcategory_name"),
                rs.getInt("category_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("category_name")
				);
				
				Productsubcategory.add(productsubcategory);
           }
           rs.close();
           ps.close();
          
           return Productsubcategory;
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
	public void addProductsubcategory(Productsubcategory s)
	{
		logger.info("Inside Add Productsubcategory Impl");
		
		String sql = "insert into product_subcategory (subcategory_name, category_id, status, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getProductsubcategoryName());
			ps.setInt(2, s.getProductcategoryId());
			ps.setString(3, s.getStatus());
			ps.setInt(4, s.getCreatedBy());
			ps.setString(5, s.getIpAddress());
			
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
	public void editProductsubcategory(Productsubcategory s)
	{		
		logger.info("Inside Edit Productsubcategory Impl");
		
		String sql = "update product_subcategory set subcategory_name=?, category_id=?, created_by=?, ip_address=? where subcategory_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s.getProductsubcategoryName());
			ps.setInt(2, s.getProductcategoryId());
			ps.setInt(3, s.getCreatedBy());
			ps.setString(4, s.getIpAddress());
			ps.setInt(5, s.getProductsubcategoryId());
			
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
	public void deleteProductsubcategory(int productsubcategoryid)
	{
		logger.info("Inside Delete Productsubcategory Impl");
		
		String status="n";
		
		String sql = "update product_subcategory set status=? where subcategory_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, productsubcategoryid);
			
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

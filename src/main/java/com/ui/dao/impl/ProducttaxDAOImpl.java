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

import com.ui.dao.ProducttaxDAO;
import com.ui.model.Producttax;

public class ProducttaxDAOImpl implements ProducttaxDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(ProducttaxDAOImpl.class);
	
	@Override
	public List<Producttax> getAllProducttaxes()
	{
		logger.info("Inside Get All Producttax Impl");
		List<Producttax> sta = new ArrayList<Producttax>();
		String s = "y";
		String sql = "select tax_id, tax_name, status, created_by, created_date, ip_address from product_tax where status=? order by tax_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Producttax producttax = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				producttax = new Producttax(
                rs.getInt("tax_id"),
                rs.getString("tax_name"),
				rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(producttax);
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
	public List<Producttax> getAllProducttaxesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Producttax By Page Impl");
		List<Producttax> sta = new ArrayList<Producttax>();
		String s = "y";
		String sql = "select tax_id, tax_name,  status, created_by, created_date, ip_address from product_tax where status=? order by tax_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Producttax producttax = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				producttax = new Producttax(
                rs.getInt("tax_id"),
                rs.getString("tax_name"),
				rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(producttax);
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
	public void addProducttax(Producttax c)
	{
		logger.info("Inside Add Producttax Impl");
		
		String sql = "insert into producttax (tax_name, status, created_by, ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getProducttaxName());
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
	public void editProducttax(Producttax c)
	{		
		logger.info("Inside Edit Producttax Impl");
		
		String sql = "update producttax set tax_name=?, created_by=?, ip_address=? where tax_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getProducttaxName());
			ps.setInt(2, c.getCreatedBy());
			ps.setString(3, c.getIpAddress());
			ps.setInt(4, c.getProducttaxId());
			
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
	public void deleteProducttax(int producttaxid)
	{
		logger.info("Inside Delete Producttax Impl");
		
		String status="n";
		
		String sql = "update producttax set status=? where tax_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, producttaxid);
			
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

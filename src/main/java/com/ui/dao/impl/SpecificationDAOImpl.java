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

import com.ui.dao.SpecificationDAO;
import com.ui.model.Specification;

public class SpecificationDAOImpl implements SpecificationDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(SpecificationDAOImpl.class);
	
	@Override
	public List<Specification> getAllSpecifications()
	{
		logger.info("Inside Get All Specification Impl");
		List<Specification> sta = new ArrayList<Specification>();
		String s = "y";
		String sql = "select a.specification_id, a.specification_name, a.subcategoey_id, a.categoey_id, a.status, a.created_by, a.created_date, a.ip_address, s.subcategory_name, c.category_id, c.category_name from product_specification a, producat_subcategory s, producat_category c where a.subcategoey_id=s.subcategoey_id and s.categoey_id=c.categoey_id and a.status=? order by a.specification_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Specification specification = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				specification = new Specification(
                rs.getInt("specification_id"),
                rs.getString("specification_name"),
                rs.getInt("subcategory_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("subcategory_name"),
                rs.getInt("category_id"),
                rs.getString("category_name")
				);
				
				sta.add(specification);
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
	public List<Specification> getAllSpecificationsByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Specification Impl");
		List<Specification> sta = new ArrayList<Specification>();
		String s = "y";
		String sql = "select a.specification_id, a.specification_name, a.subcategory_id, a.status, a.created_by, a.created_date, a.ip_address, s.subcategory_name, c.category_id, c.category_name from product_specification a, producat_subcategory s, producat_category c where a.subcategoey_id=s.subcategoey_id and s.categoey_id=c.categoey_id and a.status=? order by a.specification_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Specification specification = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				specification = new Specification(
							
								rs.getInt("specification_id"),
								rs.getString("specification_name"),
								rs.getInt("subcategory_id"),
								rs.getString("status"),
								rs.getInt("created_by"),
								rs.getString("created_date"),
								rs.getString("ip_address"),
								rs.getString("subcategory_name"),
								rs.getInt("category_id"),
								rs.getString("category_name")
								);
				
				sta.add(specification);
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
	public List<Specification> getSpecificationByProductsubcategoryId(int subcategoryId)
	{
		logger.info("Inside Get Specification By Subcategory Id Impl");
		
		List<Specification> Specification = new ArrayList<Specification>();
		
		String s = "y";
		
		String sql = "select a.specification_id, a.specification_name, a.subcategory_id, a.status, a.created_by, a.created_date, a.ip_address, s.subcategory_name, c.category_id, c.category_name from product_specification a, producat_subcategory s, producat_category c where a.subcategoey_id=s.subcategoey_id and s.categoey_id=c.categoey_id and a.status=? and a.state_id=? order by a.specification_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setLong(2, subcategoryId);

			Specification specification = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				specification = new Specification(
		                rs.getInt("specification_id"),
									rs.getString("specification_name"),
									rs.getInt("subcategory_id"),
									rs.getString("status"),
									rs.getInt("created_by"),
									rs.getString("created_date"),
									rs.getString("ip_address"),
									rs.getString("subcategory_name"),
									rs.getInt("category_id"),
									rs.getString("category_name")
									);
				
				Specification.add(specification);
           }
           rs.close();
           ps.close();
          
           return Specification;
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
	public void addSpecification(Specification c)
	{
		logger.info("Inside Add Specification Impl");
		
		String sql = "insert into product_specification (specification_name, subcategoey_id, categoey_id, status, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getSpecificationName());
			ps.setInt(2, c.getProductsubcategoryId());
			ps.setInt(3, c.getProductcategoryId());
			ps.setString(4, c.getStatus());
			ps.setInt(5, c.getCreatedBy());
			ps.setString(6, c.getIpAddress());
			
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
	public void editSpecification(Specification c)
	{		
		logger.info("Inside Edit Specification Impl");
		
		String sql = "update product_specification set specification_name=?, state_id=?, created_by=?, ip_address=? where specification_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getSpecificationName());
			ps.setInt(2, c.getProductsubcategoryId());
			ps.setInt(3, c.getCreatedBy());
			ps.setString(4, c.getIpAddress());
			ps.setInt(5, c.getSpecificationId());
			
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
	public void deleteSpecification(int specificationid)
	{
		logger.info("Inside Delete Specification Impl");
		
		String status="n";
		
		String sql = "update product_specificationset status=? where specification_id=?";
		
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

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

import com.ui.dao.NewsTypeDAO;
import com.ui.model.NewsType;

public class NewsTypeDAOImpl implements NewsTypeDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(OrganizerDAOImpl.class);
	
	@Override
	public List<NewsType> getAllNewsTypes()
	{
		logger.info("Inside Get All Newstype Impl");
		List<NewsType> sta = new ArrayList<NewsType>();
		String s = "y";
		String sql = "select news_type_id, news_type_name, news_description, status, created_by, created_date, ip_address from news_type where status=? order by news_type_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			NewsType newstype = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				newstype = new NewsType(
                rs.getInt("news_type_id"),
                rs.getString("news_type_name"),
                rs.getString("news_description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(newstype);
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
	public List<NewsType> getAllNewsTypesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All NewsType By Page Impl");
		List<NewsType> sta = new ArrayList<NewsType>();
		String s = "y";
		String sql = "select news_type_id, news_type_name, news_description, status, created_by, created_date, ip_address from news_type where status=? order by news_type_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			NewsType newstype = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				newstype = new NewsType(
                rs.getInt("news_type_id"),
                rs.getString("news_type_name"),
                rs.getString("news_description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(newstype);
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
	public void addNewsType(NewsType c)
	{
		logger.info("Inside Add NewsType Impl");
		
		String sql = "insert into news_type (news_type_name,news_description, status, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getNewsTypeName());
			ps.setString(2, c.getNewsDescription());
			ps.setString(3, c.getStatus());
			ps.setInt(4, c.getCreatedBy());
			ps.setString(5, c.getIpAddress());
			
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
	public void editNewsType(NewsType c)
	{		
		logger.info("Inside Edit NewsType Impl");
		
		String sql = "update news_type set news_type_name=?, news_description=?, created_by=?, ip_address=? where news_type_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getNewsTypeName());
			ps.setString(2, c.getNewsDescription());
			ps.setInt(3, c.getCreatedBy());
			ps.setString(4, c.getIpAddress());
			ps.setInt(5, c.getNewsTypeId());
			
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
	public void deleteNewsType(int newstypeid)
	{
		logger.info("Inside Delete Newstype Impl");
		
		String status="n";
		
		String sql = "update news_type set status=? where news_type_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, newstypeid);
			
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

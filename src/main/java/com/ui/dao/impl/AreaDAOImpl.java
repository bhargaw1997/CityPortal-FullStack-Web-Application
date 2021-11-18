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

import com.ui.dao.AreaDAO;
import com.ui.model.Area;

public class AreaDAOImpl implements AreaDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(AreaDAOImpl.class);
	
	@Override
	public List<Area> getAllAreas()
	{
		logger.info("Inside Get All Area Impl");
		List<Area> sta = new ArrayList<Area>();
		String s = "y";
		String sql = "select a.area_id, a.area_name, a.city_id, a.area_code, a.status, a.created_by, a.created_date, a.ip_address, ci.city_name, s.state_id, s.state_name, c.country_id, c.country_name from area a, city ci, state s, country c where a.city_id=ci.city_id and ci.state_id=s.state_id and s.country_id=c.country_id and a.status=? order by a.area_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Area area = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				area = new Area(
                rs.getInt("area_id"),
                rs.getString("area_name"),
                rs.getInt("city_id"),
                rs.getString("area_code"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("city_name"),
                rs.getInt("state_id"),
                rs.getString("state_name"),
                rs.getInt("country_id"),
                rs.getString("country_name")
				);
				
				sta.add(area);
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
	public List<Area> getAllAreasByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Area Impl");
		List<Area> sta = new ArrayList<Area>();
		String s = "y";
		String sql = "select a.area_id, a.area_name, a.city_id, a.area_code, a.status, a.created_by, a.created_date, a.ip_address, ci.city_name, s.state_id, s.state_name, c.country_id, c.country_name from area a, city ci, state s, country c where a.city_id=ci.city_id and ci.state_id=s.state_id and s.country_id=c.country_id and a.status=? order by a.area_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Area area = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				area = new Area(
		                rs.getInt("area_id"),
		                rs.getString("area_name"),
		                rs.getInt("city_id"),
		                rs.getString("area_code"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				sta.add(area);
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
	public List<Area> searchAreas(String keyword)
	{
		logger.info("Inside Search Area Impl");
		List<Area> sta = new ArrayList<Area>();
		String s = "y";
		String sql = "select a.area_id, a.area_name, a.city_id, a.area_code, a.status, a.created_by, a.created_date, a.ip_address, ci.city_name, s.state_id, s.state_name, c.country_id, c.country_name from area a, city ci, state s, country c where a.city_id=ci.city_id and ci.state_id=s.state_id and s.country_id=c.country_id and a.status=? and Concat(a.area_name, '', s.state_name, '', c.country_name) like ?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, '%'+keyword+'%');

			Area area = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				area = new Area(
		                rs.getInt("area_id"),
		                rs.getString("area_name"),
		                rs.getInt("city_id"),
		                rs.getString("area_code"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				sta.add(area);
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
	public List<Area> getAreaByCityId(int cityId)
	{
		logger.info("Inside Get Area By City Id Impl");
		
		List<Area> Area = new ArrayList<Area>();
		
		String s = "y";
		
		String sql = "select a.area_id, a.area_name, a.city_id, a.area_code, a.status, a.created_by, a.created_date, a.ip_address, ci.city_name, s.state_id, s.state_name, c.country_id, c.country_name from area a, city ci, state s, country c where a.city_id=ci.city_id and ci.state_id=s.state_id and s.country_id=c.country_id and a.status=? and a.city_id=? order by a.area_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setLong(2, cityId);

			Area area = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				area = new Area(
		                rs.getInt("area_id"),
		                rs.getString("area_name"),
		                rs.getInt("city_id"),
		                rs.getString("area_code"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				Area.add(area);
           }
           rs.close();
           ps.close();
          
           return Area;
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
	public List<Area> getAreaByAreaId(int areaId)
	{
		logger.info("Inside Get Area By Area Id Impl");
		
		List<Area> Area = new ArrayList<Area>();
		
		String s = "y";
		
		String sql = "select a.area_id, a.area_name, a.city_id, a.area_code, a.status, a.created_by, a.created_date, a.ip_address, ci.city_name, s.state_id, s.state_name, c.country_id, c.country_name from area a, city ci, state s, country c where a.city_id=ci.city_id and ci.state_id=s.state_id and s.country_id=c.country_id and a.status=? and a.area_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setLong(2, areaId);

			Area area = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				area = new Area(
		                rs.getInt("area_id"),
		                rs.getString("area_name"),
		                rs.getInt("city_id"),
		                rs.getString("area_code"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				Area.add(area);
           }
           rs.close();
           ps.close();
          
           return Area;
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
	public void addArea(Area a)
	{
		logger.info("Inside Add Area Impl");
		
		String sql = "insert into area (area_name, city_id, area_code, status, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, a.getAreaName());
			ps.setInt(2, a.getCityId());
			ps.setString(3, a.getAreaCode());
			ps.setString(4, a.getStatus());
			ps.setInt(5, a.getCreatedBy());
			ps.setString(6, a.getIpAddress());
			
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
	public void editArea(Area a)
	{		
		logger.info("Inside Edit Area Impl");
		
		String sql = "update area set area_name=?, city_id=?, area_code=?, created_by=?, ip_address=? where area_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, a.getAreaName());
			ps.setInt(2, a.getCityId());
			ps.setString(3, a.getAreaCode());
			ps.setInt(4, a.getCreatedBy());
			ps.setString(5, a.getIpAddress());
			ps.setInt(6, a.getAreaId());
			
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
	public void deleteArea(int areaid)
	{
		logger.info("Inside Delete Area Impl");
		
		String status="n";
		
		String sql = "update area set status=? where area_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, areaid);
			
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

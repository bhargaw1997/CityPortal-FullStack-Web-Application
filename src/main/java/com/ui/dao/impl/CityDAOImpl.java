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

import com.ui.dao.CityDAO;
import com.ui.model.City;

public class CityDAOImpl implements CityDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(CityDAOImpl.class);
	
	@Override
	public List<City> getAllCities()
	{
		logger.info("Inside Get All City Impl");
		List<City> sta = new ArrayList<City>();
		String s = "y";
		String sql = "select a.city_id, a.city_name, a.state_id, a.status, a.created_by, a.created_date, a.ip_address, s.state_name, c.country_id, c.country_name from city a, state s, country c where a.state_id=s.state_id and s.country_id=c.country_id and a.status=? order by a.city_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			City city = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				city = new City(
                rs.getInt("city_id"),
                rs.getString("city_name"),
                rs.getInt("state_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("state_name"),
                rs.getInt("country_id"),
                rs.getString("country_name")
				);
				
				sta.add(city);
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
	public List<City> getAllCitiesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All City Impl");
		List<City> sta = new ArrayList<City>();
		String s = "y";
		String sql = "select a.city_id, a.city_name, a.state_id, a.status, a.created_by, a.created_date, a.ip_address, s.state_name, c.country_id, c.country_name from city a, state s, country c where a.state_id=s.state_id and s.country_id=c.country_id and a.status=? order by a.city_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			City city = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				city = new City(
		                rs.getInt("city_id"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				sta.add(city);
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
	public List<City> searchCities(String keyword)
	{
		logger.info("Inside Search City Impl");
		List<City> sta = new ArrayList<City>();
		String s = "y";
		String sql = "select a.city_id, a.city_name, a.state_id, a.status, a.created_by, a.created_date, a.ip_address, s.state_name, c.country_id, c.country_name from city a, state s, country c where a.state_id=s.state_id and s.country_id=c.country_id and a.status=? and Concat(a.city_name, '', s.state_name, '', c.country_name) like ?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, '%'+keyword+'%');

			City city = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				city = new City(
		                rs.getInt("city_id"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				sta.add(city);
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
	public List<City> getCityByStateId(int stateId)
	{
		logger.info("Inside Get City By State Id Impl");
		
		List<City> City = new ArrayList<City>();
		
		String s = "y";
		
		String sql = "select a.city_id, a.city_name, a.state_id, a.status, a.created_by, a.created_date, a.ip_address, s.state_name, c.country_id, c.country_name from city a, state s, country c where a.state_id=s.state_id and s.country_id=c.country_id and a.status=? and a.state_id=? order by a.city_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setLong(2, stateId);

			City city = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				city = new City(
		                rs.getInt("city_id"),
		                rs.getString("city_name"),
		                rs.getInt("state_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("state_name"),
		                rs.getInt("country_id"),
		                rs.getString("country_name")
						);
				
				City.add(city);
           }
           rs.close();
           ps.close();
          
           return City;
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
	public void addCity(City c)
	{
		logger.info("Inside Add City Impl");
		
		String sql = "insert into city (city_name, state_id, status, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getCityName());
			ps.setInt(2, c.getStateId());
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
	public void editCity(City c)
	{		
		logger.info("Inside Edit City Impl");
		
		String sql = "update city set city_name=?, state_id=?, created_by=?, ip_address=? where city_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getCityName());
			ps.setInt(2, c.getStateId());
			ps.setInt(3, c.getCreatedBy());
			ps.setString(4, c.getIpAddress());
			ps.setInt(5, c.getCityId());
			
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
	public void deleteCity(int cityid)
	{
		logger.info("Inside Delete City Impl");
		
		String status="n";
		
		String sql = "update city set status=? where city_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, cityid);
			
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

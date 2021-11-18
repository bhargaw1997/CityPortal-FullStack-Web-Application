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

import com.ui.dao.CityguideDAO;
import com.ui.model.Cityguide;
import com.ui.model.CityguideImage;

public class CityguideDAOImpl implements CityguideDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(CityguideDAOImpl.class);
	
	@Override
	public List<Cityguide> getAllCityguides()
	{
		logger.info("Inside Get All Cityguides Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select cityguide_id, place_name, location_url, description, status, created_by, created_date, ip_address from cityguide where status=? order by place_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getAllCityguidesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Cityguides By Page Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select cityguide_id, place_name, location_url, description, status, created_by, created_date, ip_address from cityguide where status=? order by place_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesWithOneImageByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Cityguides By Page Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		//String sql = "select cityguide_id, place_name, location_url, description, status, created_by, created_date, ip_address from cityguide where status=? order by place_name limit ?,?";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? group by pi.cityguide_id order by p.place_name limit ?,?";

		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesLifestyle()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Lifestyle'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesFashion()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Fashion'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesEatdrink()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Eatdrink'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesPlacetovisit()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Placetovisit'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesOfficialmatters()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Officialmatters'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesTransportation()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Transportation'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesHealth()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Health'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<Cityguide> getCityguidesHelplines()
	{
		logger.info("Inside Get Cityguides By Cityguide Id Impl");
		List<Cityguide> sta = new ArrayList<Cityguide>();
		String s = "y";
		String sql = "select p.cityguide_id, p.place_name, p.location_url, p.description, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from cityguide p left join cityguide_image pi on p.cityguide_id=pi.cityguide_id where p.status=? and p.location_url='Helplines'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Cityguide cityguide = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguide = new Cityguide(
                rs.getInt("cityguide_id"),
                rs.getString("place_name"),
                rs.getString("location_url"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(cityguide);
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
	public List<CityguideImage> getCityguideImageByCityguideId(int cityguideid)
	{
		logger.info("Inside Get Cityguide Image By Cityguide Id Impl");
		List<CityguideImage> sta = new ArrayList<CityguideImage>();
		String sql = "select cityguide_image_id, sequence, image_name, image, cityguide_id, created_by, created_date, ip_address from cityguide_image where cityguide_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, cityguideid);

			CityguideImage cityguideImage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				cityguideImage = new CityguideImage(
                rs.getInt("cityguide_image_id"),
                rs.getInt("sequence"),
                rs.getString("image_name"),
                rs.getString("image"),
                rs.getInt("cityguide_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(cityguideImage);
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
	public void addCityguide(Cityguide p)
	{
		logger.info("Inside Add Cityguide Impl");
		
		String sql = "insert into cityguide (place_name, location_url, description, status, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getPlaceName());
			ps.setString(2, p.getLocationUrl());
			ps.setString(3, p.getDescription());
			ps.setString(4, p.getStatus());
			ps.setInt(5, p.getCreatedBy());
			ps.setString(6, p.getIpAddress());
			
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
	public int getLastCityguideId()
	{
		logger.info("Inside Get Last Cityguide Id Impl");
		
		String sql = "select cityguide_id from cityguide order by cityguide_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("cityguide_id");
			}
            rs.close();
            ps.close();
            return id;
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
	public void addCityguideImage(CityguideImage p)
	{
		logger.info("Inside Add Cityguide Image Impl");
		
		String sql = "insert into cityguide_image (sequence, image_name, image, cityguide_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getSequence());
			ps.setString(2, p.getImageName());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getCityguideId());
			ps.setInt(5, p.getCreatedBy());
			ps.setString(6, p.getIpAddress());
			
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
	public void deleteCityguideImage(int imageid)
	{
		logger.info("Inside Delete Cityguide Image Impl");
		
		String sql = "delete from cityguide_image where cityguide_image_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, imageid);
			
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
	public void editCityguide(Cityguide p)
	{		
		logger.info("Inside Edit Cityguide Impl");
		
		String sql = "update cityguide set place_name=?, location_url=?, description=?, created_by=?, ip_address=? where cityguide_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getPlaceName());
			ps.setString(2, p.getLocationUrl());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getCreatedBy());
			ps.setString(5, p.getIpAddress());
			ps.setInt(6, p.getCityguideId());
			
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
	public void deleteCityguide(int cityguideid)
	{
		logger.info("Inside Delete Cityguide Impl");
		
		String status="n";
		
		String sql = "update cityguide set status=? where cityguide_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, cityguideid);
			
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

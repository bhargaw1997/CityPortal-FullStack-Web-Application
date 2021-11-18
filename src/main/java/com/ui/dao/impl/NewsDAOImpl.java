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

import com.ui.dao.NewsDAO;
import com.ui.model.News;
import com.ui.model.NewsImage;

public class NewsDAOImpl implements NewsDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(NewsDAOImpl.class);
	
	@Override
	public List<News> getAllNews()
	{
		logger.info("Inside Get All News Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select n.news_id, n.news_title,n.news_subtitle,n.news_description, n.news_type_id,n.status, n.created_by, n.created_date, n.ip_address, t.news_type_name from news n, news_type t where n.news_type_id=t.news_type_id and n.status=? order by n.news_id desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
                rs.getInt("news_id"),
                rs.getString("news_title"),
                rs.getString("news_subtitle"),
                rs.getString("news_description"),
                rs.getInt("news_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("news_type_name")
				);
				
				sta.add(news);
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
	public News getNewsByNewsId(int newsid)
	{
		logger.info("Inside Get All News Impl");
		String s = "y";
		String sql = "select n.news_id,n.news_title,n.news_subtitle,n.news_description, n.news_type_id,n.status, n.created_by, n.created_date, n.ip_address, t.news_type_name from news n, news_type t where n.status=? and n.news_id=? ";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, newsid);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
                rs.getInt("news_id"),
                rs.getString("news_title"),
                rs.getString("news_subtitle"),
                rs.getString("news_description"),
                rs.getInt("news_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("news_type_name")
				);
				
           }
           rs.close();
           ps.close();
          
           return news;
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
	public List<News> getAllNewsByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All News By Page Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select n.news_id, n.news_title,n.news_subtitle,n.news_description, n.news_type_id,n.status, n.created_by, n.created_date, n.ip_address, t.news_type_name from news n, news_type t where n.news_type_id=t.news_type_id and n.status=? order by n.news_id desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
						rs.getInt("news_id"),
		                rs.getString("news_title"),
		                rs.getString("news_subtitle"),
		                rs.getString("news_description"),
		                rs.getInt("news_type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("news_type_name")
				);
				
				sta.add(news);
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
	public List<News> getAllNewsWithOneImageByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All News With One Image By Page Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select p.news_id, p.news_title,p.news_subtitle,p.news_description, p.news_type_id, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from news p left join news_image pi on p.news_id=pi.news_id where p.status=? group by pi.news_id order by p.news_id desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
						rs.getInt("news_id"),
		                rs.getString("news_title"),
		                rs.getString("news_subtitle"),
		                rs.getString("news_description"),
		                rs.getInt("news_type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(news);
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
	public List<News> getNewsWithOneImageByNewsId(int newsid)
	{
		logger.info("Inside Get News By News Id Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select p.news_id, p.news_title,p.news_subtitle,p.news_description, p.news_type_id, p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from news p left join news_image pi on p.news_id=pi.news_id where p.status=? and p.news_id=? group by pi.news_id order by p.news_title";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, newsid);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
						rs.getInt("news_id"),
		                rs.getString("news_title"),
		                rs.getString("news_subtitle"),
		                rs.getString("news_description"),
		                rs.getInt("news_type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(news);
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
	public List<NewsImage> getNewsImageByNewsId(int newsid)
	{
		logger.info("Inside Get News Image By News Id Impl");
		List<NewsImage> sta = new ArrayList<NewsImage>();
		String sql = "select news_image_id, sequence, image_name, image, news_id, created_by, created_date, ip_address from news_image where news_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, newsid);

			NewsImage newsImage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				newsImage = new NewsImage(
                rs.getInt("news_image_id"),
                rs.getInt("sequence"),
                rs.getString("image_name"),
                rs.getString("image"),
                rs.getInt("news_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(newsImage);
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
	public List<News> getLastFourNewsWithOneImage()
	{
		logger.info("Inside Get Last Four News With One Image Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select n.news_id, n.news_title,n.news_subtitle,n.news_description, n.news_type_id,n.status, n.created_by, n.created_date, n.ip_address, t.news_type_name, ni.image_name, ni.image from news n left join news_image ni on n.news_id=ni.news_id, news_type t where n.news_type_id=t.news_type_id and n.status=? and t.news_type_name='Vadodara News' group by n.news_title order by n.news_id desc limit 0,4";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
                rs.getInt("news_id"),
                rs.getString("news_title"),
                rs.getString("news_subtitle"),
                rs.getString("news_description"),
                rs.getInt("news_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("news_type_name"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(news);
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
	public List<News> getLastNewsWithOneImage()
	{
		logger.info("Inside Get Last News With One Image Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select n.news_id, n.news_title,n.news_subtitle,n.news_description, n.news_type_id,n.status, n.created_by, n.created_date, n.ip_address, t.news_type_name, ni.image_name, ni.image from news n left join news_image ni on n.news_id=ni.news_id, news_type t where n.news_type_id=t.news_type_id and n.status=? and t.news_type_name!='Vadodara News' group by n.news_title order by n.news_id desc limit 0,1";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				String imagename = "", image = "";
				
				if(rs.getString("image_name") != null)
					imagename = rs.getString("image_name");
				if(rs.getString("image") != null)
					image = rs.getString("image");
				
				news = new News(
                rs.getInt("news_id"),
                rs.getString("news_title"),
                rs.getString("news_subtitle"),
                rs.getString("news_description"),
                rs.getInt("news_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("news_type_name"),
                imagename,
                image
				);
				
				sta.add(news);
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
	public List<News> getSecondLastFourNewsWithOneImage()
	{
		logger.info("Inside Get Second Last Four News With One Image Impl");
		List<News> sta = new ArrayList<News>();
		String s = "y";
		String sql = "select n.news_id, n.news_title,n.news_subtitle,n.news_description, n.news_type_id,n.status, n.created_by, n.created_date, n.ip_address, t.news_type_name, ni.image_name, ni.image from news n left join news_image ni on n.news_id=ni.news_id, news_type t where n.news_type_id=t.news_type_id and n.status=? and t.news_type_name!='Vadodara News' group by n.news_title order by n.news_id desc limit 1,5";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			News news = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				news = new News(
                rs.getInt("news_id"),
                rs.getString("news_title"),
                rs.getString("news_subtitle"),
                rs.getString("news_description"),
                rs.getInt("news_type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("news_type_name"),
                rs.getString("image_name"),
                rs.getString("image")
				);
				
				sta.add(news);
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
	public void addNews(News p)
	{
		logger.info("Inside Add News Impl");
		
		String sql = "insert into news (news_title,news_subtitle, news_description, news_type_id, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getNewsTitle());
			ps.setString(2, p.getNewsSubtitle());
			ps.setString(3, p.getNewsdescription());
			ps.setInt(4, p.getNewsTypeId());
			ps.setString(5, p.getStatus());
			ps.setInt(6, p.getCreatedBy());
			ps.setString(7, p.getIpAddress());
			
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
	public int getLastNewsId()
	{
		logger.info("Inside Get Last News Id Impl");
		
		String sql = "select news_id from news order by news_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("news_id");
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
	public void addNewsImage(NewsImage p)
	{
		logger.info("Inside Add News Image Impl");
		
		String sql = "insert into news_image (sequence, image_name, image, news_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getSequence());
			ps.setString(2, p.getImageName());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getNewsId());
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
	public void deleteNewsImage(int imageid)
	{
		logger.info("Inside Delete News Image Impl");
		
		String sql = "delete from news_image where news_image_id=?";
		
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
	public void editNews(News p)
	{		
		logger.info("Inside Edit News Impl");
		
		String sql = "update news set news_title=?,news_subtitle=?, news_description=?, news_type_id=?, created_by=?, ip_address=? where news_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getNewsTitle());
			ps.setString(2, p.getNewsSubtitle());
			ps.setString(3, p.getNewsdescription());
			ps.setInt(4, p.getNewsTypeId());
			ps.setInt(5, p.getCreatedBy());
			ps.setString(6, p.getIpAddress());
			ps.setInt(7, p.getNewsId());
			
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
	public void deleteNews(int newsid)
	{
		logger.info("Inside Delete News Impl");
		
		String status="n";
		
		String sql = "update news set status=? where news_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, newsid);
			
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

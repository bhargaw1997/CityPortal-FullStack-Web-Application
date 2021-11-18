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

import com.ui.dao.MoviesDAO;
import com.ui.model.Movie;
import com.ui.model.Theatre;
import com.ui.model.TimeSlot;

public class MoviesDAOImpl implements MoviesDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(MoviesDAOImpl.class);
	
	@Override
	public List<Movie> getAllMovies()
	{
		logger.info("Inside Get All Movies Impl");
		List<Movie> sta = new ArrayList<Movie>();
		String s = "y";
		String sql = "select movie_id, movie_name,release_date,image,rating,movie_trailer, cbfc, movie_genre, movie_duration, movie_language, movie_view, description, status, created_by, created_date, ip_address from movies where status=?  order by release_date desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Movie movie = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				movie = new Movie(
                rs.getInt("movie_id"),
                rs.getString("movie_name"),
                rs.getString("release_date"),
                rs.getString("image"),
                rs.getString("rating"),
                rs.getString("movie_trailer"),
                rs.getString("cbfc"),
                rs.getString("movie_genre"),
                rs.getString("movie_duration"),
                rs.getString("movie_language"),
                rs.getString("movie_view"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(movie);
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
	public Movie getMovieByMovieId(int movieid)
	{
		logger.info("Inside movie by id Impl");
		String s = "y";
		String sql = "select movie_id, movie_name,release_date,image,rating,movie_trailer, cbfc, movie_genre, movie_duration, movie_language, movie_view, description, status, created_by, created_date, ip_address from movies where status=? and movie_id=?";			
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, movieid);

			Movie movie = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				movie = new Movie(
		                rs.getInt("movie_id"),
		                rs.getString("movie_name"),
		                rs.getString("release_date"),
		                rs.getString("image"),
		                rs.getString("rating"),
		                rs.getString("movie_trailer"),
		                rs.getString("cbfc"),
		                rs.getString("movie_genre"),
		                rs.getString("movie_duration"),
		                rs.getString("movie_language"),
		                rs.getString("movie_view"),
		                rs.getString("description"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
						);
				
           }
           rs.close();
           ps.close();
          
           return movie;
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
	public List<Movie> getAllMoviesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Movie By Page Impl");
		List<Movie> sta = new ArrayList<Movie>();
		String s = "y";
		String sql = "select movie_id, movie_name, release_date,image,rating,movie_trailer, cbfc, movie_genre, movie_duration, movie_language, movie_view, description, status, created_by, created_date, ip_address from movies where status=? order by release_date desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Movie movie = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				movie = new Movie(
						rs.getInt("movie_id"),
		                rs.getString("movie_name"),
		                rs.getString("release_date"), 
		                rs.getString("image"),
		                rs.getString("rating"),
		                rs.getString("movie_trailer"),
		                rs.getString("cbfc"),
		                rs.getString("movie_genre"),
		                rs.getString("movie_duration"),
		                rs.getString("movie_language"),
		                rs.getString("movie_view"), 
		                rs.getString("description"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(movie);
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
	public int getLastMovieId()
	{
		logger.info("Inside Get Last Movie Id Impl");
		
		String sql = "select movie_id from movies order by movie_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("movie_id");
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
	public List<Movie> getLastSixMovies()
	{
		logger.info("Inside Get last six Movies Impl");
		List<Movie> sta = new ArrayList<Movie>();
		String s = "y";
		String sql = "select movie_id, movie_name, release_date,image,rating, movie_trailer, cbfc, movie_genre, movie_duration, movie_language, movie_view, description,  status, created_by, created_date, ip_address from movies where status=?  order by release_date desc limit 0,6";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Movie movie = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				movie = new Movie(
                rs.getInt("movie_id"),
                rs.getString("movie_name"),
                rs.getString("release_date"),
                rs.getString("image"),
                rs.getString("rating"),
                rs.getString("movie_trailer"),
                rs.getString("cbfc"),
                rs.getString("movie_genre"),
                rs.getString("movie_duration"),
                rs.getString("movie_language"),
                rs.getString("movie_view"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(movie);
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
	public List<TimeSlot> getAllTimeSlot()
	{
		logger.info("Inside Get All TimeSlot Impl");
		List<TimeSlot> sta = new ArrayList<TimeSlot>();
		String sql = "select t.movie_timeslot_id ,t.theatre_screennumber_id,t.show_time, t.created_by, t.created_date, t.ip_address, s.screen_number,th.theatre_name from timeslot t,screennumber s,theatre th where t.theatre_screennumber_id=s.theatre_screennumber_id and s.theatre_id=th.theatre_id order by show_time desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			

			TimeSlot timeslot = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				timeslot = new TimeSlot(
                rs.getInt("movie_timeslot_id"),
                rs.getInt("theatre_screennumber_id"),
                rs.getString("show_time"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("screen_number"),
                rs.getString("theatre_name")
				);
				
				sta.add(timeslot);
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
	public List<TimeSlot> getTimeSlotByMovieId(int movieid)
	{
		logger.info("Inside Get TimeSlot By Movie Id Impl");
		List<TimeSlot> sta = new ArrayList<TimeSlot>();
		String sql = "select t.movie_timeslot_id ,t.theatre_screennumber_id,t.show_time,t.movie_id, t.created_by, t.created_date, t.ip_address, s.screen_number,th.theatre_name, th.theatre_id from timeslot t,screennumber s,theatre th where t.theatre_screennumber_id=s.theatre_screennumber_id and s.theatre_id=th.theatre_id and t.movie_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, movieid);

			TimeSlot timeSlot = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				timeSlot= new TimeSlot(
						rs.getInt("movie_timeslot_id"),
			            rs.getInt("theatre_screennumber_id"),
			            rs.getString("show_time"),
			            rs.getInt("movie_id"),
			            rs.getInt("created_by"),
			            rs.getString("created_date"),
			            rs.getString("ip_address"),
		                rs.getString("screen_number"),
		                rs.getString("theatre_name"),
		                rs.getInt("theatre_id")
				);
				
				sta.add(timeSlot);
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
	public List<Theatre> getTheatreByMovieId(int movieid)
	{
		logger.info("Inside Get Theatre By Movie Id Impl");
		List<Theatre> sta = new ArrayList<Theatre>();
		String sql = "select th.theatre_id ,th.theatre_name from timeslot t,screennumber s,theatre th where t.theatre_screennumber_id=s.theatre_screennumber_id and s.theatre_id=th.theatre_id and t.movie_id=? group by th.theatre_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, movieid);

			Theatre theatre = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				theatre= new Theatre(
						rs.getInt("theatre_id"),
		                rs.getString("theatre_name")
		              
				);
				
				sta.add(theatre);
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
	public void addTimeSlot(TimeSlot p)
	{
		logger.info("Inside Add Time Slot Impl");
		
		String sql = "insert into timeslot(theatre_screennumber_id, show_time, movie_id,  created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getScreenNumberId());
			ps.setString(2, p.getShowTime());
			ps.setInt(3, p.getMovieId());
			ps.setInt(4, p.getCreatedBy());
			ps.setString(5, p.getIpAddress());
			
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
	public void addMovie(Movie c)
	{
		logger.info("Inside Add Movie Impl");
		
		String sql = "insert into movies (movie_name, release_date,image, rating,movie_trailer, cbfc, movie_genre, movie_duration, movie_language, movie_view,  description, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getMovieName());
			ps.setString(2, c.getReleaseDate());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getRating());
			ps.setString(5, c.getMovieTrailer());
			ps.setString(6, c.getcbfc());
			ps.setString(7, c.getMovieGenre());
			ps.setString(8, c.getMovieDuration());
			ps.setString(9, c.getMovieLanguage());
			ps.setString(10, c.getMovieView());
			ps.setString(11, c.getDescription());
			ps.setString(12, c.getStatus());
			ps.setInt(13, c.getCreatedBy());
			ps.setString(14, c.getIpAddress());
			
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
	public void editMovie(Movie c)
	{		
		logger.info("Inside Edit Movie Impl");
		
		String sql = "update movies set movie_name=?, release_date=?,image=?, rating=?,movie_trailer=?, cbfc=?, movie_genre=?, movie_duration=?, movie_language=?, movie_view=?,  description=?, created_by=?, ip_address=? where movie_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getMovieName());
			ps.setString(2, c.getReleaseDate());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getRating());
			ps.setString(5, c.getMovieTrailer());
			ps.setString(6, c.getcbfc());
			ps.setString(7, c.getMovieGenre());
			ps.setString(8, c.getMovieDuration());
			ps.setString(9, c.getMovieLanguage());
			ps.setString(10, c.getMovieView());
			ps.setString(11, c.getDescription());
			ps.setInt(12, c.getCreatedBy());
			ps.setString(13, c.getIpAddress());
			ps.setInt(14, c.getMovieId());
			
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
	public void deleteTimeSlot(int timeslotid)
	{
		logger.info("Inside Delete TimeSlotImpl");
		
		String sql = "delete from timeslot where movie_timeslot_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, timeslotid);
			
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
	public void deleteMovie(int movieid)
	{
		logger.info("Inside Delete Movie Impl");
		
		String status="n";
		
		String sql = "update movies set status=? where movie_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, movieid);
			
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

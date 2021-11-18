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

import com.ui.dao.PropertyDAO;
import com.ui.model.Property;
import com.ui.model.PropertyImage;
import com.ui.model.Amenity;
import com.ui.model.Review;
import com.ui.model.PropertySpecification;

public class PropertyDAOImpl implements PropertyDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(PropertyDAOImpl.class);
	
	@Override
	public List<Property> getAllProperties()
	{
		logger.info("Inside Get All Properties Impl");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select property_id, property_name,property_condition,project_type,property_type,project_subtype,property_price,property_location,sequence, property_description, status, created_by, created_date, ip_address from property where status=? order by property_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Property property = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				property = new Property(
                rs.getInt("property_id"),
                rs.getString("property_name"),
                rs.getString("property_condition"),
                rs.getString("project_type"),
                rs.getString("property_type"),
                rs.getString("project_subtype"),
                rs.getFloat("property_price"),
                rs.getString("property_location"),
                rs.getInt("sequence"),
                rs.getString("property_description"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(property);
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
	public List<Property> getAllPropertiesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Properties By Page Impl");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select property_id, property_name,property_condition,project_type,property_type,project_subtype,property_price,property_location,sequence, property_description, status, created_by, created_date, ip_address from property where status=? order by property_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Property property = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				property = new Property(
						rs.getInt("property_id"),
		                rs.getString("property_name"),
		                rs.getString("property_condition"),
		                rs.getString("project_type"),
		                rs.getString("property_type"),
		                rs.getString("project_subtype"),
		                rs.getFloat("property_price"),
		                rs.getString("property_location"),
		                rs.getInt("sequence"),
		                rs.getString("property_description"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(property);
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
	public List<Property> getPropertiesWithOneImageByPropertyId(int propertyid)
	{
		logger.info("Inside Get Property By Property Id Impl");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select p.property_id, p.property_name,p.property_condition,p.project_type,p.property_type,p.project_subtype,p.property_price,p.property_location, p.sequence, p.property_description,p.status, p.created_by, p.created_date, p.ip_address, pi.image_name, pi.image from property p left join property_image pi on p.property_id=pi.property_id where p.status=? and p.property_id=? group by pi.property_id order by p.property_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, s);
			ps.setInt(3, propertyid);

			Property property = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				property = new Property(
						rs.getInt("property_id"),
		                rs.getString("property_name"),
		                rs.getString("property_condition"),
		                rs.getString("project_type"),
		                rs.getString("property_type"),
		                rs.getString("project_subtype"),
		                rs.getFloat("property_price"),
		                rs.getString("property_location"),
		                rs.getInt("sequence"),
		                rs.getString("property_description"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(property);
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
	public List<PropertyImage> getPropertyImageByPropertyId(int propertyid)
	{
		logger.info("Inside Get Property Image By Property Id Impl");
		List<PropertyImage> sta = new ArrayList<PropertyImage>();
		String sql = "select property_image_id, sequence, image_name, image, property_id, created_by, created_date, ip_address from property_image where property_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, propertyid);

			PropertyImage propertyImage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				propertyImage = new PropertyImage(
                rs.getInt("property_image_id"),
                rs.getInt("sequence"),
                rs.getString("image_name"),
                rs.getString("image"),
                rs.getInt("property_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(propertyImage);
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
	public List<Review> getReviewByPropertyId(int propertyid)
	{
		logger.info("Inside Get Review By Product Id Impl");
		List<Review> sta = new ArrayList<Review>();


		String sql = "select pt.review_id, pt.reviewer_name, pt.review, pt.property_id,pt.created_by, pt.created_date, pt.ip_address from reviews pt, property p where pt.property_id=p.property_id and pt.property_id=? order by pt.review_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, propertyid);

			Review review = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				review = new Review(
                rs.getInt("review_id"),
                rs.getString("reviewer_name"),
                rs.getString("review"),
                rs.getInt("property_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(review);
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
	public List<Amenity> getPropertyAmenitiesByPropertyId(int propertyid)
	{
		logger.info("Inside Get Amenities By Property Id");
		List<Amenity> sta = new ArrayList<Amenity>();

		String sql = "select pt.property_amenities_id, pt.amenities_id, pt.amenities_value, pt.property_id, pt.created_by, pt.created_date, pt.ip_address, t.amenities_name from property_amenities pt, property p, amenities t where pt.property_id=p.property_id and pt.amenities_id=t.amenities_id and pt.property_id=? group by pt.amenities_id order by pt.property_amenities_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, propertyid);

			Amenity amenity = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				amenity = new Amenity(
                rs.getInt("property_amenities_id"),
                rs.getInt("amenities_id"),
                rs.getString("amenities_value"),
                rs.getInt("property_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("amenities_name")
				);
				
				sta.add(amenity);
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
	public List<PropertySpecification> getPropertySpecification1ByPropertyId(int propertyid)
	{
		logger.info("Inside Get PropertySpecification By Property Id");
		List<PropertySpecification> sta = new ArrayList<PropertySpecification>();

		String sql = "select pt.property_specification1_id, pt.property_specification_id, pt.property_specification1_value, pt.property_id, pt.created_by, pt.created_date, pt.ip_address, t.property_specification_name from property_specification1 pt, property p, property_specification t where pt.property_id=p.property_id and pt.property_specification_id=t.property_specification_id and pt.property_id=? group by pt.property_specification_id order by pt.property_specification1_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, propertyid);

			PropertySpecification propertyspecification = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				propertyspecification = new PropertySpecification(
                rs.getInt("property_specification1_id"),
                rs.getInt("property_specification_id"),
                rs.getString("property_specification1_value"),
                rs.getInt("property_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("property_specification_name")
				);
				
				sta.add(propertyspecification);
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
	public void addProperty(Property p)
	{
		logger.info("Inside Add Property Impl");
		
		String sql = "insert into property (property_name,property_condition,project_type,property_type,project_subtype,property_price,property_location,sequence, project_description,status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getPropertyName());
			ps.setString(2, p.getPropertyCondition());
			ps.setString(3, p.getProjectType());
			ps.setString(4, p.getPropertyType());
			ps.setString(5, p.getProjectSubtype());
			ps.setFloat(6, p.getPropertyPrice());
			ps.setString(7, p.getPropertyLocation());
			ps.setInt(8, p.getSequence());
			ps.setString(9, p.getDescription());
			ps.setString(10, p.getStatus());
			ps.setInt(11, p.getCreatedBy());
			ps.setString(12, p.getIpAddress());
			
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
	public int getLastPropertyId()
	{
		logger.info("Inside Get Last Product Id Impl");
		
		String sql = "select property_id from property order by property_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("property_id");
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
	public void addPropertyImage(PropertyImage p)
	{
		logger.info("Inside Add Property Image Impl");
		
		String sql = "insert into property_image (sequence, image_name, image, property_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getSequence());
			ps.setString(2, p.getImageName());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getPropertyId());
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
	public void addReview(Review p)
	{
		logger.info("Inside Add Review Impl");
		
		String sql = "insert into reviews (review_id, reviewer_name,review, property_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getReviewId());
			ps.setString(2, p.getReviewerName());
			ps.setString(3, p.getReview());
			ps.setInt(4, p.getPropertyId());
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
	public void addPropertyAmenities(Amenity p)
	{
		logger.info("Inside Add Amenties Impl");
		
		String sql = "insert into property_amenities (amenities_id, amenities_value, property_id, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getAmenityId());
			ps.setString(2, p.getValue());
			ps.setInt(3, p.getPropertyId());
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
	public void addPropertySpecification1(PropertySpecification p)
	{
		logger.info("Inside Add PropertySpecification Impl");
		
		String sql = "insert into property_specification1 (property_specification_id, property_specification1_value, property_id, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getPropertySpecificationId());
			ps.setString(2, p.getValue());
			ps.setInt(3, p.getPropertyId());
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
	public void deletePropertyImage(int propertyid)
	{
		logger.info("Inside Delete Property Image Impl");
		
		String sql = "delete from property_image where property_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, propertyid);
			
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
	public void deleteAmenities(int propertyid)
	{
		logger.info("Inside Delete Amenties Impl");
		
		String sql = "delete from property_amenities where property_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, propertyid);
			
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
	public void deleteReview(int propertyid)
	{
		logger.info("Inside Delete Review Impl");
		
		String sql = "delete from reviews where property_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, propertyid);
			
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
	public void deletePropertySpecification1(int propertyid)
	{
		logger.info("Inside Delete Property Specification Impl");
		
		String sql = "delete from property_specification1 where property_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, propertyid);
			
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
	public void editProperty(Property p)
	{		
		logger.info("Inside Edit Property Impl");
		
		String sql = "update property set property_name=?,property_condition=?,project_type=?,property_type=?,project_subtype=?,property_price=?,property_location=?, project_description=?,created_by=?, ip_address=? where property_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getPropertyName());
			ps.setString(2, p.getPropertyCondition());
			ps.setString(3, p.getProjectType());
			ps.setString(4, p.getPropertyType());
			ps.setString(5, p.getProjectSubtype());
			ps.setFloat(6, p.getPropertyPrice());
			ps.setString(7, p.getPropertyLocation());
			ps.setString(8, p.getDescription());
			ps.setInt(9, p.getCreatedBy());
			ps.setString(10, p.getIpAddress());
			ps.setInt(11, p.getPropertyId());
			
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
	public void deleteProperty(int propertyid)
	{
		logger.info("Inside Delete Property Impl");
		
		String status="n";
		
		String sql = "update property set status=? where property_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, propertyid);
			
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

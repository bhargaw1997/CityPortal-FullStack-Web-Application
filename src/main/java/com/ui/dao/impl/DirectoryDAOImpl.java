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

import com.ui.dao.DirectoryDAO;
import com.ui.model.Directory;
import com.ui.model.DirectoryImage;

public class DirectoryDAOImpl implements DirectoryDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(DirectoryDAOImpl.class);
	
	@Override
	public List<Directory> getAllDirectories()
	{
		logger.info("Inside Get All Directory Impl");
		List<Directory> sta = new ArrayList<Directory>();
		String s = "y";
		String sql = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name from directory e, category o, subcategory a, type b where e.category_id=o.category_id and e.subcategory_id=a.subcategory_id and e.type_id=b.type_id and e.status=? order by business_name";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Directory directory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				directory = new Directory(
                rs.getInt("directory_id"),
                rs.getString("business_name"),  
                rs.getString("bimage"),                
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getInt("area_id"),
                rs.getString("pincode"),
                rs.getString("mobile_number1"),
                rs.getString("mobile_number2"),
                rs.getString("landline_number"),
                rs.getString("keyword"),
                rs.getString("description"),
                rs.getInt("category_id"),
                rs.getInt("subcategory_id"),
                rs.getInt("type_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("category_name"),
                rs.getString("subcategory_name"),
                rs.getString("type_name")
				);
				
				sta.add(directory);
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
	public Directory getDirectoryByDirectoryId(int directoryid)
	{
		logger.info("Inside Get All Directory by id Impl");
		String s = "y";
		String sql = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name,x.area_name from directory e, category o, subcategory a, type b,area x where e.category_id=o.category_id and e.area_id=x.area_id and e.subcategory_id=a.subcategory_id and e.type_id=b.type_id and e.status=? and e.directory_id=?";			
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, directoryid);

			Directory directory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				directory = new Directory(
		                rs.getInt("directory_id"),
		                rs.getString("business_name"),  
		                rs.getString("bimage"),                
		                rs.getString("address1"),
		                rs.getString("address2"),
		                rs.getInt("area_id"),
		                rs.getString("pincode"),
		                rs.getString("mobile_number1"),
		                rs.getString("mobile_number2"),
		                rs.getString("landline_number"),
		                rs.getString("keyword"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getInt("type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("category_name"),
		                rs.getString("subcategory_name"),
		                rs.getString("type_name"),
		                rs.getString("area_name")
						);
				
           }
           rs.close();
           ps.close();
          
           return directory;
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
	public List<Directory> searchDirectories(String keyword)
	{
		logger.info("Inside Search Directories Impl");
		List<Directory> sta = new ArrayList<Directory>();
		String s="y";
		String sql1 = "select directory_id, business_name,status from directory where status=? and business_name like ? group by business_name";
		String sql2 = "select directory_id, keyword,status from directory where status=? and keyword like ? group by keyword";

		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			ps1.setString(1, s);
			ps1.setString(2, '%'+keyword+'%');
			ps2.setString(1, s);
			ps2.setString(2, '%'+keyword+'%');

			Directory directory = null;
			
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			while (rs1.next())
			{
				directory = new Directory(
						rs1.getInt("directory_id"),
		                rs1.getString("business_name")
						);
				
				sta.add(directory);
			}
			while (rs2.next())
			{
				directory = new Directory(
						rs2.getInt("directory_id"),
		                rs2.getString("keyword")
						);
				
				sta.add(directory);
			}
			
           rs1.close();
           rs2.close();
           ps1.close();
           ps2.close();
          
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
	public List<Directory> getDirectorybySearch(String keyword)
	{
		logger.info("Inside get Directories by search Impl");
		String s= "y";
		List<Directory> sta = new ArrayList<Directory>();
		String sql1 = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name from directory e left join subcategory a on e.subcategory_id=a.subcategory_id, category o, type b where e.category_id=o.category_id and e.type_id=b.type_id and e.status=? and e.keyword like ? order by e.business_name";		
		String sql2 = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name from directory e left join subcategory a on e.subcategory_id=a.subcategory_id, category o, type b where e.category_id=o.category_id and e.type_id=b.type_id and e.status=? and e.business_name like ? order by e.business_name";		
		String sql3 = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name from directory e left join subcategory a on e.subcategory_id=a.subcategory_id, category o, type b where e.category_id=o.category_id and e.type_id=b.type_id and e.status=? and o.category_name like ? order by e.business_name";		

		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			
			ps1.setString(1, s);
			ps1.setString(2, '%'+keyword+'%');
			ps2.setString(1, s);
			ps2.setString(2, '%'+keyword+'%');
			ps3.setString(1, s);
			ps3.setString(2, '%'+keyword+'%');
			
			Directory directory = null;
			
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			ResultSet rs3 = ps3.executeQuery();
			
			while (rs1.next())
			{
				directory = new Directory(
	                rs1.getInt("directory_id"),
	                rs1.getString("business_name"),  
	                rs1.getString("bimage"),                
	                rs1.getString("address1"),
	                rs1.getString("address2"),
	                rs1.getInt("area_id"),
	                rs1.getString("pincode"),
	                rs1.getString("mobile_number1"),
	                rs1.getString("mobile_number2"),
	                rs1.getString("landline_number"),
	                rs1.getString("keyword"),
	                rs1.getString("description"),
	                rs1.getInt("category_id"),
	                rs1.getInt("subcategory_id"),
	                rs1.getInt("type_id"),
	                rs1.getString("status"),
	                rs1.getInt("created_by"),
	                rs1.getString("created_date"),
	                rs1.getString("ip_address"),
	                rs1.getString("category_name"),
	                rs1.getString("subcategory_name"),
	                rs1.getString("type_name")
				);
				
				sta.add(directory);
			}
			while (rs2.next())
			{
				directory = new Directory(
	                rs2.getInt("directory_id"),
					rs2.getString("business_name"),  
					rs2.getString("bimage"),                
					rs2.getString("address1"),
					rs2.getString("address2"),
					rs2.getInt("area_id"),
					rs2.getString("pincode"),
					rs2.getString("mobile_number1"),
					rs2.getString("mobile_number2"),
					rs2.getString("landline_number"),
					rs2.getString("keyword"),
					rs2.getString("description"),
					rs2.getInt("category_id"),
					rs2.getInt("subcategory_id"),
					rs2.getInt("type_id"),
					rs2.getString("status"),
					rs2.getInt("created_by"),
					rs2.getString("created_date"),
					rs2.getString("ip_address"),
					rs2.getString("category_name"),
					rs2.getString("subcategory_name"),
					rs2.getString("type_name")
				);
				
				sta.add(directory);
			}
			while (rs3.next())
			{
				directory = new Directory(
						rs3.getInt("directory_id"),
						rs3.getString("business_name"),  
						rs3.getString("bimage"),                
						rs3.getString("address1"),
						rs3.getString("address2"),
						rs3.getInt("area_id"),
						rs3.getString("pincode"),
						rs3.getString("mobile_number1"),
						rs3.getString("mobile_number2"),
						rs3.getString("landline_number"),
						rs3.getString("keyword"),
						rs3.getString("description"),
						rs3.getInt("category_id"),
						rs3.getInt("subcategory_id"),
						rs3.getInt("type_id"),
						rs3.getString("status"),
						rs3.getInt("created_by"),
						rs3.getString("created_date"),
						rs3.getString("ip_address"),
						rs3.getString("category_name"),
						rs3.getString("subcategory_name"),
						rs3.getString("type_name")
						);
				
				sta.add(directory);
			}
           rs1.close();
           rs2.close();
           rs3.close();
           ps1.close();
           ps2.close();
           ps3.close();
          
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
	public List<Directory> getAllDirectoriesByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Directory By Page Impl");
		List<Directory> sta = new ArrayList<Directory>();
		String s = "y";
		String sql = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword ,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name from directory e, category o, subcategory a, type b where e.category_id=o.category_id and e.subcategory_id=a.subcategory_id and e.type_id=b.type_id and e.status=? order by business_name limit ?,?";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Directory directory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				directory = new Directory(
		                rs.getInt("directory_id"),
		                rs.getString("business_name"),
		                rs.getString("bimage"),
		                rs.getString("address1"),
		                rs.getString("address2"),
		                rs.getInt("area_id"),
		                rs.getString("pincode"),
		                rs.getString("mobile_number1"),
		                rs.getString("mobile_number2"),
		                rs.getString("landline_number"),
		                rs.getString("keyword"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getInt("type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("category_name"),
		                rs.getString("subcategory_name"),
		                rs.getString("type_name")
				);
				
				sta.add(directory);
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
	public List<Directory> getAllDirectoriesWithOneImageByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Directory By Page Impl");
		List<Directory> sta = new ArrayList<Directory>();
		String s = "y";
		String sql = "select e.directory_id,e.business_name,e.bimage,e.address1, e.address2,e.area_id, e.pincode, e.mobile_number1,e.mobile_number2 ,e.landline_number,e.keyword ,e.description, e.category_id,e.subcategory_id,e.type_id,e.status, e.created_by, e.created_date, e.ip_address,o.category_name, a.subcategory_name,b.type_name,pi.image_name, pi.image from directory e left join directory_image pi on e.directory_id=pi.directory_id, category o, subcategory a, type b where e.category_id=o.category_id and e.subcategory_id=a.subcategory_id and e.type_id=b.type_id and e.status=? group by pi.directory_id order by e.business_name limit ?,?";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Directory directory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				directory = new Directory(
		                rs.getInt("directory_id"),
		                rs.getString("business_name"),
		                rs.getString("bimage"),
		                rs.getString("address1"),
		                rs.getString("address2"),
		                rs.getInt("area_id"),
		                rs.getString("pincode"),
		                rs.getString("mobile_number1"),
		                rs.getString("mobile_number2"),
		                rs.getString("landline_number"),
		                rs.getString("keyword"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getInt("type_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("category_name"),
		                rs.getString("subcategory_name"),
		                rs.getString("type_name"),
		                rs.getString("image_name"),
		                rs.getString("image")
				);
				
				sta.add(directory);
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
	public List<DirectoryImage> getDirectoryImageByDirectoryId(int directoryid)
	{
		logger.info("Inside Get directory Image By directory Id Impl");
		List<DirectoryImage> sta = new ArrayList<DirectoryImage>();
		String sql = "select directory_image_id,sequence, image_name, image, directory_id, created_by, created_date, ip_address from directory_image where directory_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, directoryid);

			DirectoryImage directoryImage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				directoryImage = new DirectoryImage(
                rs.getInt("directory_image_id"),
                rs.getInt("sequence"),
                rs.getString("image_name"),
                rs.getString("image"),
                rs.getInt("directory_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(directoryImage);
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
	public void addDirectory(Directory p)
	{
		logger.info("Inside Add directory Impl");
		
		String sql = "insert into directory (business_name, bimage, address1, address2, area_id, pincode, mobile_number1, mobile_number2,landline_number,keyword,description, category_id,subcategory_id,type_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getBusinessName());	
			ps.setString(2, p.getBimage());			
			ps.setString(3, p.getAddress1());
			ps.setString(4, p.getAddress2());
			ps.setInt(5, p.getAreaId());
			ps.setString(6, p.getPinCode());
			ps.setString(7, p.getMobileNumber1());
			ps.setString(8, p.getMobileNumber2());		
			ps.setString(9, p.getLandlineNumber());	
			ps.setString(10, p.getKeyword());	
			ps.setString(11, p.getDescription());
			ps.setInt(12, p.getCategoryId());
			ps.setInt(13, p.getSubcategoryId());
			ps.setInt(14, p.getTypeId());
			ps.setString(15, p.getStatus());
			ps.setInt(16, p.getCreatedBy());
			ps.setString(17, p.getIpAddress());
			
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
	public int getLastDirectoryId()
	{
		logger.info("Inside Get Last Directory Id Impl");
		
		String sql = "select directory_id from directory order by directory_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("directory_id");
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
	public void addDirectoryImage(DirectoryImage p)
	{
		logger.info("Inside Add Directory Image Impl");
		
		String sql = "insert into directory_image (sequence, image_name, image, directory_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getSequence());
			ps.setString(2, p.getImageName());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getDirectoryId());
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
	public void deleteDirectoryImage(int imageid)
	{
		logger.info("Inside Delete directory Image Impl");
		
		String sql = "delete from directory_image where directory_image_id=?";
		
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
	public void editDirectory(Directory p)
	{		
		logger.info("Inside Edit directory Impl");
		
		String sql = "update directory set business_name=?,bimage=? ,address1=?, address2=?, area_id=?, pincode=?, mobile_number1=?,mobile_number2=?,landline_number=?,keyword=?,description=?, category_id=?,subcategory_id=?,type_id=?, created_by=?, ip_address=? where directory_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getBusinessName());	
			ps.setString(2, p.getBimage());			
			ps.setString(3, p.getAddress1());
			ps.setString(4, p.getAddress2());
			ps.setInt(5, p.getAreaId());
			ps.setString(6, p.getPinCode());
			ps.setString(7, p.getMobileNumber1());
			ps.setString(8, p.getMobileNumber2());		
			ps.setString(9, p.getLandlineNumber());	
			ps.setString(10, p.getKeyword());	
			ps.setString(11, p.getDescription());
			ps.setInt(12, p.getCategoryId());
			ps.setInt(13, p.getSubcategoryId());
			ps.setInt(14, p.getTypeId());
			ps.setInt(15, p.getCreatedBy());
			ps.setString(16, p.getIpAddress());
			ps.setInt(17, p.getDirectoryId());
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
	public void deleteDirectory(int directoryid)
	{
		logger.info("Inside Delete Directory Impl");
		
		String status="n";
		
		String sql = "update directory set status=? where directory_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, directoryid);
			
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

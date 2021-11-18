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

import com.ui.dao.ProductDAO;
import com.ui.model.Product;
import com.ui.model.ProductImage;
import com.ui.model.Tax;
import com.ui.model.State;;

public class ProductDAOImpl implements ProductDAO
{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@Override
	public List<Product> getAllProducts()
	{
		logger.info("Inside Get All Products Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name, sequence, description, category_id, subcategory_id,  status, created_by, created_date, ip_address from product where status=? order by product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getInt("sequence"),
                rs.getString("description"),
                rs.getInt("category_id"),
                rs.getInt("subcategory_id"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(product);
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
	public List<Product> getAllProductsByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Products By Page Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name,  sequence, description, category_id, subcategory_id,  status, created_by, created_date, ip_address from product where status=? order by product_name limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(product);
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
	public List<Product> getProductsByCategoryId(int categoryid)
	{
		logger.info("Inside Get Products By Category Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name,  sequence, description, category_id, subcategory_id, status, created_by, created_date, ip_address from product where status=? and category_id=? order by product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, categoryid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(product);
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
	public List<Product> getProductsByCategoryIdAndSubcategoryId(int categoryid, int subcategoryid)
	{
		logger.info("Inside Get Products By Category Id And Subcategory Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name,  sequence, description, category_id, subcategory_id,  status, created_by, created_date, ip_address from product where status=? and category_id=? and subcategory_id=? order by product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, categoryid);
			ps.setInt(3, subcategoryid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(product);
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
	public List<Product> getProductsWithOneImageByCategoryId(int categoryid)
	{
		logger.info("Inside Get Products By Category Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select p.product_id, p.product_name, p.sequence, p.description, p.category_id, p.subcategory_id, p.status, p.created_by, p.created_date, p.ip_address, pi.image_title, pi.image from product p left join product_image pi on p.product_id=pi.product_id where p.product_id=pp.product_id p.status=? and p.category_id=? group by pi.product_id order by p.product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, categoryid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_title"),
		                rs.getString("image")
				);
				
				sta.add(product);
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
	public List<Product> getProductsWithOneImageBySubcategoryId(int subcategoryid)
	{
		logger.info("Inside Get Products By Subcategory Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select p.product_id, p.product_name, p.sequence, p.description, p.category_id, p.subcategory_id, p.active, p.status, p.created_by, p.created_date, p.ip_address, pi.image_title, pi.image from product p left join product_image pi on p.product_id=pi.product_id where p.product_id=pp.product_id p.status=? and p.subcategory_id=? group by pi.product_id order by p.product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, subcategoryid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_title"),
		                rs.getString("image")
				);
				
				sta.add(product);
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
	public List<Product> getProductsWithOneImageByProductId(int productid)
	{
		logger.info("Inside Get Products By Product Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select p.product_id, p.product_name,  p.sequence, p.description, p.category_id, p.subcategory_id, p.status, p.created_by, p.created_date, p.ip_address, pi.image_title, pi.image from product p left join product_image pi on p.product_id=pi.product_id where p.product_id=pp.product_id p.status=? and p.product_id=? group by pi.product_id order by p.product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, productid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address"),
		                rs.getString("image_title"),
		                rs.getString("image")
				);
				
				sta.add(product);
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
	public List<Product> getProductsByBrandId(int brandid)
	{
		logger.info("Inside Get Products By Brand Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name,  sequence, description, category_id, subcategory_id,  status, created_by, created_date, ip_address from product where status=? and brand_id=? order by product_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, brandid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(product);
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
	public List<ProductImage> getProductImageByProductId(int productid)
	{
		logger.info("Inside Get Product Image By Product Id Impl");
		List<ProductImage> sta = new ArrayList<ProductImage>();
		String sql = "select product_image_id, sequence, image_title, image, product_id, created_by, created_date, ip_address from product_image where product_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, productid);

			ProductImage productImage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productImage = new ProductImage(
                rs.getInt("product_image_id"),
                rs.getInt("sequence"),
                rs.getString("image_title"),
                rs.getString("image"),
                rs.getInt("product_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address")
				);
				
				sta.add(productImage);
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
	public List<Tax> getTaxByProductId(int productid)
	{
		logger.info("Inside Get Product Tax By Product Id Impl");
		List<Tax> sta = new ArrayList<Tax>();

		String sql = "select pt.product_tax_id, pt.tax_id, pt.rate, pt.product_id, pt.created_by, pt.created_date, pt.ip_address, t.tax_name from tax pt, product p, tax t where pt.product_id=p.product_id and pt.tax_id=t.tax_id and pt.product_id=? group by pt.tax_id order by pt.product_tax_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, productid);

			Tax productTax = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productTax = new Tax(
                rs.getInt("product_tax_id"),
                rs.getInt("tax_id"),
                rs.getFloat("rate"),
                rs.getInt("product_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("tax_name")
				);
				
				sta.add(productTax);
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
	public List<Tax> getTaxByProductIdAndStateId(int productid, int stateid)
	{
		logger.info("Inside Get Product Tax By Product Id And State Id Impl");
		List<Tax> sta = new ArrayList<Tax>();

		String sql = "select pt.product_tax_id, pt.tax_id, pt.rate, pt.product_id, pt.created_by, pt.created_date, pt.ip_address, t.tax_name from tax pt, product p, product_tax t where pt.product_id=p.product_id and pt.tax_id=t.tax_id and pt.state_id=? and pt.product_id=? group by pt.tax_id order by pt.product_tax_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, stateid);
			ps.setInt(2, productid);

			Tax productTax = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				productTax = new Tax(
                rs.getInt("product_tax_id"),
                rs.getInt("tax_id"),
                rs.getFloat("rate"),
                rs.getInt("product_id"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getString("tax_name")
				);
				
				sta.add(productTax);
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
	public void addProduct(Product p)
	{
		logger.info("Inside Add Product Impl");
		
		String sql = "insert into product (product_name, sku, sequence, description, category_id, subcategory_id,  active, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getProductName());
			ps.setInt(2, p.getSequence());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getCategoryId());
			ps.setInt(5, p.getSubcategoryId());
			ps.setString(7, p.getStatus());
			ps.setInt(8, p.getCreatedBy());
			ps.setString(9, p.getIpAddress());
			
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
	public int getLastProductId()
	{
		logger.info("Inside Get Last Product Id Impl");
		
		String sql = "select product_id from product order by product_id desc limit 0,1";		
		int id=0;
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("product_id");
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
	public void addProductImage(ProductImage p)
	{
		logger.info("Inside Add Product Image Impl");
		
		String sql = "insert into product_image (sequence, image_title, image, product_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getSequence());
			ps.setString(2, p.getImageTitle());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getProductId());
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
	public void addTax(Tax p)
	{
		logger.info("Inside Add Product Tax Impl");
		
		String sql = "insert into tax (tax_id, rate, product_id, created_by, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, p.getTaxId());
			ps.setFloat(2, p.getRate());
			ps.setInt(3, p.getProductId());
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
	public void deleteProductImage(int productid)
	{
		logger.info("Inside Delete Product Image Impl");
		
		String sql = "delete from product_image where product_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, productid);
			
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
	public void deleteTax(int productid)
	{
		logger.info("Inside Delete Product Tax Impl");
		
		String sql = "delete from tax where product_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setInt(1, productid);
			
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
	public void editProduct(Product p)
	{		
		logger.info("Inside Edit Product Impl");
		
		String sql = "update product set product_name=?, description=?, category_id=?, subcategory_id=?, created_by=?, ip_address=? where product_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getProductName());
			ps.setString(2, p.getDescription());
			ps.setInt(3, p.getCategoryId());
			ps.setInt(4, p.getSubcategoryId());
			ps.setInt(5, p.getCreatedBy());
			ps.setString(6, p.getIpAddress());
			ps.setInt(7, p.getProductId());
			
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
	public void deleteProduct(int productid)
	{
		logger.info("Inside Delete Product Impl");
		
		String status="n";
		
		String sql = "update product set status=? where product_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, productid);
			
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
	public List<Product> getLastProductByCategoryIdAndSubcategoryId(int categoryid, int subcategoryid)
	{
		logger.info("Inside Get Last Product By Category Id and Subcategory Id Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name, sequence, description, category_id, subcategory_id,  status, created_by, created_date, ip_address from product where status=? and category_id=? and subcategory_id=? order by product_id desc limit 0,1";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, categoryid);
			ps.setInt(3, subcategoryid);

			Product product = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				product = new Product(
						rs.getInt("product_id"),
		                rs.getString("product_name"),
		                rs.getInt("sequence"),
		                rs.getString("description"),
		                rs.getInt("category_id"),
		                rs.getInt("subcategory_id"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
				);
				
				sta.add(product);
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
	public List<State> getStatesByProductIdAndTaxId(int categoryId, int taxId)
	{
		logger.info("Inside Get State By Product Id And Tax Id Impl");
		List<State> sta = new ArrayList<State>();
		String s = "y";
		String sql = "select pt.*, s.state_name from tax pt, state s where pt.state_id = s.state_id and pt.product_id = ? and pt.tax_id = ? order by pt.product_tax_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, categoryId);
			ps.setInt(2, taxId);

			State state = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				state = new State(
                rs.getInt("state_id"),
                rs.getString("state_name")
				);
				
				sta.add(state);
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
	
	
}

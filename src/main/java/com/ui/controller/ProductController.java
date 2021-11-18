package com.ui.controller;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.ProductcategoryDAO;
import com.ui.dao.ProductDAO;
import com.ui.dao.ProductsubcategoryDAO;
import com.ui.model.Productcategory;
import com.ui.model.Product;
import com.ui.model.ProductImage;
import com.ui.model.Tax;
import com.ui.model.Productsubcategory;;

@RestController
public class ProductController
{
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ProductcategoryDAO categoryDAO;
	
	@Autowired
	ProductsubcategoryDAO subcategoryDAO;
	
	Product product;
	ProductImage productImage;
	Tax productTax;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="/getProducts", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProducts(HttpServletRequest request)
	{
		logger.info("Inside Get All Products Controller");
		
		List<Product> product = productDAO.getAllProducts();
		
		return product;
	}
	
	@RequestMapping(value="/getProductsByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get All Products By Page Controller");
		
		List<Product> product = productDAO.getAllProductsByPage(pagesize, startindex);
		
		return product;
	}
	
	@RequestMapping(value="/getProductsByCategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsByCategoryId(int categoryid, HttpServletRequest request)
	{
		logger.info("Inside Get Products By Category Id Controller");
		
		List<Product> product = productDAO.getProductsByCategoryId(categoryid);
		
		return product;
	}
	
	@RequestMapping(value="/getProductsByCategoryIdAndSubcategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsByCategoryIdAndSubcategoryId(int categoryid, int subcategoryid, HttpServletRequest request)
	{
		logger.info("Inside Get Products By Category Id And Subcategory Id Controller");
		
		List<Product> product = productDAO.getProductsByCategoryIdAndSubcategoryId(categoryid, subcategoryid);
		
		return product;
	}
	
	@RequestMapping(value="/getProductsWithOneImageByCategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsWithOneImageByCategoryId(int categoryid, HttpServletRequest request)
	{
		logger.info("Inside Get Products By Category Id Controller");
		
		List<Product> product = productDAO.getProductsWithOneImageByCategoryId(categoryid);
		
		return product;
	}
	
	@RequestMapping(value="/getProductsWithOneImageBySubcategoryId", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsWithOneImageBySubcategoryId(int subcategoryid, HttpServletRequest request)
	{
		logger.info("Inside Get Products By Subcategory Id Controller");
		
		List<Product> product = productDAO.getProductsWithOneImageBySubcategoryId(subcategoryid);
		
		return product;
	}
	
	@RequestMapping(value="/getProductsWithOneImageByProductId", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsWithOneImageByProductId(int productid, HttpServletRequest request)
	{
		logger.info("Inside Get Products By Product Id Controller");
		
		List<Product> product = productDAO.getProductsWithOneImageByProductId(productid);
		
		return product;
	}
	
	
	
	@RequestMapping(value="/getProductsByBrandId", method= RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsByBrandId(int brandid, HttpServletRequest request)
	{
		logger.info("Inside Get Products By Brand Id Controller");
		
		List<Product> product = productDAO.getProductsByBrandId(brandid);
		
		return product;
	}
	
	@RequestMapping(value="/getProductImageByProductId", method= RequestMethod.GET, produces = "application/json")
	public List<ProductImage> getProductImageByProductId(int productid, HttpServletRequest request)
	{
		logger.info("Inside Get Product Image By Product Id Controller");
		
		List<ProductImage> productimage = productDAO.getProductImageByProductId(productid);
		
		return productimage;
	}
	
	@RequestMapping(value="/getProductTaxByProductId", method= RequestMethod.GET, produces = "application/json")
	public List<Tax> getProductTaxByProductId(int productid, HttpServletRequest request)
	{
		logger.info("Inside Get Product Tax By Product Id Controller");
		
		List<Tax> producttax = productDAO.getTaxByProductId(productid);
		
		for (Tax pt : producttax)
		{
			pt.setStates(productDAO.getStatesByProductIdAndTaxId(productid, pt.getTaxId()));
		}
		
		return producttax;
	}
	
	@RequestMapping(value="/getProductTaxByProductIdAndStateId", method= RequestMethod.GET, produces = "application/json")
	public List<Tax> getProductTaxByProductIdAndStateId(int productid, int stateid, HttpServletRequest request)
	{
		logger.info("Inside Get Product Tax By Product Id And State Id Controller");
		
		List<Tax> ProductTax = productDAO.getTaxByProductIdAndStateId(productid, stateid);
		
		return ProductTax;
	}
	
	
	@RequestMapping(value="addProduct", method= RequestMethod.POST)
	public String addProduct(int categoryname, int subcategoryname, String productname, String sku, int sequence, String description, String featured, String active, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Product Controller");
		
		String p = productname.replace("$","&");
		String p1 = p.replace("~","#");
		String p2 = p1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		product = new Product(p2, sequence, description, categoryname, subcategoryname,  s, id, IpAddress);
		productDAO.addProduct(product);

		return "";
	}
	
	@RequestMapping(value="addProductImage", method= RequestMethod.POST)
	public String addProductImage(@RequestParam(value="image", required=false) MultipartFile[] image, int imagesequence[], String imagetitle[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Product Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		int productid = productDAO.getLastProductId();
		
		String image1 = "";
		
		try
		{           
			for(int i=0; i<image.length; i++)
			{
				if(image[i].getOriginalFilename() != null && image[i].getOriginalFilename() != "")
				{
					try
					{			
						byte[] bytes =  image[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "product");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/images/product/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            //int height=525, width=560;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		if(valuex[i] < 0 || valuey[i] < 0)
			        		{
			        			valuex[i] = 0;
			        			valuey[i] = 0;
			        			valuew[i] = 650;
			        			valueh[i] = 500;
			        		}
			        		
			        		Image scaledImage = img.getScaledInstance(valuew[i]-1, valueh[i], Image.SCALE_SMOOTH);
			                BufferedImage SubImgage = img.getSubimage(valuex[i], valuey[i], valuew[i]-1, valueh[i]);
			                Graphics2D drawer = SubImgage.createGraphics();
			                drawer.setComposite(AlphaComposite.Src);
			                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			                drawer.drawImage(scaledImage, valuew[i]-1, valueh[i], null);
			                drawer.dispose();
			                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			                ImageIO.write(SubImgage, "jpg", buffer);
			                bytes = buffer.toByteArray();
			        	}
			        	catch (IOException e)
			        	{
			        		//throw new ApplicationException("IOException in scale");
			        	}
			            
			            
			            /********* Today End **********/
			            
			            System.out.println("*********************Path"+path);
			            
			            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			            bufferedOutputStream.write(bytes);
			            bufferedOutputStream.close();
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/bms/resources/admin/images/product/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/product/"+image[i].getOriginalFilename();
			            
			            productImage = new ProductImage(imagesequence[i], imagetitle[i], image1, productid, id, IpAddress);
			    		productDAO.addProductImage(productImage);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}
	
	
	@RequestMapping(value = "addProductTax", method = RequestMethod.POST)
	public String addProductTax(int taxid, float rate, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Add Product Tax Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}

		int productid = productDAO.getLastProductId();
		
		productTax = new Tax(taxid, rate, productid, id, IpAddress);
		productDAO.addTax(productTax);

		return "";
	}
	
	@RequestMapping(value="/deleteProductImage",method= RequestMethod.DELETE)
	public void deleteProductImage(int productid)
	{
		logger.info("Inside Delete Product Image Controller");
		
		productDAO.deleteProductImage(productid);
	}
	
	@RequestMapping(value="/deleteProductTax",method= RequestMethod.DELETE)
	public void deleteProductTax(int productid)
	{
		logger.info("Inside Delete Product Tax Controller");
		
		productDAO.deleteTax(productid);
	}
	
	@RequestMapping(value="editProduct", method= RequestMethod.POST)
	public String editProduct(int productid, int categoryname, int subcategoryname, String productname, String description, String featured, String active, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Product Controller");
		
		String p = productname.replace("$","&");
		String p1 = p.replace("~","#");
		String p2 = p1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		product = new Product(productid, p2, description, categoryname, subcategoryname,  id, IpAddress);
		productDAO.editProduct(product);

		return "";
	}
	
	@RequestMapping(value="editProductImage", method= RequestMethod.POST)
	public String editProductImage(@RequestParam(value="image", required=false) MultipartFile[] image, int productid, int imagesequence[], String imagetitle[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Product Image Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String image1 = "";
		
		try
		{           
			for(int i=0; i<image.length; i++)
			{
				if(image[i].getOriginalFilename() != null && image[i].getOriginalFilename() != "")
				{
					try
					{			
						byte[] bytes =  image[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "product");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
		    			String path = request.getRealPath("/resources/admin/images/product/");
			            File uploadfile = new File(path+File.separator+image[i].getOriginalFilename());
			            
			            /********* Today Start **********/
			            
			            //int height=525, width=560;
			            
			            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			        	try
			        	{
			        		BufferedImage img = ImageIO.read(in);
			        		
			        		if(valuex[i] < 0 || valuey[i] < 0)
			        		{
			        			valuex[i] = 0;
			        			valuey[i] = 0;
			        			valuew[i] = 650;
			        			valueh[i] = 500;
			        		}
			        			
			        		Image scaledImage = img.getScaledInstance(valuew[i]-1, valueh[i], Image.SCALE_SMOOTH);
			                BufferedImage SubImgage = img.getSubimage(valuex[i], valuey[i], valuew[i]-1, valueh[i]);
			                Graphics2D drawer = SubImgage.createGraphics();
			                drawer.setComposite(AlphaComposite.Src);
			                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			                drawer.drawImage(scaledImage, valuew[i]-1, valueh[i], null);
			                drawer.dispose();
			                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			                ImageIO.write(SubImgage, "jpg", buffer);
			                bytes = buffer.toByteArray();
			        	}
			        	catch (IOException e)
			        	{
			        		//throw new ApplicationException("IOException in scale");
			        	}
			            
			            
			            /********* Today End **********/
			            
			            System.out.println("*********************Path"+path);
			            
			            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			            bufferedOutputStream.write(bytes);
			            bufferedOutputStream.close();
			             
			            image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/bms/resources/admin/images/product/"+image[i].getOriginalFilename();
			            //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/product/"+image[i].getOriginalFilename();
			            
			            productImage = new ProductImage(imagesequence[i], imagetitle[i], image1, productid, id, IpAddress);
			    		productDAO.addProductImage(productImage);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}
	
	@RequestMapping(value="addProductImageOld", method= RequestMethod.POST)
	public String addProductImageOld(int productid, int sequence, String imagetitle, String image, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Product Image Old Controller");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		productImage = new ProductImage(sequence, imagetitle, image, productid, id, IpAddress);
		productDAO.addProductImage(productImage);

		return "";
	}
	
	
	@RequestMapping(value = "editProductTax", method = RequestMethod.POST)
	public String editProductTax(int productid, int taxid, float rate, HttpServletRequest request,	HttpSession session)
	{
		logger.info("Inside Edit Product Tax Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		productTax = new Tax(taxid, rate, productid, id, IpAddress);
		productDAO.addTax(productTax);

		return "";
	}
	
	@RequestMapping(value="deleteProduct", method= RequestMethod.DELETE)
	public void delete(int productid)
	{
		logger.info("Inside Delete Product Controller...");

		productDAO.deleteProduct(productid);
	}

}
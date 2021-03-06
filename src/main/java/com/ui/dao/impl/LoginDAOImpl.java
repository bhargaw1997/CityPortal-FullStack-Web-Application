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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.LoginDAO;
import com.ui.model.User;

public class LoginDAOImpl implements LoginDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImpl.class);

	@Override
	public List<User> checkAdminLogin(String email, String password)
	{
		logger.info("Inside Check Admin Login Impl");
		
		List<User> User = new ArrayList<User>();
		
		String sql = "select user_id, first_name, middle_name, last_name, email, password from user where status=? and email=? and password=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, "y");
			ps.setString(2, email);
			ps.setString(3, password);
			
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				user = new User(
		                rs.getInt("user_id"),
		                rs.getString("first_name"),
		                rs.getString("middle_name"),
		                rs.getString("last_name"),
		                rs.getString("email"),
		                rs.getString("password")
				);
				User.add(user);
           }
			rs.close();
			ps.close();
			return User;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	
}
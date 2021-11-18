package com.ui.dao;

import java.util.List;

import com.ui.model.User;

public interface LoginDAO 
{
	List<User> checkAdminLogin(String email, String password);
}

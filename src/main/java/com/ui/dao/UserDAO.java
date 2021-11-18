package com.ui.dao;

import java.util.List;

import com.ui.model.User;

public interface UserDAO 
{
	public List<User> getAllUsers();
	public void addUser(User u);
	public void editUser(User u);
	public void deleteUser(int userid);
	public List<User> getAllUsersByPage(int pagesize, int startindex);
	public List<User> getUserByUserTypeId(int usertypeid);
	public List<User> getUserByAreaId(int areaid);
	public List<User> getUserByUserId(int userid);
}

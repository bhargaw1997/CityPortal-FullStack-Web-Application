package com.ui.dao;

import java.util.List;

import com.ui.model.UserType;

public interface UserTypeDAO 
{
	public List<UserType> getAllUserTypes();
	public void addUserType(UserType u);
	public void editUserType(UserType u);
	public void deleteUserType(int usertypeid);
	public List<UserType> getAllUserTypesByPage(int pagesize, int startindex);
}

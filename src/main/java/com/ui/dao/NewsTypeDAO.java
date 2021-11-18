package com.ui.dao;

import java.util.List;

import com.ui.model.NewsType;

public interface NewsTypeDAO 
{
	public List<NewsType> getAllNewsTypes();
	public void addNewsType(NewsType c);
	public void editNewsType(NewsType c);
	public void deleteNewsType(int newstypeid);
	public List<NewsType> getAllNewsTypesByPage(int pagesize, int startindex);
}

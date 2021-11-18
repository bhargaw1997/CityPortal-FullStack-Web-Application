package com.ui.dao;

import java.util.List;

import com.ui.model.News;
import com.ui.model.NewsImage;

public interface NewsDAO 
{
	public List<News> getAllNews();
	public News getNewsByNewsId(int newsid);
	public void addNews(News p);
	public void editNews(News p);
	public void deleteNews(int newsid);
	public List<News> getAllNewsByPage(int pagesize, int startindex);
	public List<News> getAllNewsWithOneImageByPage(int pagesize, int startindex);
	public List<NewsImage> getNewsImageByNewsId(int newsid);
	public int getLastNewsId();
	public void addNewsImage(NewsImage newsImage);
	public void deleteNewsImage(int imageid);
	public List<News> getNewsWithOneImageByNewsId(int newsid);
	public List<News> getLastFourNewsWithOneImage();
	public List<News> getLastNewsWithOneImage();
	public List<News> getSecondLastFourNewsWithOneImage();
}

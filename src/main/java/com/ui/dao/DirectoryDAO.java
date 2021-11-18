package com.ui.dao;

import java.util.List;

import com.ui.model.Directory;
import com.ui.model.DirectoryImage;
import com.ui.model.Event;
import com.ui.model.Product;

public interface DirectoryDAO 
{
	public List<Directory> getAllDirectories();
	public List<Directory> searchDirectories(String keyword);
	public List<Directory> getDirectorybySearch(String keyword);
	public void addDirectory(Directory e);
	public void editDirectory(Directory e);
	public void deleteDirectory(int directoryid);
	public List<Directory> getAllDirectoriesByPage(int pagesize, int startindex);
	public List<Directory> getAllDirectoriesWithOneImageByPage(int pagesize, int startindex);
	public List<DirectoryImage> getDirectoryImageByDirectoryId(int directoryid);
	public int getLastDirectoryId();
	public void addDirectoryImage(DirectoryImage directoryimage);
	public void deleteDirectoryImage(int imageid);
	public Directory getDirectoryByDirectoryId(int directoryid);
}

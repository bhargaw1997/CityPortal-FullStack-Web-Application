package com.ui.dao;

import java.util.List;

import com.ui.model.Area;

public interface AreaDAO 
{
	public List<Area> getAllAreas();
	public List<Area> getAreaByCityId(int cityId);
	public List<Area> getAreaByAreaId(int areaId);
	public void addArea(Area a);
	public void editArea(Area a);
	public void deleteArea(int areaid);
	public List<Area> getAllAreasByPage(int pagesize, int startindex);
	public List<Area> searchAreas(String keyword);
}

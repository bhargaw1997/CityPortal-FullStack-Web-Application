package com.ui.dao;

import java.util.List;

import com.ui.model.City;

public interface CityDAO 
{
	public List<City> getAllCities();
	public List<City> getCityByStateId(int stateId);
	public void addCity(City c);
	public void editCity(City c);
	public void deleteCity(int cityid);
	public List<City> getAllCitiesByPage(int pagesize, int startindex);
	public List<City> searchCities(String keyword);
}

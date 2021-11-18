package com.ui.dao;

import java.util.List;

import com.ui.model.Cityguide;
import com.ui.model.CityguideImage;

public interface CityguideDAO 
{
	public List<Cityguide> getAllCityguides();
	public void addCityguide(Cityguide p);
	public void editCityguide(Cityguide p);
	public void deleteCityguide(int cityguideid);
	public List<Cityguide> getAllCityguidesByPage(int pagesize, int startindex);
	public List<Cityguide> getCityguidesWithOneImageByPage(int pagesize, int startindex);
	public List<CityguideImage> getCityguideImageByCityguideId(int cityguideid);
	public int getLastCityguideId();
	public void addCityguideImage(CityguideImage cityguideImage);
	public void deleteCityguideImage(int imageid);
	public List<Cityguide> getCityguidesLifestyle();
	public List<Cityguide> getCityguidesFashion();
	public List<Cityguide> getCityguidesEatdrink();
	public List<Cityguide> getCityguidesPlacetovisit();
	public List<Cityguide> getCityguidesOfficialmatters();
	public List<Cityguide> getCityguidesTransportation();
	public List<Cityguide> getCityguidesHealth();
	public List<Cityguide> getCityguidesHelplines();
}

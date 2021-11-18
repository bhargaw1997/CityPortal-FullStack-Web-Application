package com.ui.dao;

import java.util.List;

import com.ui.model.Country;

public interface CountryDAO 
{
	public List<Country> getAllCountries();
	public void addCountry(Country c);
	public void editCountry(Country c);
	public void deleteCountry(int countryid);
	public List<Country> getAllCountriesByPage(int pagesize, int startindex);
	public List<Country> searchCountries(String keyword);
}

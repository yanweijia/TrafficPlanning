package cn.yanweijia.dao;

import java.util.ArrayList;

public class CityList {
	private ArrayList<City> list = null;
	
	public CityList(){
		list = new ArrayList<City>();
	}
	public int getSize(){
		return list.size();
	}
	public ArrayList<City> getList(){
		return list;
	}
	public void addCity(City city){
		list.add(city);
	}
}

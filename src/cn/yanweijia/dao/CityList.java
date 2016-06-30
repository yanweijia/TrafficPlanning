package cn.yanweijia.dao;

import java.util.ArrayList;
import java.util.Iterator;


public class CityList {
	public static final int NAME_CN = 0;
	public static final int NAME_EN = 1;
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
	public int getCityID(int nameType,String cityName){
		Iterator<City> iterator = getList().iterator();
		while(iterator.hasNext()){
			City city = (City)iterator.next();
			if((nameType==NAME_CN)?cityName.equals(city.nameCN):cityName.equals(city.nameEN))
				return city.id;
		}
		return 0;	//如果找不到,返回0
	}
	public City getCity(int ID){
		City[] cityArray;
		cityArray = new City[getSize()];
		
		Iterator<City> iterator = getList().iterator();
		for(int i = 0 ; iterator.hasNext() ; i++){
			cityArray[i] = (City)iterator.next();
			if(cityArray[i].id==ID)
				return cityArray[i];
		}
		return null;
	}
	//指定下标的city
	public City IndexOf(int index){
		City[] cityArray;
		cityArray = new City[getSize()];
		
		Iterator<City> iterator = getList().iterator();
		for(int i = 0 ; iterator.hasNext() ; i++){
			cityArray[i] = (City)iterator.next();
		}
		
		if(index >= 0 && index < cityArray.length)
			return cityArray[index];
		return null;
	}
}

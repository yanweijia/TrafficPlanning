package cn.yanweijia.dao;

public class City {
	int id;
	public String nameCN,nameEN;
	double longitude,latitude;
	public City(int _id,String _nameCN,String _nameEN,double _longitude,double _latitude){
		this.id = _id;
		this.nameCN = _nameCN;
		this.nameEN = _nameEN;
		this.longitude = _longitude;
		this.latitude = _latitude;
	}
}

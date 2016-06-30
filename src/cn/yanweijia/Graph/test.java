package cn.yanweijia.Graph;

import cn.yanweijia.Tools.Config;
import cn.yanweijia.dao.City;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.DBHelper;
import cn.yanweijia.dao.DayTime;
import cn.yanweijia.dao.Line;
import cn.yanweijia.dao.LineList;

public class test {

	public static void main(String[] args) {
		/*
		Config.init();
		DBHelper dbHelper = new DBHelper();
		CityList cityList = dbHelper.getAllCitys();
		LineList lineList = dbHelper.getAllLineList_Train();
//		CityList cityList = new CityList();
//		LineList lineList = new LineList();
//		cityList.addCity(new City(1, "北京", "Beijing", 0, 0));
//		cityList.addCity(new City(2,"上海","Shanghai",0,0));
//		cityList.addCity(new City(3,"哈尔滨","haerbin",0,0));
//		lineList.addLine(new Line("AC123", 1, 2, new DayTime("11:11"), new DayTime("12:12"), new DayTime("1:1"), 50, 50));
//		lineList.addLine(new Line("AC123", 2,3, new DayTime("11:11"), new DayTime("12:12"), new DayTime("1:1"), 50, 50));
//		//lineList.addLine(new Line("AC123", 3, 1, new DayTime("11:11"), new DayTime("12:12"), new DayTime("1:1"), 50, 50));
		PlanLine test = new PlanLine(cityList,lineList,1,4);
		*/
		Dijkstra di = new Dijkstra();
		Node start = di.init();
		di.computePath(start);
		di.printPathInfo();
		
	}
}

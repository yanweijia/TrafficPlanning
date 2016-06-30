package cn.yanweijia.tu;


import cn.yanweijia.Tools.Config;
import cn.yanweijia.dao.City;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.DBHelper;
import cn.yanweijia.dao.Line;
import cn.yanweijia.dao.LineList;

public class Graph {
	public static final int PRINCPLE_DISTANCE = 1;
	public static final int PRINCPLE_PRICE = 2;
	public static void main(String[] args){
		Config.init();
		DBHelper dbHelper = new DBHelper();
		CityList cityList = dbHelper.getAllCitys();
		LineList lineList = dbHelper.getAllLineList_Train();
		getLineList(cityList,lineList,1,3,PRINCPLE_DISTANCE);
	}
	
	
	public static LineList getLineList(CityList cityList,LineList lineList,int startID,int endID,int princple){
		String[] vertices=new String[cityList.getSize()];
		for(int i = 0 ; i < vertices.length ; i++){
			vertices[i] = cityList.IndexOf(i).nameCN;
		}
		Triple edges[] = new Triple[lineList.getSize()];
		for(int i = 0 ; i < edges.length ; i++){
			Line line = lineList.IndexOf(i);
			int num1,num2;
			City city1 = cityList.getCity(line.lineFrom);
			City city2 = cityList.getCity(line.lineTo);
			num1 = getIndex(vertices,city1.nameCN);
			num2 = getIndex(vertices,city2.nameCN);
			//System.out.println("" +num1 + ":" + num2 + city.nameCN + line.ID);
			edges[i] = new Triple(num1,num2,(int)((princple==PRINCPLE_DISTANCE)?line.distance:line.price));
		}
		
		

		MatrixGraph<String>graph=new MatrixGraph<String>(vertices,edges);
		System.out.println("带权无向图G3（除顶点F），"+graph.shortestPath("上海", "西安"));
		
		return null;
	}
	private static int getIndex(String[] a,String b){
		for(int i = 0 ; i < a.length ; i++)
		{
			//System.out.println(a[i] + b);
			if(a[i].equals(b))
				return i;
		}
		return -1;
	}

		
	
}


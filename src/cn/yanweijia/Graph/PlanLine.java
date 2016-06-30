package cn.yanweijia.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import cn.yanweijia.dao.City;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.Line;
import cn.yanweijia.dao.LineList;

public class PlanLine {
	Dijkstra graph;
	HashMap<String,Node> nodeList;
	public PlanLine(CityList cityList,LineList lineList,int startID,int endID){
		graph = new Dijkstra();
		nodeList = new HashMap<String,Node>();
		//先把所有node遍历生成后放入nodeList中
		for(int i = 0 ; i < cityList.getSize() ; i++){
			City city = cityList.IndexOf(i);
			Node node = new Node(city.nameCN);
			nodeList.put(city.nameCN, node);
		}
		//再遍历一遍所有边,将边的起始站点是node的添加在node的chind里面
		for(int i = 0 ; i < cityList.getSize() ; i++){
			Node node = nodeList.get(cityList.IndexOf(i).nameCN);
			//将出发站点为node的边添加到该node的child里
			Line[] lineArray = lineList.getLineWithStartCity(cityList.IndexOf(i).id);
			for(int j = 0 ; j < lineArray.length ; j++){
				City tempCity = cityList.getCity(lineArray[j].lineTo);
				if(tempCity==null)
					continue;
				Map<Node,Double> child = node.getChild();
				Node tempNode = nodeList.get(tempCity.nameCN);
				double distance = lineArray[j].distance;
				child.put(tempNode, distance);
				
				
				//node.getChild().put(nodeList.get(tempCity.nameCN), lineArray[j].distance);
			}
		}
		
		
		
		Set<Node> open;	//将初始节点放入close,其他节点放入open 
	    Set<Node> close;
	    Map<String,Double> path;//封装路径距离 
	    Map<String,String> pathInfo;//封装路径信息 
	    
	    open = graph.getOpen();
	    close = graph.getClose();
	    path = graph.getPath();
	    pathInfo = graph.getPathInfo();
		Node startNode = null;
		//用for循环来讲node放进open或close中
	    for(int i = 0 ; i < nodeList.size() ; i++){
	    	City city = cityList.IndexOf(i);
	    	Node node = nodeList.get(city.nameCN);
	    	if(city.id==startID){
	    		close.add(node);
	    		startNode = node;
	    	}
	    	else{
	    		open.add(node);
	    		//初始路径,放进path里面,没有路径的默认返回为Double.MAX_VALUE
	    		double distance = lineList.getDistance(startID, endID);
	    		path.put(node.getName(),distance);

	    		String pathStr;
	    		if(distance==Double.MAX_VALUE)
	    			pathStr = cityList.getCity(startID).nameCN;
	    		else
	    			pathStr = cityList.getCity(startID).nameCN + "->" + node.getName();
	    		
	    		pathInfo.put(node.getName(),pathStr);
	    	}
	    }
	    this.graph.computePath(startNode);
		this.graph.printPathInfo();
//	    String result = this.graph.getPathString(cityList.getCity(endID).nameCN);
//	    System.out.println(result);
	}
}

package cn.yanweijia.Graph;

import java.util.ArrayList;

//节点定义
public class Node {
	public String ID;
	public ArrayList<Edge> edgeList;	//Edge集合,出边表
	public Node(String _id){
		this.ID = _id;
		this.edgeList = new ArrayList<Edge>();
	}
}

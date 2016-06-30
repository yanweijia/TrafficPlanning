package cn.yanweijia.Graph;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private String name;
	private Map<Node,Double> child=new HashMap<Node,Double>();
	public Node(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Node, Double> getChild() {
		return child;
	}
	public void setChild(Map<Node, Double> child) {
		this.child = child;
	}
}
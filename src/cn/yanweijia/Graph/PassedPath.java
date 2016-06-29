package cn.yanweijia.Graph;

import java.util.ArrayList;

public class PassedPath {
	private String curNodeID;
	private boolean beProcessed;	//是否已被处理
	private double weight;	//累计的权值
	private	ArrayList<String> passedIDList;	//路径
	
	public PassedPath(String ID){
		this.curNodeID = ID;
		this.weight = Double.MAX_VALUE;
		this.passedIDList = new ArrayList<String>();
		this.beProcessed = false;
	}
	public boolean getBeProcessed(){
		return this.beProcessed;
	}
	public void setBeProcessed(boolean value){
		this.beProcessed = value;
	}
	public String CurNodeID(){
		return this.curNodeID;
	}
	public double getWeight(){
		return this.weight;
	}
	public void setWeight(double _weight){
		this.weight = _weight;
	}
	public ArrayList<String> PassedIDList(){
		return this.passedIDList;
	}
}

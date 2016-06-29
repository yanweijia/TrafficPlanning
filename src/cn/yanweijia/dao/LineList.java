package cn.yanweijia.dao;

import java.util.ArrayList;

public class LineList {
	private ArrayList<Line> list = null;
	public LineList(){
		list = new ArrayList<Line>();
	}
	public int getSize(){
		return list.size();
	}
	public ArrayList<Line> getList(){
		return list;
	}
	public void addLine(Line line){
		list.add(line);
	}
}

package cn.yanweijia.dao;

import java.util.ArrayList;
import java.util.Iterator;


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
	public Line getLine(String lineID){
		//先进行赋值
		Iterator<Line> iterator = list.iterator();
		while(iterator.hasNext()){
			Line line = (Line)iterator.next();
			if(line.ID.equals(lineID))
				return line;
		}
		return null;
	}
	public Line getLine(int line_from,int line_to){
		Line[] lineArray = getLineWithStartCity(line_from);
		//寻找相同的
		for(int i = 0 ; i < lineArray.length ; i++){
			if(lineArray[i].lineTo==line_to){
				return lineArray[i];
			}
		}
		return null;
		
	}
	public Line[] getLineWithStartCity(int cityID){
		Line[] lineArray ,lineArray_new;
		int lineCount = 0;
		//初始化数组
		Iterator<Line> iterator = getList().iterator();
		lineArray = new Line[getSize()];
		for(int i = 0 ; iterator.hasNext() ; i++){
			lineArray[i] = (Line)iterator.next();
		}
		//寻找相同的
		for(int i = 0 ; i < lineArray.length ; i++){
			if(lineArray[i].lineFrom==cityID)
				lineCount++;
		}
		lineArray_new = new Line[lineCount];
		lineCount = 0;
		for(int i = 0 ; i < lineArray.length ; i++){
			if(lineArray[i].lineFrom==cityID){
				lineArray_new[lineCount] = lineArray[i];
				lineCount++;
			}
		}
		return lineArray_new;
	}
	public double getDistance(int line_from,int line_to){
		Line[] lineArray = getLineWithStartCity(line_from);
		double distance = Double.MAX_VALUE;
		//寻找相同的
		for(int i = 0 ; i < lineArray.length ; i++){
			if(lineArray[i].lineTo==line_to){
				System.out.println(line_from + "  " + line_to + "  " + distance);
				return lineArray[i].distance;
			}
		}		
		
		
		return distance;
	}
	//指定下标的line
	public Line IndexOf(int index){
		Line[] lineArray;
		lineArray = new Line[getSize()];
		
		Iterator<Line> iterator = getList().iterator();
		for(int i = 0 ; iterator.hasNext() ; i++){
			lineArray[i] = (Line)iterator.next();
		}
		
		if(index >= 0 && index < lineArray.length)
			return lineArray[index];
		return null;
	}
}

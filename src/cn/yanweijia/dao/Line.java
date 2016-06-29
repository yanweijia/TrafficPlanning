package cn.yanweijia.dao;

public class Line {
	public String ID;
	public int lineFrom,lineTo;
	public DayTime startTime,endTime,costTime;
	public double distance;
	public double price;
	public Line(String _ID,int _lineFrom,int _lineTo,DayTime _startTime,DayTime _endTime,DayTime _costTime,double _distance,double _price){
		ID = _ID;
		lineFrom = _lineFrom;
		lineTo = _lineTo;
		startTime = _startTime;
		endTime = _endTime;
		costTime = _costTime;
		distance = _distance;
		price = _price;
	}
}

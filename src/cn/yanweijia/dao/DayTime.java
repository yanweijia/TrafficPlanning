package cn.yanweijia.dao;


//自定义时间类.
public class DayTime {
	private int day;
	private int hours;
	private int minutes;
	//从文本构造时间,比如  11:13
	public DayTime(String strTime){
		day = 0;
		hours = Integer.parseInt(strTime.substring(0, strTime.indexOf(":")));
		strTime = strTime.substring(strTime.indexOf(":") + 1);
		minutes = Integer.parseInt(strTime.substring(strTime.indexOf(":") + 1));
	}
	public DayTime(int _day,int _hours,int _minutes){
		if(_day < 0) _day = 0;
		if(_hours < 0 || _hours > 24) _hours = 0;
		if(_minutes < 0 || _minutes > 60) _minutes = 0;
		day = _day;
		hours = _hours;
		minutes = _minutes;
	}
	public DayTime(){
		day = 0;
		hours = 0;
		minutes = 0;
	}
	public DayTime(int _hours,int _minutes){
		if(_hours < 0 || _hours > 24) _hours = 0;
		if(_minutes < 0 || _minutes > 60) _minutes = 0;
		day = 0;
		hours = _hours;
		minutes = _minutes;
	}
	//时间加法
	public DayTime add(DayTime another){
		int _day = this.getDay() + another.getDay();
		int _hours = this.getHours() + another.getHours();
		int _minutes = this.getMinutes() + another.getMinutes();
		if(_minutes > 60){
			_hours++;
			_minutes %= 60;
		}
		if(_hours > 24){
			_day++;
			_hours %= 24;
		}
		DayTime dayTime = new DayTime(_day,_hours,_minutes);
		return dayTime;
	}
	//时间减法
	public DayTime sub(DayTime another){
		int _minutes = this.getMinutes() - another.getMinutes();
		int _hours = this.getHours() - another.getMinutes();
		if(_minutes < 0){
			_hours--;
			_minutes = (_minutes + 60) % 60;
		}
		if(_hours < 0){
			_hours = (_hours + 24) % 24;
		}
		DayTime dayTime = new DayTime(_hours,_minutes);
		return dayTime;
	}
	public String toString(){
		String sHours = (hours >= 10) ? String.valueOf(hours) : "0"+String.valueOf(hours);
		String sMinutes = (minutes >= 10) ? String.valueOf(minutes) : "0"+String.valueOf(minutes);
		return sHours + ":" + sMinutes;
	}
	public String toStringWithDay(){
		return String.valueOf(day) + ":" + toString();
	}

	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}

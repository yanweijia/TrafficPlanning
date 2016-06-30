package cn.yanweijia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.yanweijia.Tools.Config;
import cn.yanweijia.Tools.Debug;

//数据库操作
public class DBHelper {
	String url = "jdbc:mysql://localhost/trafficplanning?characterEncoding=UTF-8";
	String driver = "com.mysql.jdbc.Driver";
	String user = "root";
	String password = "";
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet result = null;
	public static final String LINE_PLANE = "line_plane";
	public static final String LINE_TRAIN = "line_train";
	public DBHelper(){
		url = Config.getSqlUrl();
		driver = Config.getSqlDriverName();
		user = Config.getSqlUsername();
		password = Config.getSqlPassword();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);

		}catch(ClassNotFoundException e){
			Debug.log("数据库ClassNotFoundException:" + e.getMessage());
		}catch (SQLException e) {
			Debug.log("数据库连接异常:" + e.getMessage());
		}
	}
	//析构函数 , 系统自动调用
	protected void finalize(){
		this.close();
	}
	//关闭数据连接,使用完成及时释放连接资源
	public void close(){
		try {
			//result.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			Debug.log("数据库连接断开(关闭)失败:" + e.getMessage());
		}
	}
	//获取城市列表
	public CityList getAllCitys(){
		CityList list = new CityList();
		try{
			String sql = "SELECT * FROM city";
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next()){
				int id = result.getInt("city_id");
				String nameCN = result.getString("name_cn");
				String nameEN = result.getString("name_en");
				double longitude = result.getDouble("longitude");
				double latitude = result.getDouble("latitude");
				City city = new City(id,nameCN,nameEN,longitude,latitude);
				list.addCity(city);
			}
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库查询City失败:" + e.getMessage());
		}
		
		return list;
	}
	//获取火车线路表
	public LineList getAllLineList_Train(){
		LineList list = new LineList();
		try{
			String sql = "SELECT * FROM line_train";
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next()){
				String ID = result.getString("line_id");
				int lineFrom = result.getInt("line_from");
				int lineTo = result.getInt("line_to");
				DayTime startTime = new DayTime(result.getString("startTime"));
				DayTime endTime = new DayTime(result.getString("endTime"));
				DayTime costTime = new DayTime(result.getString("costTime"));
				double distance = result.getDouble("distance");
				double price = result.getDouble("price");
				Line line = new Line(ID,lineFrom,lineTo,startTime,endTime,costTime,distance,price);
				list.addLine(line);
			}
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库查询Line失败:" + e.getMessage());
		}
		return list;
	}
	//获取飞机线路表
	public LineList getAllLineList_Plane(){
		LineList list = new LineList();
		try{
			String sql = "SELECT * FROM line_plane";
			pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			while(result.next()){
				String ID = result.getString("line_id");
				int lineFrom = result.getInt("line_from");
				int lineTo = result.getInt("line_to");
				String strStartTime = result.getString("startTime");
				DayTime startTime = new DayTime(strStartTime.substring(0, strStartTime.lastIndexOf(':')));
				String strEndTime = result.getString("endTime");
				DayTime endTime = new DayTime(strEndTime.substring(0, strEndTime.lastIndexOf(':')));
				String strCostTime = result.getString("costTime");
				DayTime costTime = new DayTime(strCostTime.substring(0, strCostTime.lastIndexOf(':')));
				double distance = result.getDouble("distance");
				double price = result.getDouble("price");
				Line line = new Line(ID,lineFrom,lineTo,startTime,endTime,costTime,distance,price);
				list.addLine(line);
			}
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库查询Line失败:" + e.getMessage());
		}
		return list;
	}
	//删除指定城市
	public boolean deleteCity(int cityID){
		try{
			String sql = "DELETE FROM city WHERE city_id=" + cityID;
			pst = con.prepareStatement(sql);
			int result = pst.executeUpdate();	//返回值为受影响行数
			if(result > 0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库删除指定城市失败,城市ID:" + cityID + ",详细信息:" + e.getMessage());
		}
		return false;
	}
	//删除指定火车线路
	public boolean deleteLine_Train(String lineID){
		try{
			String sql = "DELETE FROM line_train WHERE line_id='" + lineID + "'";
			pst = con.prepareStatement(sql);
			int result = pst.executeUpdate();
			if(result > 0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库删除指定火车线路失败,线路ID:" + lineID + ",详细信息:" + e.getMessage());
		}
		return false;
	}
	//删除指定飞机线路
	public boolean deleteLine_Plane(String lineID){
		try{
			String sql = "DELETE FROM line_plane WHERE line_id='" + lineID + "'";
			pst = con.prepareStatement(sql);
			int result = pst.executeUpdate();
			if(result > 0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库删除指定飞机线路失败,线路ID:" + lineID + ",详细信息:" + e.getMessage());
		}
		return false;
	}
	//添加一个城市
	public boolean addCity(int cityID,String nameCN,String nameEN,double longitude,double latitude){
		try{
			String sql = "INSERT INTO city(city_id,name_cn,name_en,longitude,latitude)VALUES"
					+ "(" + cityID + ",'" + nameCN + "','" + nameEN + "'," + longitude + "," + latitude + ")";
			pst = con.prepareStatement(sql);
			int result = pst.executeUpdate();
			if(result > 0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库增加新城市出错!" + e.getMessage());
			return false;
		}
	}
	//添加一条新火车线路,第一个参数line是数据库名,调用的时候选择DBHelper.LINE_TRAIN就可以,在本类全局变量中有声明,(LINE_TRAIN,LINE_PLANE)
	public boolean addLine(String line,String line_id,int line_from,int line_to,DayTime startTime,DayTime endTime,double distance,DayTime costTime,double price){
		if(!(line.equals(LINE_TRAIN) || line.equals(LINE_PLANE)))
			return false;
		try{
			String sql = "INSERT INTO " + line + "(line_id,line_from,line_to,startTime,endTime,distance,costTime,price)VALUES"
					+ "('" + line_id + "'," + line_from + "," + line_to + ",'" + startTime.toString() + "','" + endTime.toString() + "'," + distance + ",'" + costTime.toString() + "'," + price + ")";
			pst = con.prepareStatement(sql);
			int result = pst.executeUpdate();
			if(result > 0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			Debug.log("数据库增加火车线路出错" + e.getMessage());
			return false;
		}
	}
}

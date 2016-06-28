package cn.yanweijia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//数据库操作
public class DBHelper {
	String url = "jdbc:mysql://localhost/trafficplanning?characterEncoding=UTF-8";
	String driver = "com.mysql.jdbc.Driver";
	String user = "root";
	String password = "";
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet result = null;
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
			result.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			Debug.log("数据库连接断开(关闭)失败:" + e.getMessage());
		}
	}
	
	public City[] getAllCitys(){
		
		
		return null;
	}
	
	
}

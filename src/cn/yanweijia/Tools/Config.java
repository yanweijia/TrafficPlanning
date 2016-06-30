package cn.yanweijia.Tools;


import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {
	public static final int LANGUAGE_CN = 0;
	public static final int LANGUAGE_EN = 1;
	private static Properties pro = null;	//存放配置信息(String类型的键值对)
	private static final String FILE_DIR = "Config.ini";
	
	//初始化必须运行,否则会报空指针异常
	public static void init(){
		if(pro==null){
			pro = new Properties();
		}
	}
	//从文件读取配置信息
	public static void readFromFile(){
		try {
			
			FileInputStream inStream = new FileInputStream(FILE_DIR);
			InputStreamReader reader = new InputStreamReader(inStream,"UTF-8");
			pro.load(reader);
			reader.close();
			inStream.close();
			
		} catch (Exception e) {
			Debug.log("未发现配置文件,读取配置文件失败,详细信息:" + e.getMessage());
		}
		Debug.log("配置文件载入成功!");
	}
	//将所有配置信息写到文件
	public static void writeToFile(){
		try{
			//Debug.log("" + Config.class.getResource(FILE_DIR));
			FileOutputStream outFile = new FileOutputStream(FILE_DIR);
			pro.store(outFile, "Please don't delete this File!");
			outFile.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//设置软件使用语言
	public static void setLanguage(int language){
		if(language == LANGUAGE_CN){
			pro.setProperty("language", "" + String.valueOf(LANGUAGE_CN));
		}
		if(language == LANGUAGE_EN){
			pro.setProperty("language", String.valueOf(LANGUAGE_EN));
		}
		writeToFile();
	}
	//获取使用的语言,如果没有,默认中文
	public static int getLanguage(){
		String strLanguage = pro.getProperty("language", String.valueOf(LANGUAGE_CN));
		return Integer.parseInt(strLanguage);
	}
	//将窗口的位置大小写入到配置文件中
	public static void setMainWindowBounds(Rectangle rec){
		
		pro.setProperty("MainWindowBounds_x", String.valueOf(rec.getX()));
		pro.setProperty("MainWindowBounds_y", String.valueOf(rec.getY()));
		pro.setProperty("MainWindowBounds_width", String.valueOf(rec.getWidth()));
		pro.setProperty("MainWindowBounds_height", String.valueOf(rec.getHeight()));
		writeToFile();
	}
	//读取配置文件中主窗口的位置,如果读取失败,这里给出默认值
	public static Rectangle getMainWindowBounds(){
		double x,y,width,height;
		x = Double.parseDouble(pro.getProperty("MainWindowBounds_x","100"));
		y = Double.parseDouble(pro.getProperty("MainWindowBounds_y","100"));
		width = Double.parseDouble(pro.getProperty("MainWindowBounds_width","600"));
		height = Double.parseDouble(pro.getProperty("MainWindowBounds_height","600"));

		Rectangle rec = new Rectangle();
		rec.setRect(x, y, width, height);
		return rec;
	}
	
	//数据库url
	public static String getSqlUrl(){
		String url = pro.getProperty("sql_url", "jdbc:mysql://localhost/trafficplanning?characterEncoding=UTF-8");
		return url;
	}
	
	//数据库驱动名称
	public static String getSqlDriverName(){
		String driver = pro.getProperty("sql_driver","com.mysql.jdbc.Driver");
		return driver;
	}
	
	//数据库用户名
	public static String getSqlUsername(){
		String username = pro.getProperty("sql_user", "root");
		return username;
	}
	
	//数据库密码
	public static String getSqlPassword(){
		String password = pro.getProperty("sql_password", "");
		return password;
	}

}

package cn.yanweijia.dao;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Language {
	private static Properties pro = null;	//存放配置信息(String类型的键值对)
	private static final String FILE_DIR_ZH_CN = "zh_cn.ini";
	private static final String FILE_DIR_EN_US = "en_us.ini";
	
	//初始化,根据Config.ini的语言设置来选择性载入文件
	public static void init(){
		if(pro==null){
			pro = new Properties();
		}
		try{
			Config.init();
			FileInputStream inStream;
			if(Config.getLanguage()==Config.LANGUAGE_CN)
				inStream = new FileInputStream(FILE_DIR_ZH_CN);
			else
				inStream = new FileInputStream(FILE_DIR_EN_US);
			InputStreamReader reader = new InputStreamReader(inStream,"UTF-8");	//这里指定编码,否则会利用系统默认编码中文可能乱码
			pro.load(reader);
			reader.close();
			inStream.close();
		}catch(Exception e){
			Debug.log("载入语言出错!详细信息:" + e.getMessage());
		}
		Debug.log("载入语言文件成功!");
		
	}
	public static String MainWindow_title(){
		return pro.getProperty("MainWindow_title","全国交通咨询模拟 20140712 Yan Weijia");
	}
	public static String MainWindow_btn_city(){
		return pro.getProperty("MainWindow_btn_city", "城市维护");
	}
	public static String MainWindow_btn_line(){
		return pro.getProperty("MainWindow_btn_line", "线路规划");
	}
	public static String MainWindow_btn_history(){
		return pro.getProperty("MainWindow_btn_history","查询历史");
	}
	public static String MainWindow_btn_language(){
		return pro.getProperty("MainWindow_btn_language","语言选择");
	}
	public static String MainWindow_titledBorder(){
		return pro.getProperty("MainWindow_titledBorder","查询");
	}
	public static String MainWindow_label_way(){
		return pro.getProperty("MainWindow_label_way","交通方式:");
	}
	public static String MainWindow_label_rules(){
		return pro.getProperty("MainWindow_label_rules","决策原则:");
	}
	public static String MainWindow_label_from(){
		return pro.getProperty("MainWindow_label_from","起始站点:");
	}
	public static String MainWindow_label_to(){
		return pro.getProperty("MainWindow_label_to","到达站点:");
	}
	public static String MainWindow_btn_query(){
		return pro.getProperty("MainWindow_btn_query","查询");
	}
	public static String MainWindow_btn_clear(){
		return pro.getProperty("MainWindow_btn_clear","清空查询结果");
	}
	public static String MainWindow_comboBox_way_train(){
		return pro.getProperty("MainWindow_comboBox_way_train","火车");
	}
	public static String MainWindow_comboBox_way_plane(){
		return pro.getProperty("MainWindow_comboBox_way_plane","飞机");
	}
	public static String MainWindow_comboBox_rules_timeFirst(){
		return pro.getProperty("MainWindow_comboBox_rules_timeFirse","时间最短");
	}
	public static String MainWindow_comboBox_rules_moneyFirst(){
		return pro.getProperty("MainWindow_comboBox_rules_moneyFirst","价格最少");
	}
	public static String MainWindow_comboBox_rules_transferFirst(){
		return pro.getProperty("MainWindow_comboBox_rules_transferFirst","最少换乘");
	}
	public static String MainWindow_label_sumTime(){
		return pro.getProperty("MainWindow_label_sumTime","总时间:");
	}
	public static String MainWindow_label_sumMoney(){
		return pro.getProperty("MainWindow_label_sumMoney","总金额:");
	}
	public static String MainWindow_label_sumTransfer(){
		return pro.getProperty("MainWindow_label_sumTransfer","换乘次数:");
	}
}

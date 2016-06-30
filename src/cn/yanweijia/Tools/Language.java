package cn.yanweijia.Tools;

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
	public static String MainWindow_label_distance(){
		return pro.getProperty("MainWindow_label_distance","总距离");
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
		return pro.getProperty("MainWindow_comboBox_rules_timeFirst","时间最短");
	}
	public static String MainWindow_comboBox_rules_moneyFirst(){
		return pro.getProperty("MainWindow_comboBox_rules_moneyFirst","价格最少");
	}
	public static String MainWindow_comboBox_rules_distanceFirst(){
		return pro.getProperty("MainWindow_comboBox_rules_distanceFirst","距离最短");
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
	public static String CityWindow_title(){
		return pro.getProperty("CityWindow_title","城市信息维护");
	}
	public static String CityWindow_btn_add(){
		return pro.getProperty("CityWindow_btn_add","添加城市");
	}
	public static String CityWindow_btn_delete(){
		return pro.getProperty("CityWindow_btn_delete","删除选中城市");
	}
	public static String CityWindow_JOptionPaneLanguageBtnOK(){
		return pro.getProperty("CityWindow_JOptionPaneLanguage_btn_ok","确定");
	}
	public static String CityWindow_JOptionPaneLanguageBtnCANCEL(){
		return pro.getProperty("CityWindow_JOptionPaneLanguage_btn_cancel","取消");
	}
	public static String LineWindow_title(){
		return pro.getProperty("LineWindow_title","交通线路维护");
	}
	public static String LineWindow_label_line(){
		return pro.getProperty("LineWindow_label_line","线路:");
	}
	public static String LineWindow_comboBox_train(){
		return pro.getProperty("LineWindow_comboBox_train","火车");
	}
	public static String LineWindow_comboBox_plane(){
		return pro.getProperty("LineWindow_comboBox_plane","飞机");
	}
	public static String LineWindow_btn_addNew(){
		return pro.getProperty("LineWindow_btn_addNew","新建线路");
	}
	public static String LineWindow_btn_delete(){
		return pro.getProperty("LineWindow_btn_delete","删除选中线路");
	}
	public static String NewLineWindow_title(){
		return pro.getProperty("NewLineWindow_title","增加新线路");
	}
	public static String NewLineWindow_label_way(){
		return pro.getProperty("NewLineWindow_label_way","线路类型");
	}
	public static String NewLineWindow_label_from(){
		return pro.getProperty("NewLineWindow_label_from","起始站点:");
	}
	public static String NewLineWindow_label_to(){
		return pro.getProperty("NewLineWindow_label_to","到达站点:");
	}
	public static String NewLineWindow_label_startTime(){
		return pro.getProperty("NewLineWindow_label_startTime","出发时间:");
	}
	public static String NewLineWindow_label_endTime(){
		return pro.getProperty("NewLineWindow_label_endTime","到达时间:");
	}
	public static String NewLineWindow_label_distance(){
		return pro.getProperty("NewLineWindow_label_distance", "两站距离:");
	}
	public static String NewLineWindow_label_costTime(){
		return pro.getProperty("NewLineWindow_label_costTime","旅途时长:");
	}
	public static String NewLineWindow_label_price(){
		return pro.getProperty("NewLineWindow_label_price","价格:");
	}
	public static String NewLineWindow_btn_ok(){
		return pro.getProperty("NewLineWindow_btn_ok","确认");
	}
	public static String NewLineWindow_btn_cancel(){
		return pro.getProperty("NewLineWindow_btn_cancel","取消");
	}
	public static String NewLineWindow_label_wayValue_train(){
		return pro.getProperty("NewLineWindow_label_wayValue_train","火车");
	}
	public static String NewLineWindow_label_wayValue_plane(){
		return pro.getProperty("NewLineWindow_label_wayValue_plane","飞机");
	}
	public static String NewCity_title(){
		return pro.getProperty("NewCity_title","增加新城市");
	}
	public static String NewCity_label_cityID(){
		return pro.getProperty("NewCity_label_cityID","城市编号:");
	}
	public static String NewCity_label_nameCN(){
		return pro.getProperty("NewCity_label_nameCN","中文名:");
	}
	public static String NewCity_label_nameEN(){
		return pro.getProperty("NewCity_label_nameEN","英文名:");
	}
	public static String NewCity_label_longitude(){
		return pro.getProperty("NewCity_label_longitude","经度:");
	}
	public static String NewCity_label_latitude(){
		return pro.getProperty("NewCity_label_latitude","纬度:");
	}
	public static String NewCity_btn_ok(){
		return pro.getProperty("NewCity_btn_ok","确认");
	}
	public static String NewCity_btn_cancel(){
		return pro.getProperty("NewCity_btn_cancel","取消");
	}
	public static String MainWindow_Table_cityID(){
		return pro.getProperty("MainWindow_table_cityId","城市编号");
	}
	public static String MainWindow_Table_cityNameCN(){
		return pro.getProperty("MainWindow_table_cityNameCN","中文名称");
	}
	public static String MainWindow_Table_cityNameEN(){
		return pro.getProperty("MainWindow_table_cityNameEN","英文名称");
	}
	public static String MainWindow_Table_cityLongitude(){
		return pro.getProperty("MainWindow_table_cityLongitude","经度");
	}
	public static String MainWindow_Table_cityLatitude(){
		return pro.getProperty("MainWindow_table_cityLatitude","纬度");
	}
	public static String LineWindow_table_LineID_train(){
		return pro.getProperty("LineWindow_table_LineID_train","列车编号");
	}
	public static String LineWindow_table_LineID_plane(){
		return pro.getProperty("LineWindow_table_LineID_plane","航班编号");
	}
	public static String LineWindow_table_LineFrom(){
		return pro.getProperty("LineWindow_table_LineFrom","出发站点");
	}
	public static String LineWindow_table_LineTo(){
		return pro.getProperty("LineWindow_table_LineTo","到达站点");
	}
	public static String LineWindow_table_startTime(){
		return pro.getProperty("LineWindow_table_startTime","出发时间");
	}
	public static String LineWindow_table_endTime(){
		return pro.getProperty("LineWindow_table_endTime","到站时间");
	}
	public static String LineWindow_table_distance(){
		return pro.getProperty("LineWindow_table_distance","两站距离");
	}
	public static String LineWindow_table_costTime(){
		return pro.getProperty("LineWindow_table_costTime","花费时间");
	}
	public static String LineWindow_table_price(){
		return pro.getProperty("LineWindow_table_price","价格");
	}
	public static String NewLineWindow_label_id(){
		return pro.getProperty("NewLineWindow_label_id","线路编号:");
	}
	
}

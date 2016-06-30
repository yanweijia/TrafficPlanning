package cn.yanweijia.Appearance;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cn.yanweijia.Graph.Graph;
import cn.yanweijia.Tools.Config;
import cn.yanweijia.Tools.Debug;
import cn.yanweijia.Tools.Language;
import cn.yanweijia.dao.City;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.DBHelper;
import cn.yanweijia.dao.DayTime;
import cn.yanweijia.dao.Line;
import cn.yanweijia.dao.LineList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;



public class MainWindow {
	private JPanel contentPane,panel;	//默认面板,查询面板
	private JFrame frame;
	private JButton btn_city,btn_line,btn_language;//顶部四个按钮
	private JButton btn_query,btn_clear;	//按钮:查询,清空查询结果
	private TitledBorder titledBorder;	//"查询"面板的边框
	private JLabel label_way,label_rules,label_from,label_to;	//四个标签:交通方式,决策原则,起始站点,到达站点.
	private JLabel label_sumTime,label_sumMoney,label_sumTransfer;	//三个标签:总用时,总金额,换乘次数
	private JLabel label_sumTimeValue,label_sumMoneyValue,label_sumTransferValue;	//显示 总用时,总金额,换乘次数 的标签.
	private JComboBox<String> comboBox_way,comboBox_rules,comboBox_from,comboBox_to;	//组合框:交通方式,决策原则,起始站点,到达站点
	private City[] cityArray;	//城市列表,窗口焦点获取则更新
	private JPanel panel_table;
	private JTable table;	//显示线路数据
	private JLabel label_distance;
	private JLabel label_distanceValue;
	/*
	 * 主过程
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * 构造函数
	 */
	public MainWindow() {
		//初始化配置文件
		Config.init();
		Config.readFromFile();
		
		frame = new JFrame();
		frame.setResizable(false);
		contentPane = (JPanel)frame.getContentPane();
		contentPane.setLayout(null);
		
		table = new JTable();
		
		btn_city = new JButton("City");
		btn_city.setBounds(10, 11, 120, 40);
		btn_city.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new CityWindow();
			}
		});
		contentPane.add(btn_city);
		
		btn_line = new JButton("Line");
		btn_line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LineWindow();
			}
		});
		btn_line.setBounds(234, 11, 120, 40);
		contentPane.add(btn_line);
		
		btn_language = new JButton("Language");
		btn_language.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//先将当前窗口大小位置保存,一会儿调用initialize方法会重新读取位置
				Config.setMainWindowBounds(frame.getBounds());
				//语言选择
				String[] strLanguage = new String[]{"中文-简体","English-US"};
				String choice = (String)JOptionPane.showInputDialog(null,"请选择语言(Please select your language):\n","语言选择(Language)",JOptionPane.PLAIN_MESSAGE,null,strLanguage, null);
				if(choice==null)
					return;
				if(choice.equals("中文-简体"))
					Config.setLanguage(Config.LANGUAGE_CN);
				else
					Config.setLanguage(Config.LANGUAGE_EN);
				
				Debug.log("语言被修改为:" + choice);
				
				initialize();	//重新初始化界面,更改语言
			}
		});
		btn_language.setBounds(464, 11, 120, 40);
		contentPane.add(btn_language);
		
		//查询面板
		panel = new JPanel();
		panel.setBounds(8, 61, 576, 499);
		panel.setLayout(null);
		titledBorder = BorderFactory.createTitledBorder("Query");	//主面板查询面板名字
		panel.setBorder(titledBorder);
		frame.getContentPane().add(panel);
		
		label_way = new JLabel("Way:");
		label_way.setBounds(36, 37, 85, 14);
		panel.add(label_way);
		
		label_from = new JLabel("From:");
		label_from.setBounds(36, 83, 85, 14);
		panel.add(label_from);
		
		label_rules = new JLabel("Rules:");
		label_rules.setBounds(287, 37, 85, 14);
		panel.add(label_rules);
		
		label_to = new JLabel("To:");
		label_to.setBounds(287, 83, 85, 14);
		panel.add(label_to);
		
		comboBox_way = new JComboBox<String>();
		comboBox_way.setBounds(131, 34, 115, 20);
		comboBox_way.removeAllItems();
		comboBox_way.addItem("Train");
		comboBox_way.addItem("AirPlane");
		panel.add(comboBox_way);
		
		comboBox_rules = new JComboBox<String>();
		comboBox_rules.setBounds(382, 34, 115, 20);
		comboBox_rules.removeAllItems();
		comboBox_rules.addItem("TimeFirst");
		comboBox_rules.addItem("MoneyFirst");
		comboBox_rules.addItem("DistanceFirst");
		panel.add(comboBox_rules);
		
		comboBox_from = new JComboBox<String>();
		comboBox_from.setBounds(131,80,115,20);
		panel.add(comboBox_from);
		
		comboBox_to = new JComboBox<String>();
		comboBox_to.setBounds(382,80,115,20);
		panel.add(comboBox_to);
		
		
		
		btn_query = new JButton("Query");
		btn_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String from = (String) comboBox_from.getSelectedItem();
				String to = (String) comboBox_to.getSelectedItem();
				if(from==null || to==null){
					//这里的提示语言从语言选择类中获取
					JOptionPane.showMessageDialog(null, "站点读取失败,请选择出发站点和到达站点!(Please Select the Node)","警告:",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(from.equals(to)){
					//提示用户出发站点和到达站点不能够一样!
					JOptionPane.showMessageDialog(null, "出发站点和到达站点不能够一样,请重新选择!(Same error:origin station and object station!)","警告:",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//把CityID找出来
				int id_from = 1,id_to = 3;
				for(int i = 0 ; i < cityArray.length ; i++){
					if((Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN).equals(from))
						id_from = cityArray[i].id;
				}
				for(int i = 0 ; i < cityArray.length ; i++){
					if((Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN).equals(to))
						id_to = cityArray[i].id;
				}
				
				DBHelper dbHelper = new DBHelper();
				CityList cityList = dbHelper.getAllCitys();
				LineList lineList;
				if(comboBox_way.getSelectedIndex()==0)
					lineList = dbHelper.getAllLineList_Train();
				else
					lineList = dbHelper.getAllLineList_Plane();
				dbHelper.close();
				//这里调用核心代码来计算数据
				LineList lineListResult = Graph.getLineList(cityList, lineList, id_from,id_to,(comboBox_rules.getSelectedIndex()==0)?Graph.PRINCPLE_DISTANCE:Graph.PRINCPLE_PRICE);
				
				DefaultTableModel tableModel = getNewTableModel();
				Iterator<Line> iterator = lineListResult.getList().iterator();
				double price = 0.0d;
				double distance = 0.0d;
				DayTime sumTime = new DayTime("00:00");
				while(iterator.hasNext()){
					Line line = iterator.next();
					if(line==null)
						continue;
					Object value[] = new Object[8];
					value[0] = line.ID;
					value[1] = Config.getLanguage()==Config.LANGUAGE_CN?cityList.getCity(line.lineFrom).nameCN:cityList.getCity(line.lineFrom).nameEN;
					value[2] = Config.getLanguage()==Config.LANGUAGE_CN?cityList.getCity(line.lineTo).nameCN:cityList.getCity(line.lineTo).nameEN;
					value[3] = line.startTime.toString();
					value[4] = line.endTime.toString();
					value[5] = line.distance;
					value[6] = line.costTime.toString();
					value[7] = line.price;
					tableModel.addRow(value);
					
					price += line.price;
					distance += line.distance;
					sumTime = sumTime.add(line.endTime.sub(line.startTime).add(line.costTime));
				}
				table.setModel(tableModel);
				label_distanceValue.setText("" + distance);
				label_sumMoneyValue.setText("" + price);
				label_sumTimeValue.setText(sumTime.toStringWithDay());
				label_sumTransferValue.setText("" + tableModel.getRowCount());
				
				Debug.log("查询成功!");
			}
		});
		btn_query.setBounds(377, 111, 120, 28);
		panel.add(btn_query);
		
		btn_clear = new JButton("ClearResult");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tableModel = getNewTableModel();
				table.setModel(tableModel);
				label_distanceValue.setText("");
				label_sumMoneyValue.setText("");
				label_sumTimeValue.setText("");
				label_sumTransferValue.setText("");
			}
		});
		btn_clear.setBounds(36, 111, 120, 28);
		panel.add(btn_clear);
		
		label_sumTime = new JLabel("SumTime:");
		label_sumTime.setBounds(10, 454, 50, 20);
		panel.add(label_sumTime);
		
		label_sumMoney = new JLabel("SumMoney:");
		label_sumMoney.setBounds(146, 454, 55, 20);
		panel.add(label_sumMoney);
		
		label_sumTransfer = new JLabel("TransferNum:");
		label_sumTransfer.setBounds(416, 454, 70, 20);
		panel.add(label_sumTransfer);
		
		label_sumTimeValue = new JLabel("time");
		label_sumTimeValue.setBounds(70, 454, 70, 20);
		panel.add(label_sumTimeValue);
		
		label_sumMoneyValue = new JLabel("money");
		label_sumMoneyValue.setBounds(211, 454, 70, 20);
		panel.add(label_sumMoneyValue);
		
		label_sumTransferValue = new JLabel("transfer");
		label_sumTransferValue.setBounds(496, 454, 70, 20);
		panel.add(label_sumTransferValue);
		
		panel_table = new JPanel();
		panel_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_table.setBounds(10, 150, 556, 296);
		panel.add(panel_table);
		
		panel_table.add(new JScrollPane(table));
		
		label_distance = new JLabel("diatance");
		label_distance.setBounds(271, 454, 61, 20);
		panel.add(label_distance);
		
		label_distanceValue = new JLabel("diatance");
		label_distanceValue.setBounds(342, 454, 50, 20);
		panel.add(label_distanceValue);
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Debug.log("窗口关闭");
				Rectangle rec = frame.getBounds();
				Config.setMainWindowBounds(rec);
				
				
				System.exit(0);
			}
			@Override
			public void windowActivated(WindowEvent e) {
				//窗口获取焦点,被激活自动刷新城市数据
				reloadCityList();
			}
		});


		initialize();	//初始化代码,同时负责中英文显示.
	}

	/*
	 * 初始化代码,从配置文件中读取数据来更新窗口外观位置等.
	 */
	private void initialize() {
		Language.init();	//初始化语言文件
		//窗口大小和位置读取上次关闭保存的值
		frame.setBounds(Config.getMainWindowBounds());
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口标题,设置各个控件的语言文字
		frame.setTitle(Language.MainWindow_title());
		btn_city.setText(Language.MainWindow_btn_city());
		btn_line.setText(Language.MainWindow_btn_line());
		btn_language.setText(Language.MainWindow_btn_language());
		titledBorder.setTitle(Language.MainWindow_titledBorder());
		label_way.setText(Language.MainWindow_label_way());
		label_rules.setText(Language.MainWindow_label_rules());
		label_from.setText(Language.MainWindow_label_from());
		label_to.setText(Language.MainWindow_label_to());
		btn_query.setText(Language.MainWindow_btn_query());
		btn_clear.setText(Language.MainWindow_btn_clear());
		comboBox_way.removeAllItems();
		comboBox_way.addItem(Language.MainWindow_comboBox_way_train());
		comboBox_way.addItem(Language.MainWindow_comboBox_way_plane());
		comboBox_rules.removeAllItems();
		comboBox_rules.addItem(Language.MainWindow_comboBox_rules_distanceFirst());
		comboBox_rules.addItem(Language.MainWindow_comboBox_rules_moneyFirst());
		comboBox_rules.addItem(Language.MainWindow_comboBox_rules_timeFirst());
		label_sumTime.setText(Language.MainWindow_label_sumTime());
		label_sumMoney.setText(Language.MainWindow_label_sumMoney());
		label_sumTransfer.setText(Language.MainWindow_label_sumTransfer());
		label_distance.setText(Language.MainWindow_label_distance());
		label_distanceValue.setText("");
		label_sumMoneyValue.setText("");
		label_sumTimeValue.setText("");
		label_sumTransferValue.setText("");
	}
	//重新加载城市列表
	private void reloadCityList(){
		DBHelper dbHelper = new DBHelper();
		CityList list = null;
		list = dbHelper.getAllCitys();
		dbHelper.close();
		cityArray = new City[list.getSize()];
		Iterator<City> iterator = list.getList().iterator();
		comboBox_from.removeAllItems();
		comboBox_to.removeAllItems();
		for(int i = 0 ; iterator.hasNext() ; i++){
			cityArray[i] = (City)iterator.next();
			comboBox_from.addItem(Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN);
			comboBox_to.addItem(Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN);
		}
	}
	private DefaultTableModel getNewTableModel(){
		String line_id,line_from,line_to,startTime,endTime,distance,costTime,price;
		line_id = (comboBox_way.getSelectedIndex()==0)?Language.LineWindow_table_LineID_train():Language.LineWindow_table_LineID_plane();
		line_from = Language.LineWindow_table_LineFrom();
		line_to = Language.LineWindow_table_LineTo();
		startTime = Language.LineWindow_table_startTime();
		endTime = Language.LineWindow_table_endTime();
		distance = Language.LineWindow_table_distance();
		costTime = Language.LineWindow_table_costTime();
		price = Language.LineWindow_table_price();
		
		Object name[] = {line_id,line_from,line_to,startTime,endTime,distance,costTime,price};
		DefaultTableModel tableModel = new DefaultTableModel(name, 0);
		return tableModel;
	}
}

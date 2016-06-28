package cn.yanweijia.Appearance;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import cn.yanweijia.dao.Config;
import cn.yanweijia.dao.Debug;
import cn.yanweijia.dao.Language;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;



public class MainWindow {
	private JPanel contentPane,panel;	//默认面板,查询面板
	private JFrame frame;
	private JButton btn_city,btn_line,btn_history,btn_language;//顶部四个按钮
	private JButton btn_query,btn_clear;	//按钮:查询,清空查询结果
	private TitledBorder titledBorder;	//"查询"面板的边框
	private JLabel label_way,label_rules,label_from,label_to;	//四个标签:交通方式,决策原则,起始站点,到达站点.
	private JLabel label_sumTime,label_sumMoney,label_sumTransfer;	//三个标签:总用时,总金额,换乘次数
	private JLabel label_sumTimeValue,label_sumMoneyValue,label_sumTransferValue;	//显示 总用时,总金额,换乘次数 的标签.
	private JComboBox<String> comboBox_way,comboBox_rules,comboBox_from,comboBox_to;	//组合框:交通方式,决策原则,起始站点,到达站点
	private JTable table;
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
		btn_line.setBounds(164, 11, 120, 40);
		contentPane.add(btn_line);
		
		btn_history = new JButton("History");
		btn_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HistoryWindow();
			}
		});
		btn_history.setBounds(316, 11, 120, 40);
		contentPane.add(btn_history);
		
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
		comboBox_rules.addItem("transferFirst");
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
					//TODO:这里的提示语言从语言选择类中获取
					JOptionPane.showMessageDialog(null, "站点读取失败,请选择出发站点和到达站点!","警告:",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(from.equals(to)){
					//提示用户出发站点和到达站点不能够一样!
					JOptionPane.showMessageDialog(null, "出发站点和到达站点不能够一样,请重新选择!","警告:",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//TODO:这里调用核心代码来计算数据
			}
		});
		btn_query.setBounds(377, 111, 120, 28);
		panel.add(btn_query);
		
		btn_clear = new JButton("ClearResult");
		btn_clear.setBounds(36, 111, 120, 28);
		panel.add(btn_clear);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));	//布局边界_分界线
		table.setBounds(10, 150, 556, 282);
		panel.add(table);
		
		label_sumTime = new JLabel("SumTime:");
		label_sumTime.setBounds(10, 454, 70, 20);
		panel.add(label_sumTime);
		
		label_sumMoney = new JLabel("SumMoney:");
		label_sumMoney.setBounds(176, 454, 70, 20);
		panel.add(label_sumMoney);
		
		label_sumTransfer = new JLabel("TransferNum:");
		label_sumTransfer.setBounds(377, 454, 95, 20);
		panel.add(label_sumTransfer);
		
		label_sumTimeValue = new JLabel("time");
		label_sumTimeValue.setBounds(86, 454, 70, 20);
		panel.add(label_sumTimeValue);
		
		label_sumMoneyValue = new JLabel("money");
		label_sumMoneyValue.setBounds(256, 454, 70, 20);
		panel.add(label_sumMoneyValue);
		
		label_sumTransferValue = new JLabel("transfer");
		label_sumTransferValue.setBounds(482, 454, 70, 20);
		panel.add(label_sumTransferValue);
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Debug.log("窗口关闭");
				Rectangle rec = frame.getBounds();
				Config.setMainWindowBounds(rec);
				
				
				System.exit(0);
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
		btn_history.setText(Language.MainWindow_btn_history());
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
		comboBox_rules.addItem(Language.MainWindow_comboBox_rules_timeFirst());
		comboBox_rules.addItem(Language.MainWindow_comboBox_rules_moneyFirst());
		comboBox_rules.addItem(Language.MainWindow_comboBox_rules_transferFirst());
		label_sumTime.setText(Language.MainWindow_label_sumTime());
		label_sumMoney.setText(Language.MainWindow_label_sumMoney());
		label_sumTransfer.setText(Language.MainWindow_label_sumTransfer());
	}
}

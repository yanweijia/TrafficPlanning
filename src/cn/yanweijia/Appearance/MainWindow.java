package cn.yanweijia.Appearance;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;



public class MainWindow {
	private JPanel contentPane,panel;	//默认面板,查询面板
	private JFrame frame;
	private JButton btn_city,btn_line,btn_history,btn_language;//顶部四个按钮
	private JButton btn_query,btn_clear;	//按钮:查询,清空查询结果
	private TitledBorder titledBorder;	//"查询"面板的边框
	private JLabel label_way,label_rules,label_from,label_to;	//四个标签:交通方式,决策原则,起始站点,到达站点.
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
				//TODO:语言选择
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
		table.setBounds(10, 150, 556, 282);
		panel.add(table);
		

		
		
		//初始化代码
		initialize();
	}

	/*
	 * 初始化代码,从配置文件中读取数据来更新窗口外观位置等.
	 */
	private void initialize() {
		//TODO:窗口大小和位置读取上次关闭保存的值
		frame.setBounds(100, 100, 600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TODO:设置窗口标题,设置各个控件的语言文字
		frame.setTitle("全国交通咨询模拟   20140712 Yan Weijia");
		btn_city.setText("城市线路");
		btn_line.setText("线路规划");
		btn_history.setText("查询历史");
		btn_language.setText("语言选择");
		titledBorder.setTitle("查询");
		label_way.setText("交通方式:");
		label_rules.setText("决策原则:");
		label_from.setText("起始站点:");
		label_to.setText("到达站点:");
		btn_query.setText("查询");
		btn_clear.setText("清空查询结果");
		comboBox_way.removeAllItems();
		comboBox_way.addItem("火车");
		comboBox_way.addItem("飞机");
		comboBox_rules.removeAllItems();
		comboBox_rules.addItem("时间最短");
		comboBox_rules.addItem("价格最少");
		comboBox_rules.addItem("最少换乘");
		
	}
}

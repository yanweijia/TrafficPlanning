package cn.yanweijia.Appearance;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.yanweijia.Tools.Config;
import cn.yanweijia.Tools.Debug;
import cn.yanweijia.Tools.Language;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.DBHelper;
import cn.yanweijia.dao.Line;
import cn.yanweijia.dao.LineList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class LineWindow extends JFrame {
	private static final long serialVersionUID = 1L;	//序列化ID

	private JPanel contentPane;
	private JLabel label_line;	//线路标签
	private JComboBox<String> comboBox;	
	private JButton btn_addNew,btn_delete;
	private JPanel panel_table;//放ScrollPane和table的面板
	private JTable table;	//表格,放线路数据
	
	public LineWindow() {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_line = new JLabel("Line:");
		label_line.setBounds(26, 11, 80, 35);
		contentPane.add(label_line);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(140, 18, 101, 20);
		comboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){//当选中一项的时候触发
					//TODO:comboBox选中
					showLineDatas(comboBox.getSelectedIndex());
				}
			}
			
		});
		contentPane.add(comboBox);
		
		btn_addNew = new JButton("Add New");
		btn_addNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int wayValue = 0;	//如果为0:火车   1:飞机
				wayValue = comboBox.getSelectedIndex();
				new NewLineWindow(wayValue);
			}
		});
		btn_addNew.setBounds(308, 12, 115, 34);
		contentPane.add(btn_addNew);
		
		btn_delete = new JButton("Delete Selected");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//删除选中线路
				DBHelper dbHelper = new DBHelper();
				String lineID = table.getValueAt(table.getSelectedRow(), 0).toString();
				boolean flag = false;
				if(comboBox.getSelectedIndex()==0)
					flag = dbHelper.deleteLine_Train(lineID);
				else
					flag = dbHelper.deleteLine_Plane(lineID);
				if(flag)
					JOptionPane.showMessageDialog(LineWindow.this, "删除成功!(Deleted)");
				else
					JOptionPane.showMessageDialog(LineWindow.this, "删除失败!(Cannot delete this line)");
			}
		});
		btn_delete.setBounds(490, 12, 115, 34);
		contentPane.add(btn_delete);
		
		panel_table = new JPanel();
		panel_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_table.setBounds(10, 50, 674, 510);
		contentPane.add(panel_table);
		
		this.addWindowFocusListener(new WindowFocusListener(){
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				Debug.log("LineWindow获取焦点");
				//窗口重新获取焦点,重新读取线路数据并显示
				showLineDatas(comboBox.getSelectedIndex());
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {Debug.log("LineWindow窗口失去焦点");}
		});

		table = new JTable();
		table.setRowHeight(20);
		panel_table.add(new JScrollPane(table), BorderLayout.CENTER);
		
		initialize();
	}
	
	/*
	 * 初始化
	 */
	private void initialize(){
		Language.init();
		this.setTitle(Language.LineWindow_title());
		label_line.setText(Language.LineWindow_label_line());
		comboBox.removeAllItems();
		comboBox.addItem(Language.LineWindow_comboBox_train());
		comboBox.addItem(Language.LineWindow_comboBox_plane());
		btn_addNew.setText(Language.LineWindow_btn_addNew());
		btn_delete.setText(Language.LineWindow_btn_delete());
		//showLineDatas();	//读取线路数据并显示到table中  ,窗口获取焦点会自动运行
	}
	//重新根据comboBox状态 读取指定线路数据,并更新到
	private void showLineDatas(int lineType){
		String line_id,line_from,line_to,startTime,endTime,distance,costTime,price;
		line_id = (lineType==0)?Language.LineWindow_table_LineID_train():Language.LineWindow_table_LineID_plane();
		line_from = Language.LineWindow_table_LineFrom();
		line_to = Language.LineWindow_table_LineTo();
		startTime = Language.LineWindow_table_startTime();
		endTime = Language.LineWindow_table_endTime();
		distance = Language.LineWindow_table_distance();
		costTime = Language.LineWindow_table_costTime();
		price = Language.LineWindow_table_price();
		
		Object name[] = {line_id,line_from,line_to,startTime,endTime,distance,costTime,price};
		DefaultTableModel tableModel = new DefaultTableModel(name, 0);
		//Object value[][];	//表格元素
		DBHelper dbHelper = new DBHelper();
		CityList cityList = dbHelper.getAllCitys();
		LineList list = (lineType==0)?dbHelper.getAllLineList_Train():dbHelper.getAllLineList_Plane();
		dbHelper.close();
		//int initRows = list.getSize();
		//value = new Object[initRows][name.length];
		Iterator<Line> iterator = list.getList().iterator();
		while(iterator.hasNext()){
			Line line = iterator.next();
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
		}
		table.setModel(tableModel);
		
		Debug.log("拉取线路数据成功");
		
	}
}

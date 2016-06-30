package cn.yanweijia.Appearance;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import cn.yanweijia.Tools.Debug;
import cn.yanweijia.Tools.Language;
import cn.yanweijia.dao.City;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.DBHelper;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CityWindow extends JFrame {
	private static final long serialVersionUID = 1L;	//序列化ID
	private JPanel contentPane;
	private JButton btn_add,btn_delete;	//按钮:增加新城市,删除新城市
	private JPanel panel_table; //放ScrollPanel和table的panel
	private JTable table;	//放数据
	public CityWindow() {
		Debug.log("城市编辑窗口被打开");
		setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//增加新城市
				new NewCity();
			}
		});
		btn_add.setBounds(64, 11, 124, 36);
		contentPane.add(btn_add);
		
		btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//删除选中城市
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(CityWindow.this, "请先选中一行数据再进行删除\n(Please Select A Line)!");
				}else{
					int cityID = Integer.parseInt(table.getValueAt(row, 0).toString());
					DBHelper dbHelper = new DBHelper();
					boolean flag = dbHelper.deleteCity(cityID);
					if(flag)
						JOptionPane.showMessageDialog(CityWindow.this, "删除成功(Success)!");
					else
						JOptionPane.showMessageDialog(CityWindow.this, "删除指定城市失败(Fail),请检查线路里面是否已将关于该城市的线路删除!");
				}
			}
		});
		btn_delete.setBounds(277, 11, 124, 36);
		contentPane.add(btn_delete);
		
		panel_table = new JPanel();
		panel_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_table.setBounds(10, 60, 474, 450);
		contentPane.add(panel_table);
		
		this.addWindowFocusListener(new WindowFocusListener(){
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				Debug.log("CityWindow获取焦点");
				//窗口重新获取焦点,重新读取城市数据并显示
				showCityDatas();
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
		//TODO:用配置项类来获取信息
		this.setBounds(100, 100, 500, 550);
		Language.init();
		this.setTitle(Language.CityWindow_title());
		btn_add.setText(Language.CityWindow_btn_add());
		btn_delete.setText(Language.CityWindow_btn_delete());
		
		//showCityDatas();	//读取城市数据并更新到table中,窗口获取焦点的时候回自动读取
	}
	//读取城市数据并更新到table中
	private void showCityDatas(){
		//Object name[] = { "城市编号", "中文名", "英文名", "经度","纬度"};
		String cityID,cityNameCN,cityNameEN,cityLongitude,cityLatitude;
		cityID = Language.MainWindow_Table_cityID();
		cityNameCN = Language.MainWindow_Table_cityNameCN();
		cityNameEN = Language.MainWindow_Table_cityNameEN();
		cityLongitude = Language.MainWindow_Table_cityLongitude();
		cityLatitude = Language.MainWindow_Table_cityLatitude();

		
		//表格表头
		Object name[] = {cityID,cityNameCN,cityNameEN,cityLongitude,cityLatitude};
		DefaultTableModel tableModel = new DefaultTableModel(name, 0);
		//Object value[][];	//表格元素
		DBHelper dbHelper = new DBHelper();
		CityList list = dbHelper.getAllCitys();
		dbHelper.close();
		//int initRows = list.getSize();
		//value = new Object[initRows][name.length];
		Iterator<City> iterator = list.getList().iterator();
		while(iterator.hasNext()){
			City city = iterator.next();
			Object value[] = new Object[5];
			value[0] = city.id;
			value[1] = city.nameCN;
			value[2] = city.nameEN;
			value[3] = city.longitude;
			value[4] = city.latitude;
			tableModel.addRow(value);
		}
//		for(int i = 0 ; i < value.length ; i++){	//测试是否正确读出数据
//			for(int j = 0 ; j < value[i].length ; j++)
//				System.out.print(" " + value[i][j]);
//			System.out.println();
//		}
		table.setModel(tableModel);
		Debug.log("拉取城市数据信息成功!");
	}
}

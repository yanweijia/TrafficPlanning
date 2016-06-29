package cn.yanweijia.Appearance;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cn.yanweijia.Tools.Config;
import cn.yanweijia.Tools.Language;
import cn.yanweijia.dao.City;
import cn.yanweijia.dao.CityList;
import cn.yanweijia.dao.DBHelper;
import cn.yanweijia.dao.DayTime;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class NewLineWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_startTime,textField_endTime,textField_distance,textField_price;	//文本框: 发车时间,到站时间,两站距离,价格,花费时间
	private JComboBox<String> comboBox_from,comboBox_to;	//组合框: 起始站点,到达站点
	private JButton btn_ok,btn_cancel;	//按钮: 确认,取消
	//标签: 线路类型,起始站点,出发时间,终点站,到站时间,距离,时长,价格
	private JLabel label_way,label_from,label_to,label_startTime,label_endTime,label_distance,label_price;
	private JLabel label_wayValue,label_id;	//线路类型的值 , 线路编号
	private int wayValue;	//为0:火车,为1:飞机
	private JTextField textField_id;
	private City[] cityArray;
	
	
	
	public NewLineWindow(int wayValue) {
		this.wayValue=wayValue;
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 333, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		label_way = new JLabel("Way:");
		label_way.setBounds(38, 11, 119, 14);
		contentPane.add(label_way);
		
		label_wayValue = new JLabel("Train");
		label_wayValue.setBounds(167, 11, 119, 14);
		contentPane.add(label_wayValue);
		
		label_from = new JLabel("From:");
		label_from.setBounds(38, 81, 119, 14);
		contentPane.add(label_from);
		
		comboBox_from = new JComboBox<String>();
		comboBox_from.setBounds(167, 78, 119, 20);
		contentPane.add(comboBox_from);
		
		label_startTime = new JLabel("StartTime:");
		label_startTime.setBounds(38, 125, 119, 14);
		contentPane.add(label_startTime);
		
		label_to = new JLabel("To:");
		label_to.setBounds(38, 167, 119, 14);
		contentPane.add(label_to);
		
		label_endTime = new JLabel("EndTime:");
		label_endTime.setBounds(38, 213, 119, 14);
		contentPane.add(label_endTime);
		
		label_distance = new JLabel("Distance:");
		label_distance.setBounds(38, 255, 119, 14);
		contentPane.add(label_distance);
		
		label_price = new JLabel("Price:");
		label_price.setBounds(38, 301, 119, 14);
		contentPane.add(label_price);
		
		btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断是否已有线路,若没有,添加新线路,若有(意思是距离,价格,发车时间等全部一样),提示用户已有该条线路
				String lineID,line_from,line_to,startTime,endTime,distance,costTime,price;
				lineID = textField_id.getText();
				line_from = comboBox_from.getSelectedIndex()==-1?"":comboBox_from.getSelectedItem().toString();
				line_to = comboBox_to.getSelectedIndex()==-1?"":comboBox_to.getSelectedItem().toString();
				if(line_from.equals(line_to)){
					JOptionPane.showMessageDialog(NewLineWindow.this, "出发站和到达站不能是同一站点!");
					return;
				}
				//把站点名称对应中文找回来
				for(int i = 0 ; i < cityArray.length ; i++){
					if((Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN).equals(line_from))
						line_from = "" + cityArray[i].id;
				}
				for(int i = 0 ; i < cityArray.length ; i++){
					if((Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN).equals(line_to))
						line_to = "" + cityArray[i].id;
				}
				startTime = textField_startTime.getText();
				endTime = textField_endTime.getText();
				costTime = new DayTime(endTime).sub(new DayTime(startTime)).toString();
				distance = textField_distance.getText();
				price = textField_price.getText();
				DBHelper dbHelper = new DBHelper();
				boolean flag = dbHelper.addLine((wayValue==0)?DBHelper.LINE_TRAIN:DBHelper.LINE_PLANE, lineID, Integer.parseInt(line_from), Integer.parseInt(line_to), new DayTime(startTime), new DayTime(endTime), Double.parseDouble(distance), new DayTime(costTime), Double.parseDouble(price));
				dbHelper.close();
				if(flag){
					JOptionPane.showMessageDialog(NewLineWindow.this, "添加成功!(Success)");
					NewLineWindow.this.dispose();
				}
				else
					JOptionPane.showMessageDialog(NewLineWindow.this,"添加失败!(Fail)");
				
			}
		});
		btn_ok.setBounds(38, 344, 119, 36);
		contentPane.add(btn_ok);
		
		btn_cancel = new JButton("CANCEL");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewLineWindow.this.dispose();	//关闭当前窗口
			}
		});
		btn_cancel.setBounds(167, 344, 119, 36);
		contentPane.add(btn_cancel);
		
		comboBox_to = new JComboBox<String>();
		comboBox_to.setBounds(167, 164, 119, 20);
		contentPane.add(comboBox_to);
		
		textField_startTime = new JTextField();
		textField_startTime.setHorizontalAlignment(SwingConstants.CENTER);
		textField_startTime.setBounds(167, 122, 119, 20);
		contentPane.add(textField_startTime);
		textField_startTime.setColumns(10);
		
		textField_endTime = new JTextField();
		textField_endTime.setHorizontalAlignment(SwingConstants.CENTER);
		textField_endTime.setColumns(10);
		textField_endTime.setBounds(167, 210, 119, 20);
		contentPane.add(textField_endTime);
		
		textField_distance = new JTextField();
		textField_distance.setHorizontalAlignment(SwingConstants.CENTER);
		textField_distance.setColumns(10);
		textField_distance.setBounds(167, 252, 86, 20);
		contentPane.add(textField_distance);
		
		JLabel label_km = new JLabel("KM");
		label_km.setHorizontalAlignment(SwingConstants.CENTER);
		label_km.setBounds(263, 255, 23, 14);
		contentPane.add(label_km);
		
		textField_price = new JTextField();
		textField_price.setHorizontalAlignment(SwingConstants.CENTER);
		textField_price.setColumns(10);
		textField_price.setBounds(167, 298, 86, 20);
		contentPane.add(textField_price);
		
		JLabel label_rmb = new JLabel("¥");
		label_rmb.setHorizontalAlignment(SwingConstants.CENTER);
		label_rmb.setBounds(263, 301, 23, 14);
		contentPane.add(label_rmb);
		
		label_id = new JLabel("no:");
		label_id.setBounds(38, 42, 119, 14);
		contentPane.add(label_id);
		
		textField_id = new JTextField();
		textField_id.setHorizontalAlignment(SwingConstants.CENTER);
		textField_id.setColumns(10);
		textField_id.setBounds(167, 39, 119, 20);
		contentPane.add(textField_id);
		initizalize();
	}
	
	//初始化界面语言
	private void initizalize(){
		Language.init();
		this.setTitle(Language.NewLineWindow_title());
		btn_ok.setText(Language.NewLineWindow_btn_ok());
		btn_cancel.setText(Language.NewLineWindow_btn_cancel());
		label_id.setText(Language.NewLineWindow_label_id());
		label_way.setText(Language.NewLineWindow_label_way());
		label_from.setText(Language.NewLineWindow_label_from());
		label_to.setText(Language.NewLineWindow_label_to());
		label_startTime.setText(Language.NewLineWindow_label_startTime());
		label_endTime.setText(Language.NewLineWindow_label_endTime());
		label_distance.setText(Language.NewLineWindow_label_distance());
		label_price.setText(Language.NewLineWindow_label_price());
		
		
		if(wayValue==0){
			label_wayValue.setText(Language.NewLineWindow_label_wayValue_train());
		}
		else{
			label_wayValue.setText(Language.NewLineWindow_label_wayValue_plane());
		}
		DBHelper dbHelper = new DBHelper();
		CityList list = null;
		list = dbHelper.getAllCitys();
		dbHelper.close();
		cityArray = new City[list.getSize()];
		Iterator<City> iterator = list.getList().iterator();
		for(int i = 0 ; iterator.hasNext() ; i++){
			cityArray[i] = (City)iterator.next();
			comboBox_from.addItem(Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN);
			comboBox_to.addItem(Config.getLanguage()==Config.LANGUAGE_CN?cityArray[i].nameCN:cityArray[i].nameEN);
		}
		

	}
}

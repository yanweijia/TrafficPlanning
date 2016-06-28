package cn.yanweijia.Appearance;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.yanweijia.dao.Language;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewCity extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//文本框: 中文名,英文名,经度,纬度,城市编号
	private JTextField textField_nameCN,textField_nameEN,textField_longitude,textField_latitude,textField_cityID;
	//标签: 中文名,英文名,经度,纬度,城市编号
	private JLabel label_nameCN,label_nameEN,label_longitude,label_latitude,label_cityID;
	private JButton btn_ok,btn_cancel;	//按钮: 确认,取消



	public NewCity() {
		Language.init();
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_ok = new JButton("OK");
		btn_ok.setBounds(45, 305, 125, 50);
		contentPane.add(btn_ok);
		
		btn_cancel = new JButton("CANCEL");
		btn_cancel.setBounds(180, 305, 125, 50);
		contentPane.add(btn_cancel);
		
		label_nameCN = new JLabel("Name(CN):");
		label_nameCN.setBounds(45, 87, 125, 21);
		contentPane.add(label_nameCN);
		
		label_nameEN = new JLabel("Name(EN):");
		label_nameEN.setBounds(45, 139, 125, 21);
		contentPane.add(label_nameEN);
		
		label_longitude = new JLabel("Longitude:");
		label_longitude.setBounds(45, 192, 125, 21);
		contentPane.add(label_longitude);
		
		label_latitude = new JLabel("Latitude:");
		label_latitude.setBounds(45, 244, 125, 21);
		contentPane.add(label_latitude);
		
		textField_nameCN = new JTextField();
		textField_nameCN.setHorizontalAlignment(SwingConstants.CENTER);
		textField_nameCN.setBounds(180, 87, 125, 20);
		contentPane.add(textField_nameCN);
		textField_nameCN.setColumns(10);
		
		textField_nameEN = new JTextField();
		textField_nameEN.setHorizontalAlignment(SwingConstants.CENTER);
		textField_nameEN.setColumns(10);
		textField_nameEN.setBounds(180, 139, 125, 20);
		contentPane.add(textField_nameEN);
		
		textField_longitude = new JTextField();
		textField_longitude.setHorizontalAlignment(SwingConstants.CENTER);
		textField_longitude.setColumns(10);
		textField_longitude.setBounds(180, 192, 125, 20);
		contentPane.add(textField_longitude);
		
		textField_latitude = new JTextField();
		textField_latitude.setHorizontalAlignment(SwingConstants.CENTER);
		textField_latitude.setColumns(10);
		textField_latitude.setBounds(180, 244, 125, 20);
		contentPane.add(textField_latitude);
		
		label_cityID = new JLabel("CityID:");
		label_cityID.setBounds(45, 38, 125, 21);
		contentPane.add(label_cityID);
		
		textField_cityID = new JTextField();
		textField_cityID.setHorizontalAlignment(SwingConstants.CENTER);
		textField_cityID.setColumns(10);
		textField_cityID.setBounds(180, 39, 125, 20);
		contentPane.add(textField_cityID);
		initialize();
	}
	
	//初始化,中英文自动载入
	private void initialize(){
		this.setTitle(Language.NewCity_title());
		label_cityID.setText(Language.NewCity_label_cityID());
		label_nameCN.setText(Language.NewCity_label_nameCN());
		label_nameEN.setText(Language.NewCity_label_nameEN());
		label_longitude.setText(Language.NewCity_label_longitude());
		label_latitude.setText(Language.NewCity_label_latitude());
		btn_ok.setText(Language.NewCity_btn_ok());
		btn_cancel.setText(Language.NewCity_btn_cancel());
	}

}

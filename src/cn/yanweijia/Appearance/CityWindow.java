package cn.yanweijia.Appearance;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.yanweijia.dao.Debug;
import cn.yanweijia.dao.Language;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;


public class CityWindow extends JFrame {
	private static final long serialVersionUID = 1L;	//序列化ID
	private JPanel contentPane;
	private JTable table;
	private JButton btn_add,btn_delete;	//按钮:增加新城市,删除新城市

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
				//TODO:增加新城市
				new NewCity();
			}
		});
		btn_add.setBounds(64, 18, 124, 36);
		contentPane.add(btn_add);
		
		btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO:删除选中城市
			}
		});
		btn_delete.setBounds(277, 18, 124, 36);
		contentPane.add(btn_delete);
		
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
		table.setBounds(10, 69, 474, 405);
		contentPane.add(table);
		initialize();
	}
	/*
	 * 初始化
	 */
	private void initialize(){
		//TODO:用配置项类来获取信息
		this.setBounds(100, 100, 500, 514);
		Language.init();
		this.setTitle(Language.CityWindow_title());
		btn_add.setText(Language.CityWindow_btn_add());
		btn_delete.setText(Language.CityWindow_btn_delete());
		
		//showCityDatas();	//读取城市数据并更新到table中,窗口获取焦点的时候回自动读取
	}
	//读取城市数据并更新到table中
	private void showCityDatas(){
		
	}
}

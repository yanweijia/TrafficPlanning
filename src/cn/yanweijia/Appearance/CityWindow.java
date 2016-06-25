package cn.yanweijia.Appearance;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CityWindow extends JFrame {

	private JPanel contentPane;

	public CityWindow() {
		setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		initialize();
	}
	/*
	 * 初始化
	 */
	private void initialize(){
		//TODO:用配置项类来获取信息
		this.setTitle("城市信息维护");
	}

}

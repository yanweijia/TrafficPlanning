package cn.yanweijia.Appearance;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LineWindow extends JFrame {

	private JPanel contentPane;


	/*
	 * 
	 */
	public LineWindow() {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		this.setTitle("交通线路维护");
	}

}

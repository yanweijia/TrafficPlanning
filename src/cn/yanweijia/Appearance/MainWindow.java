package cn.yanweijia.Appearance;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainWindow {

	private JFrame frame;
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
		initialize();
	}

	/*
	 * 初始化代码
	 */
	private void initialize() {
		//TODO:窗口大小和位置读取上次关闭保存的值
		frame.setBounds(100, 100, 619, 455);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

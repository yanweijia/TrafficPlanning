package cn.yanweijia.Appearance;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainWindow {

	private JFrame frame;
	/*
	 * ������
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
	 * ���캯��
	 */
	public MainWindow() {
		frame = new JFrame();
		initialize();
	}

	/*
	 * ��ʼ������
	 */
	private void initialize() {
		//TODO:���ڴ�С��λ�ö�ȡ�ϴιرձ����ֵ
		frame.setBounds(100, 100, 619, 455);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

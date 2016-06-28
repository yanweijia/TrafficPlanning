package cn.yanweijia.Appearance;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cn.yanweijia.dao.Debug;
import cn.yanweijia.dao.Language;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LineWindow extends JFrame {
	private static final long serialVersionUID = 1L;	//序列化ID

	private JPanel contentPane;
	private JLabel label_line;	//线路标签
	private JComboBox<String> comboBox;	
	private JButton btn_addNew,btn_delete;
	private JTable table;
	
	
	
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
		btn_delete.setBounds(490, 12, 115, 34);
		contentPane.add(btn_delete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 674, 497);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		this.addWindowFocusListener(new WindowFocusListener(){
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				Debug.log("LineWindow获取焦点");
				//窗口重新获取焦点,重新读取线路数据并显示
				showLineDatas();
			}
			@Override
			public void windowLostFocus(WindowEvent arg0) {Debug.log("LineWindow窗口失去焦点");}
		});

		
		
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
		table.setBackground(new Color(255,255,255));
		//showLineDatas();	//读取线路数据并显示到table中  ,窗口获取焦点会自动运行
	}
	//重新根据comboBox状态 读取指定线路数据,并更新到
	private void showLineDatas(){
		
	}
}

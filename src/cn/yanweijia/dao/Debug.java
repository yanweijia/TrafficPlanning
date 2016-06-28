package cn.yanweijia.dao;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {
	private static final String FILE_NAME = "runtime.log";
	public static void log(String str){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]: ");
		str = simpleDateFormat.format(new Date()) + str;
		System.out.println(str);
		FileWriter fw = null;
		try{
			fw = new FileWriter(FILE_NAME,true);	//用追加方式打开文件
			fw.write(str + "\r\n");
			fw.close();
		}catch(Exception e){
			System.out.println("调试日志文件写入失败!");
		}
		
	}
}

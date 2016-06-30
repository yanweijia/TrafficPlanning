package cn.yanweijia.tu;
public class Plane_path {
	String[] vertices={"北京","上海","西安","广州","武汉","拉萨","昆明","乌鲁木齐"};
	Triple edges[]={new Triple(0,1,1200),new Triple(0,2,1500),
	                new Triple(1,0,1200),
	                new Triple(2,0,1500),new Triple(2,3,1500),
	                new Triple(3,2,1500),new Triple(3,4,1350),
	                new Triple(4,5,800),new Triple(4,3,1350),
	                new Triple(5,4,800),new Triple(5,6,850),
	                new Triple(6,7,1000),new Triple(6,5,900),
	                new Triple(7,0,1000),new Triple(7,6,1000),
	};
	

}

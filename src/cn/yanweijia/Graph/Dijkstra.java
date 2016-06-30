package cn.yanweijia.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;





//最短路径算法,参考博客http://www.tuicool.com/articles/yIRrUn
public class Dijkstra { 
    Set<Node> open=new HashSet<Node>(); 
    Set<Node> close=new HashSet<Node>(); 
    Map<String,Double> path=new HashMap<String,Double>();//封装路径距离 
    Map<String,String> pathInfo=new HashMap<String,String>();//封装路径信息 
    public Map<String,Double> getPath(){
    	return path;
    }
    public Map<String,String> getPathInfo(){
    	return pathInfo;
    }
    public Set<Node> getOpen(){
    	return this.open;
    }
    public Set<Node> getClose(){
    	return this.close;
    }
    public Node init(){ 
        //初始路径,因没有A->E这条路径,所以path(E)设置为Double.MAX_VALUE 
        path.put("B", 1.0); 
        pathInfo.put("B", ""); 
        path.put("C", 1.0); 
        pathInfo.put("C", "A->C"); 
        path.put("D", 4.0); 
        pathInfo.put("D", "A->D"); 
        path.put("E", Double.MAX_VALUE); 
        pathInfo.put("E", "A"); 
        path.put("F", 1.0); 
        pathInfo.put("F", "A->F"); 
        path.put("G", 5.0); 
        pathInfo.put("G", "A->G"); 
        path.put("H", Double.MAX_VALUE); 
        pathInfo.put("H", "A"); 
        //将初始节点放入close,其他节点放入open 
        Node start=new MapBuilder().build(open,close); 
        return start;
    }
    public void computePath(Node start) {
        Node nearest = getShortestPath(start);// 取距离start节点最近的子节点,放入close
        if (nearest == null) {
            return;
        }
        close.add(nearest);
        open.remove(nearest);
        Map<Node, Double> childs = nearest.getChild();
        for (Node child : childs.keySet()) {
            if (open.contains(child)) {// 如果子节点在open中
                Double newCompute = path.get(nearest.getName()) + childs.get(child);
                if (path.get(child.getName()) > newCompute) {// 之前设置的距离大于新计算出来的距离
                    path.put(child.getName(), newCompute);
                    
                    //之前缺少的两行代码,找到最近的路径后，更新相邻点的距离
                    start.getChild().put(child, newCompute);
                    close.add(start);
                    
                    pathInfo.put(child.getName(), pathInfo.get(nearest.getName()) + "-》" + child.getName());
                }
            }
        }
        computePath(start);// 重复执行自己,确保所有子节点被遍历
        computePath(nearest);// 向外一层层递归,直至所有顶点被遍历
    }
    public void printPathInfo(){ 
        Set<Map.Entry<String, String>> pathInfos=pathInfo.entrySet(); 
        for(Map.Entry<String, String> pathInfo:pathInfos){ 
            System.out.println(pathInfo.getKey()+":"+pathInfo.getValue()); 
        } 
    } 
    public String getPathString(String endNode){
    	String result = "";
    	Set<Map.Entry<String, String>> pathInfos=pathInfo.entrySet(); 
    	for(Map.Entry<String, String> pathInfo:pathInfos){
    		if(pathInfo.getValue().endsWith(endNode))
    			return pathInfo.getValue();
        }
    	return null;
    }
    /**
     * 获取与node最近的子节点
     */
    private Node getShortestPath(Node node){ 
        Node res=null; 
        double minDis=Double.MAX_VALUE; 
        Map<Node,Double> childs=node.getChild(); 
        for(Node child:childs.keySet()){ 
            if(open.contains(child)){ 
                double distance=childs.get(child); 
                if(distance<minDis){ 
                    minDis=distance; 
                    res=child; 
                } 
            } 
        } 
        return res; 
    } 
} 
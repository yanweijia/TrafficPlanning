package cn.yanweijia.Graph;

import java.util.ArrayList;
import java.util.Hashtable;

import cn.yanweijia.Tools.Debug;

public class PlanCourse {
	private Hashtable<String, PassedPath> htPassedPath;
	public PlanCourse(ArrayList<Node> nodeList,String originID){
		this.htPassedPath = new Hashtable<String, PassedPath>();
		
		Node originNode = null;
		for(Node node:nodeList){
			if(node.ID.equals(originID)){
				originNode = node;
			}else{
				PassedPath pPath = new PassedPath(node.ID);
				this.htPassedPath.put(node.ID, pPath);
			}
		}
		if(originNode == null){
			Debug.log("不存在该节点" + originID);
		}
		this.InitializeWeight(originNode);
	}
	private void InitializeWeight(Node originNode){
		if((originNode.edgeList == null) || originNode.edgeList.size() == 0)
			return;
		for(Edge edge:originNode.edgeList){
			PassedPath pPath = this.getPassedPath(edge.EndNodeID);
			if(pPath == null){
				continue;
			}
			pPath.PassedIDList().add(originNode.ID);
			pPath.setWeight(edge.weight);
		}
	}
	public PassedPath getPassedPath(String nodeID){
		return this.htPassedPath.get(nodeID);
	}
	
}

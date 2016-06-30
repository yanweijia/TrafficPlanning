package cn.yanweijia.Graph;

import java.util.ArrayList;

public class RoutePlanner {
	public RoutePlanner(){
		
	}
	//获取权值最小的路径
	public RoutePlanResult Paln(ArrayList<Node> nodeList,String originID,String destID){
		PlanCourse planCourse = new PlanCourse(nodeList ,originID) ;
		Node curNode = this.getMinWeightRudeNode(planCourse ,nodeList ,originID) ;
		
		
		while(curNode != null){
			PassedPath curPath = planCourse.getPassedPath(curNode.ID);
			for(Edge edge:curNode.edgeList){
				PassedPath targetPath = planCourse.getPassedPath(edge.EndNodeID);
				double tempWeight = curPath.getWeight() + edge.weight;
				
				if(tempWeight < targetPath.getWeight()){
					targetPath.setWeight(tempWeight);
					targetPath.PassedIDList().clear();
					
					for(int i = 0; i < curPath.PassedIDList().size() ; i++){
						targetPath.PassedIDList().add(curPath.PassedIDList().get(i).toString());
					}
					
					targetPath.PassedIDList().add(curNode.ID);
				}
			}
			//标志为已处理
			planCourse.getPassedPath(curNode.ID).setBeProcessed(true);
			//获取下一个未处理的节点
			curNode = this.getMinWeightRudeNode(planCourse,nodeList,originID);
		}
		return this.getResult(planCourse,destID);
	}
	private RoutePlanResult getResult(PlanCourse planCourse,String destID){
		PassedPath pPath = planCourse.getPassedPath(destID);
		if(pPath.getWeight() == Integer.MAX_VALUE){
			RoutePlanResult result1 = new RoutePlanResult(null,Integer.MAX_VALUE);
			return result1;
		}
		String[] passedNodeIDs = new String[pPath.PassedIDList().size()];
		for(int i = 0 ; i < passedNodeIDs.length ; i++){
			passedNodeIDs[i] = pPath.PassedIDList().get(i).toString();
		}
		RoutePlanResult result = new RoutePlanResult(passedNodeIDs,pPath.getWeight());
		return result;
	}
	//从PlanCourse取出一个当前累计权值最小,并且没有处理过的节点
	private Node getMinWeightRudeNode(PlanCourse planCourse,ArrayList<Node> nodeList,String originID){
		double weight = Double.MAX_VALUE;
		Node destNode = null;
		
		Node node;
		for(int i = 0;i < nodeList.size() ; i++){
			node = (Node)nodeList.get(0);
			if(node.ID.equals(originID)){
				continue;
			}
			PassedPath pPath = planCourse.getPassedPath(node.ID);
			if(pPath.getBeProcessed()){
				continue;
			}
			if(pPath.getWeight() < weight){
				weight = pPath.getWeight();
				destNode = node;
			}
		}
		
		
		return destNode;
	}
}

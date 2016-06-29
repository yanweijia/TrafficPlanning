package cn.yanweijia.Graph;

import java.util.ArrayList;

//用边和节点来构成图
public class Graph {
	public ArrayList<Node> m_nodeList = null;
	public Graph(){
		m_nodeList = new ArrayList<Node>();
	}
	public ArrayList<Node> NodeList(){
		return this.m_nodeList;
	}
	//初始化拓扑图
	public void init(){
//		//***************** B Node *******************
//        Node aNode = new Node("A");
//        m_nodeList.Add(aNode);
//        //A -> B
//        Edge aEdge1 = new Edge();
//        aEdge1.StartNodeID = aNode.ID;
//        aEdge1.EndNodeID = "B";
//        aEdge1.Weight = 10;
//        aNode.EdgeList.Add(aEdge1);
//        //A -> C
//        Edge aEdge2 = new Edge();
//        aEdge2.StartNodeID = aNode.ID;
//        aEdge2.EndNodeID = "C";
//        aEdge2.Weight = 20;
//        aNode.EdgeList.Add(aEdge2);
//        //A -> E
//        Edge aEdge3 = new Edge();
//        aEdge3.StartNodeID = aNode.ID;
//        aEdge3.EndNodeID = "E";
//        aEdge3.Weight = 30;
//        aNode.EdgeList.Add(aEdge3);
//
//        //***************** B Node *******************
//        Node bNode = new Node("B");
//        m_nodeList.Add(bNode);
//        //B -> C
//        Edge bEdge1 = new Edge();
//        bEdge1.StartNodeID = bNode.ID;
//        bEdge1.EndNodeID = "C";
//        bEdge1.Weight = 5;
//        bNode.EdgeList.Add(bEdge1);
//        //B -> E
//        Edge bEdge2 = new Edge();
//        bEdge2.StartNodeID = bNode.ID;
//        bEdge2.EndNodeID = "E";
//        bEdge2.Weight = 10;
//        bNode.EdgeList.Add(bEdge2);
//
//        //***************** C Node *******************
//        Node cNode = new Node("C");
//        m_nodeList.Add(cNode);
//        //C -> D
//        Edge cEdge1 = new Edge();
//        cEdge1.StartNodeID = cNode.ID;
//        cEdge1.EndNodeID = "D";
//        cEdge1.Weight = 30;
//        cNode.EdgeList.Add(cEdge1);
//
//        //***************** D Node *******************
//        Node dNode = new Node("D");
//        m_nodeList.Add(dNode);
//
//        //***************** E Node *******************
//        Node eNode = new Node("E");
//        m_nodeList.Add(eNode);
//        //E -> D
//        Edge eEdge1 = new Edge();
//        eEdge1.StartNodeID = eNode.ID;
//        eEdge1.EndNodeID = "D";
//        eEdge1.Weight = 20;
//        eNode.EdgeList.Add(eEdge1);
	}
}

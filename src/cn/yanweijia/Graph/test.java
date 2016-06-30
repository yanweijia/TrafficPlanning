package cn.yanweijia.Graph;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ArrayList nodeList = new ArrayList() ;
		 
         //***************** B Node *******************
         Node aNode  = new Node("A") ;
         nodeList.add(aNode) ;
         //A -> B
         Edge aEdge1 = new Edge() ;
         aEdge1.StartNodeID = aNode.ID ;
         aEdge1.EndNodeID   = "B" ;
         aEdge1.weight      = 10 ;
         aNode.edgeList.add(aEdge1) ;
         //A -> C
         Edge aEdge2 = new Edge() ;
         aEdge2.StartNodeID = aNode.ID ;
         aEdge2.EndNodeID   = "C" ;
         aEdge2.weight      = 20 ;
         aNode.edgeList.add(aEdge2) ;
         //A -> E
         Edge aEdge3 = new Edge() ;
         aEdge3.StartNodeID = aNode.ID ;
         aEdge3.EndNodeID   = "E" ;
         aEdge3.weight=30 ;
         aNode.edgeList.add(aEdge3) ;

         //***************** B Node *******************
         Node bNode  = new Node("B") ;
         nodeList.add(bNode) ;
         //B -> C
         Edge bEdge1 = new Edge() ;
         bEdge1.StartNodeID = bNode.ID ;
         bEdge1.EndNodeID   = "C" ;
         bEdge1.weight      = 5 ;
         bNode.edgeList.add(bEdge1) ;
         //B -> E
         Edge bEdge2 = new Edge() ;
         bEdge2.StartNodeID = bNode.ID ;
         bEdge2.EndNodeID   = "E" ;
         bEdge2.weight      = 10 ;
         bNode.edgeList.add(bEdge2) ;

         //***************** C Node *******************
         Node cNode  = new Node("C") ;
         nodeList.add(cNode) ;
         //C -> D
         Edge cEdge1        = new Edge() ;
         cEdge1.StartNodeID = cNode.ID ;
         cEdge1.EndNodeID   = "D" ;
         cEdge1.weight      = 30 ;
         cNode.edgeList.add(cEdge1) ;

         //***************** D Node *******************
         Node dNode  = new Node("D") ;
         nodeList.add(dNode) ;

         //***************** C Node *******************
         Node eNode  = new Node("E") ;
         nodeList.add(eNode) ;
         //C -> D
         Edge eEdge1        = new Edge() ;
         eEdge1.StartNodeID = eNode.ID ;
         eEdge1.EndNodeID   = "D" ;
         eEdge1.weight      = 20 ;
         eNode.edgeList.add(eEdge1) ;
         Node fNode = new Node("F");
         Edge dEdge1 = new Edge();
         dEdge1.StartNodeID = dNode.ID;
         dEdge1.EndNodeID = fNode.ID;
         dEdge1.weight = 20;
         dNode.edgeList.add(dEdge1);

         RoutePlanner planner = new RoutePlanner() ;
         RoutePlanResult result = planner.Paln(nodeList ,"A" ,"B") ;
         for(String str:result.getResultNodes()){
        	 System.out.println(str);
         }
         System.out.println(result.getValue());
         
	}

}

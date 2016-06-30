package cn.yanweijia.Graph;

import java.util.Set;

public class MapBuilder { 
    public Node build(Set<Node> open, Set<Node> close){ 
        Node nodeA=new Node("A"); 
        Node nodeB=new Node("B"); 
        Node nodeC=new Node("C"); 
        Node nodeD=new Node("D"); 
        Node nodeE=new Node("E"); 
        Node nodeF=new Node("F"); 
        Node nodeG=new Node("G"); 
        Node nodeH=new Node("H"); 
        nodeA.getChild().put(nodeB, 1.0); 
        nodeA.getChild().put(nodeC, 1.0); 
        nodeA.getChild().put(nodeD, 4.0); 
        nodeA.getChild().put(nodeG, 5.0); 
        nodeA.getChild().put(nodeF, 1.0); 
        nodeB.getChild().put(nodeA, 1.0); 
        nodeB.getChild().put(nodeF, 2.0); 
        nodeB.getChild().put(nodeH, 4.0); 
        nodeC.getChild().put(nodeA, 1.0); 
        nodeC.getChild().put(nodeG, 3.0); 
        nodeD.getChild().put(nodeA, 4.0); 
        nodeD.getChild().put(nodeE, 1.0); 
        nodeE.getChild().put(nodeD, 1.0); 
        nodeE.getChild().put(nodeF, 1.0); 
        nodeF.getChild().put(nodeE, 1.0); 
        nodeF.getChild().put(nodeB, 2.0); 
        nodeF.getChild().put(nodeA, 2.0); 
        nodeG.getChild().put(nodeC, 3.0); 
        nodeG.getChild().put(nodeA, 5.0); 
        nodeG.getChild().put(nodeH, 1.0); 
        nodeH.getChild().put(nodeB, 4.0); 
        nodeH.getChild().put(nodeG, 1.0); 
        open.add(nodeB); 
        open.add(nodeC); 
        open.add(nodeD); 
        open.add(nodeE); 
        open.add(nodeF); 
        open.add(nodeG); 
        open.add(nodeH); 
        close.add(nodeA); 
        return nodeA;
    } 
}
package cn.yanweijia.Graph;

public class RoutePlanResult {
	private String[] m_resultNodes;
	private double m_value;
	
	public RoutePlanResult(String[] passedNodes,double value){
		m_resultNodes = passedNodes;
		m_value = value;
	}
	public String[] getResultNodes(){
		return m_resultNodes;
	}
	public double getValue(){
		return m_value;
	}
}

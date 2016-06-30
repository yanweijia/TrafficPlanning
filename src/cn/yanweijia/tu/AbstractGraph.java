package cn.yanweijia.tu;

public class AbstractGraph<T>
{
	public static final int MAX_WEIGHT=0x0000ffff;
	protected SeqList<T>vertexlist;
	public AbstractGraph(int length)
	{
		this.vertexlist=new SeqList<T>(length);
	}
	public AbstractGraph()
	{
		this(10);
	}
	public int vertexCount()
	{
		return this.vertexlist.length();
	}
	public String toString()
	{
		return "���㼯�ϣ�   "+this.vertexlist.toString()+"\n";
	}
    public T getVertex(int i)
    {
    	return this.vertexlist.get(i);
    }
    public void setVertex(int i,T x)
    {
    	this.vertexlist.set(i, x);
    }
    public void insetVertex(T x)
    {
    	this.vertexlist.insert(x);
    }
}

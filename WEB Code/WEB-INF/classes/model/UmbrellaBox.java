package model;

//����Կ� ���� Ŭ�����Դϴ�. �ڽ�id, ��ġ, ����Ǽ�,���峭 ����� ���� �����Ǿ��ֽ��ϴ�.

public class UmbrellaBox{
	public int boxid;
	public String location;
	public int quantityUMB;
	public int brokenUMB;
	
	public int getboxId(){
		return (boxid);
	}
	
	public void setboxId(int boxid){
		this.boxid = boxid;
	}
	
	public String getLocation(){
		return (location);
	}
	
	public void setLocation(String location)
	{
		this.location=location;
	}
	
	public int getQuantityUMB(){
		return (quantityUMB);
	}
	
	public void setQuantityUMB(int quantityUMB)
	{
		this.quantityUMB=quantityUMB;
	}
	
	public int getBrokenUMB(){
		return (brokenUMB);
	}
	
	public void setBrokenUMB(int brokenUMB)
	{
		this.brokenUMB=brokenUMB;
	}
	
}

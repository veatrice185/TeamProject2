package model;

//우산함에 대한 클래스입니다. 박스id, 위치, 우산의수,고장난 우산의 수로 구성되어있습니다.

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

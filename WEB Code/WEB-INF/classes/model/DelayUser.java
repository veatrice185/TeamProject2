package model;

//연체자에 대한 클래스입니다. id,핸드폰번호, 연체일로 구성되어있습니다.

public class DelayUser{
	public String userid;
	public String phonenumber;
	public String day;

	public String getUserId(){
		return (userid);
	}
	
	public void setUserId(String userid){
		this.userid = userid;
	}
	
	public String getPhonenumber(){
		return (phonenumber);
	}
	
	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber=phonenumber;
	}
	
	public String getDay(){
		return (day);
	}
	
	public void setDay(String day)
	{
		this.day=day;
	}

	
}

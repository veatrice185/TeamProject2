package model;

//��ü�ڿ� ���� Ŭ�����Դϴ�. id,�ڵ�����ȣ, ��ü�Ϸ� �����Ǿ��ֽ��ϴ�.

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

package model;

//대여현황 로그에 대한 클래스입니다, 몇번째 로그인지에 대해 알려주는 number와 id,빌린날,빌림유무로 구성되어있습니다.


public class History {

	public int number;
	public String customerid;
	public String renttime;
	public int issuedcheck;

	public int getNumber(){
		return (number);
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public String getCustomerId(){
		return (customerid);
	}
	
	public void setCustomerId(String customerid){
		this.customerid = customerid;
	}
	
	public String getRentTime(){
		return (renttime);
	}
	
	public void setRentTime(String renttime)
	{
		this.renttime=renttime;
	}
	
	public int getIssuedCheck(){
		return (issuedcheck);
	}
	
	public void setIssuedCheck(int issuedcheck)
	{
		this.issuedcheck=issuedcheck;
	}

}

package model;

//�뿩��Ȳ �α׿� ���� Ŭ�����Դϴ�, ���° �α������� ���� �˷��ִ� number�� id,������,���������� �����Ǿ��ֽ��ϴ�.


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

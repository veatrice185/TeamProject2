package model;

//����ڿ� ���� Ŭ�����Դϴ�, ���,nfc id, �ڵ�����ȣ, �����Ÿ��(������,�Ϲݻ����), ���������� �����Ǿ��ֽ��ϴ�.

public class User{
	public String customerid;
	public String nfctagid;
	public String phonenumber;
	public int usertype;
	public int issued;
	
	
	public String getCustomerId(){
		return (customerid);
	}
	
	public void setCustomerId(String customerid){
		this.customerid = customerid;
	}
	
	public String getNfcTagId(){
		return (nfctagid);
	}
	
	public void setNfcTagId(String nfctagid){
		this.nfctagid = nfctagid;
	}
	
	public String getPhonenumber(){
		return (phonenumber);
	}
	
	public void setPhonenumber(String phonenumber)
	{
		this.phonenumber=phonenumber;
	}
	
	public int getUserType(){
		return (usertype);
	}
	
	public void setUserType(int usertype){
		this.usertype = usertype;
	}
	
	public int getIssued(){
		return (issued);
	}
	
	public void setIssued(int issued){
		this.issued = issued;
	}
	
	
}

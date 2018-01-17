package model;


//관리자 계정에 대한 클래스입니다. id와 패스워드로 구성되어있습니다.

public class Admin{
	public String id;
	public String password;
	
	public String getId(){
		return (id);
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getPassword(){
		return (password);
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
}

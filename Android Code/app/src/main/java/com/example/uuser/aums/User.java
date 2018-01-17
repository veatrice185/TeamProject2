package com.example.uuser.aums;

import java.io.Serializable;

public class User implements Serializable {

    public static final int CUSTOMER = 0;
    public static final int MANAGER = 1;
    public static final int UNISSUED = 0;
    public static final int ISSUED = 1;

    private int type;
    private int status;
    private String name = "NONAME";
    private String ID;
    private String pNum;

    public String getNFCtagID() {
        return NFCtagID;
    }

    public void setNFCtagID(String NFCtagID) {
        this.NFCtagID = NFCtagID;
    }

    private String NFCtagID;

    public User(String NFCtagID, int type, int status, String name, String ID, String pNum){
        //initializeUser(NFCtagID);
        this.NFCtagID = NFCtagID;
        this.type = type;
        this.status = status;
        this.name = name;
        this.ID = ID;
        this.pNum = pNum;
    }

    public User(String TagID){
        this.NFCtagID = TagID;
    }

//    public User(int ID){
//        initializeUser(ID);
//    }

    public void setUserStatus(int status){
        this.status = status;
    }

    public boolean isIssued() {
        if (this.status == ISSUED)
            return true;
        else
            return false;
    }

    public boolean isManager() {
        if(this.type == MANAGER)
            return true;
        else
            return false;
    }

    //public void initializeUser(String NFCtagID, ){ }

    public void initializeUser(String NFCtagID, int type, int status, String name, String ID, String pNum){
        //initializeUser(NFCtagID);
        this.NFCtagID = NFCtagID;
        this.type = type;
        this.status = status;
        this.name = name;
        this.ID = ID;
        this.pNum = pNum;
    }
//    public void initializeUser(int type){
//        if(type == MANAGER) {
//            this.type = MANAGER;
//            this.status = UNISSUED;
//            this.name = "Mr.Manager";
//            this.ID = "0000";
//            this.NFCtagID = "317";
//        }
//        else {
//            this.type = CUSTOMER;
//            this.status = UNISSUED;
//            this.name = "Mr.Customer";
//            this.ID = "1234";
//            this.NFCtagID = "312";
//        }
//    }


    public String getName(){
        return name;
    }
    public String getID(){
        return ID;
    }
}

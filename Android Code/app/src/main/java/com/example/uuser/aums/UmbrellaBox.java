package com.example.uuser.aums;

public class UmbrellaBox {

    public static final boolean EMPTY = false;
    public static final boolean OCCUPIED = true;
    private static int brokenUmbrella = 0;
    private static int UmbrellaID = 0;

    private boolean[] umbrellaStatus = new boolean[4];


    private static UmbrellaBox ourInstance = new UmbrellaBox();

    public static UmbrellaBox getInstance() {
        return ourInstance;
    }

    public UmbrellaBox() {
        initializeBox();
    }

    public int getBrokenUmbrella() {
        return brokenUmbrella;
    }

    public void addbrokenUmbrella() {
        brokenUmbrella++;
    }

    public void setBrokenUmbrella() {
        brokenUmbrella = 0;
    }

    public void setUmbrellaID(int id) {
        UmbrellaID = id;
    }

    public int getUmbrellaID() {
        return UmbrellaID;
    }

    public void setStatus(boolean status, int place){
        umbrellaStatus[place] = status;
    }
    public boolean getStatus(int place){
        return umbrellaStatus[place];
    }

    public boolean isEmpty(int place) {
        return umbrellaStatus[place];
    }


    public void initializeBox(){
        //Arrays.fill(umbrellaStatus, false);
        BoxAdapter.boxFromDB(this);
    }
}

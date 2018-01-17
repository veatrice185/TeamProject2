package com.example.uuser.aums;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

import com.example.uuser.aums.bluetooth.BTConn;

/**
 * Created by UUser on 2015-11-27.
 */
public class ArduinoAdapter {
    private static ArduinoAdapter ourInstance = new ArduinoAdapter();

    public static ArduinoAdapter getInstance() {
        return ourInstance;
    }

    private static final String TAG = "ArduinoAdapter";
    private static BluetoothDevice device;
    private static BTConn conn;
    private static String NFCtagID = "0";

    private static boolean isTagged = false;

    private ArduinoAdapter() {
//        BTConn conn = new BTConn();
        //conn.onConnectionStarted(ArduinoAdapter.device);
//        changeTextDevice();
//        conn.connect(ArduinoAdapter.device);
//        Log.e("BTconnect", "success");

//        conn.startServer();
//        Log.e("BTServerStart", "success");
    }

    public static void bluetoothConnectionStart(){
        conn.onConnectionStarted(device);
        conn.connect(device);
        Log.e("BTconnect", "success");
//        conn.startServer();
//        Log.e("BTServerStart", "success");

    }
    public static void openBox(int num){
        conn.sendText(String.valueOf(num));
    }
//    public static void lockBox(int num){
//
//    }


    public static String getNFCTag(){
        return "0";
    }



    public static BluetoothDevice getDevice() {
        return device;
    }










    public static void setDevice(BluetoothDevice device) {
        try{
            ArduinoAdapter.device = device;
            Log.e(TAG, device.getName()+" is connected Successly");
        }catch (Exception e){
            Log.e(TAG, e.toString());
        }

    }

    public static BTConn getConn() {
        return conn;
    }

    public static void setConn(BTConn conn) {
        ArduinoAdapter.conn = conn;
    }


    public static String getNFCtagID() {
        return NFCtagID;
    }

    public static void setNFCtagID(String NFCtagID) {
        ArduinoAdapter.NFCtagID = NFCtagID;
    }


    public static boolean isTagged() {
        return isTagged;
    }

    public static void setIsTagged(boolean isTagged) {
        ArduinoAdapter.isTagged = isTagged;
    }

}

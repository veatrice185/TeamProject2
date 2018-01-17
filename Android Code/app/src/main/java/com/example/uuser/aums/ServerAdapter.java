package com.example.uuser.aums;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

/**
 * Created by UUser on 2015-11-27.
 */
public class ServerAdapter {
    private static ServerAdapter ourInstance = new ServerAdapter();

    private static String str_url;

    public static ServerAdapter getInstance() {
        return ourInstance;
    }

    private ServerAdapter() {
    }

    public static User getUserData(){
        User user = new User("312");
        return user;
    }

    public static void addUmbrella(){
        httpConn con = new httpConn();
        con.addUmbrella();

    }
    public static void minusUmbrella(){
        httpConn con = new httpConn();
        con.minusUmbrella();
    }
    public static void clearBroken(){
        httpConn con = new httpConn();
        con.clearBroken();
    }
    public static void setUserIssued(String userNFCTagID, String customerID){
        httpConn con = new httpConn();
        con.setIssued(userNFCTagID, customerID);

    }
    public static void setUserUnissued(String userNFCTagID, String customerID){
        httpConn con = new httpConn();
        con.setUnIssued(userNFCTagID, customerID);
    }

    public static void reportBroken(){
        httpConn con = new httpConn();
        con.addBroken();
    }

    public static void login(User user, String NFCTagID){

        httpConn con = new httpConn();
        con.login(user, NFCTagID);
    }

    public static void setURL(String url){
        str_url = url;
    }
    public static String getURL(){
        return str_url;
    }

    public static void connect() throws Exception{

        httpConn con = new httpConn();
        con.initialConnect();

    }


//
//    private class getUserDataAsyncTask extends AsyncTask<String, Integer, String> {
//        protected String doInBackground(String... urls) {
//            //                        String[] a = {w,leftSel,rightSel};
//            String str = HttpParser.parseURL(ServerAdapter.getURL());
//            /*
//            String str = HttpParser.parseURL("http://piloteer.wo.tc/~sub/" +
//                    "AddQ.php?writer="+urls[0]+ "&LSelect=" +
//                    urls[1]+"&RSelect="+urls[2]);
//            */
//            return str;
//        }
//
//        protected void onProgressUpdate(Integer... progress) {
//        }
//
//        protected void onPostExecute(String result) {
//            try {
////                 parser = new XMLParser(result);
////                Map<String,String> vals = parser.simpleDecompose();
////                int QID = Integer.parseInt(vals.get("QID"));
////                MyQuestionActivity.addNewQuestion(QID);
//                Log.e("test", "blah");
//            }catch(Exception e){
//                Log.e("error", e.toString());
//            }
//        }
//    }
//
//    private class AddUmbrellaAsyncTask extends AsyncTask<String, Integer, String> {
//        protected String doInBackground(String... urls) {
//            //                        String[] a = {w,leftSel,rightSel};
//            String str = HttpParser.parseURL(ServerAdapter.getURL()+ "UserData");
//            /*
//            String str = HttpParser.parseURL("http://piloteer.wo.tc/~sub/" +
//                    "AddQ.php?writer="+urls[0]+ "&LSelect=" +
//                    urls[1]+"&RSelect="+urls[2]);
//            */
//            return str;
//        }
//
//        protected void onProgressUpdate(Integer... progress) {
//        }
//
//        protected void onPostExecute(String result) {
//            try {
////                 parser = new XMLParser(result);
////                Map<String,String> vals = parser.simpleDecompose();
////                int QID = Integer.parseInt(vals.get("QID"));
////                MyQuestionActivity.addNewQuestion(QID);
//                Log.e("test", "blah");
//            }catch(Exception e){
//                Log.e("error", e.toString());
//            }
//        }
//    }
}

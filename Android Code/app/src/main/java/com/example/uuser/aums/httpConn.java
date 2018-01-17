package com.example.uuser.aums;

import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by UUser on 2015-11-27.
 */
public class httpConn {

    private String str_url;
    private URL url;

    public void setUrl(String str){
        str_url = str;
        //url = str_url;
    }

    public void addUmbrella(){
        String[] a = {null, null, null};
        AddUmbrellaAsyncTask task = new AddUmbrellaAsyncTask();
        task.execute(a);

    }

    public void minusUmbrella(){
        String[] a = {null, null, null};
        MinusUmbrellaAsyncTask task = new MinusUmbrellaAsyncTask();
        task.execute(a);
    }

    public void clearBroken(){
        String[] a = {null, null, null};
        ClearBrokenAsyncTask task = new ClearBrokenAsyncTask();
        task.execute(a);
    }

    public void initialConnect() throws Exception{
        //HttpURLConnection
        String[] a = {null, null, null};
        InitialConnectAsyncTask task = new InitialConnectAsyncTask();
            task.execute(a);
    }
    public void setIssued(String NFCtagID, String customerID){
        String[] a = {NFCtagID, customerID, null};
        SetIssuedAsyncTask task = new SetIssuedAsyncTask();
        task.execute(a);
    }

    public void setUnIssued(String NFCtagID, String customerID){
        String[] a = {NFCtagID, customerID, null};
        SetUnIssuedAsyncTask task = new SetUnIssuedAsyncTask();
        task.execute(a);
    }

    public void addBroken(){
        String[] a = {null, null, null};
       AddBrokenAsyncTask task = new AddBrokenAsyncTask();
        task.execute(a);
    }

    public void login(User user, String NFCTagID){
        String[] a={NFCTagID, null, null};
        LogInAsyncTask task = new LogInAsyncTask(user);
        task.execute(a);

    }

    private class AddUmbrellaAsyncTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+"20151127/"+ "ReturnUmbrella");
            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
                Log.e("AddUmbrella", "Success");
            }catch(Exception e){
                Log.e("AddUmbrella error", e.toString());
            }
        }
    }

    private class ClearBrokenAsyncTask extends AsyncTask<String, Integer, String>{
        protected String doInBackground(String... urls) {
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+"20151202/"+ "BrokenBox");
            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
                Log.e("ClearBroken", "Success");
            }catch(Exception e){
                Log.e("ClearBroken error", e.toString());
            }
        }
    }

    private class MinusUmbrellaAsyncTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+ "20151127/"+ "BorrowUmbrella");
            /*
            String str = HttpParser.parseURL("http://piloteer.wo.tc/~sub/" +
                    "AddQ.php?writer="+urls[0]+ "&LSelect=" +
                    urls[1]+"&RSelect="+urls[2]);
            */
            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
//                 parser = new XMLParser(result);
//                Map<String,String> vals = parser.simpleDecompose();
//                int QID = Integer.parseInt(vals.get("QID"));
//                MyQuestionActivity.addNewQuestion(QID);
                Log.e("MinusUmbrella", "Success");
            }catch(Exception e){
                Log.e("MinusUmbrella error", e.toString());
            }
        }
    }

    private class SetIssuedAsyncTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+ "20151202/"+ "IssuedChange" +
                    "?nfctagid="+urls[0]+"&customerid="+urls[1]);
            Log.e("url:", str);
            /*
            String str = HttpParser.parseURL("http://piloteer.wo.tc/~sub/" +
                    "AddQ.php?writer="+urls[0]+ "&LSelect=" +
                    urls[1]+"&RSelect="+urls[2]);
            */
            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
//                 parser = new XMLParser(result);
//                Map<String,String> vals = parser.simpleDecompose();
//                int QID = Integer.parseInt(vals.get("QID"));
//                MyQuestionActivity.addNewQuestion(QID);
                Log.e("SetIssued", "Success");
            }catch(Exception e){
                Log.e("SetIssued error", e.toString());
            }
        }
    }

    private class SetUnIssuedAsyncTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+ "20151202/"+ "UnIssuedChange" +
                    "?nfctagid=" + urls[0]+"&customerid="+urls[1]);
            Log.e("url:", str);
            /*
            String str = HttpParser.parseURL("http://piloteer.wo.tc/~sub/" +
                    "AddQ.php?writer="+urls[0]+ "&LSelect=" +
                    urls[1]+"&RSelect="+urls[2]);
            */
            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
//                 parser = new XMLParser(result);
//                Map<String,String> vals = parser.simpleDecompose();
//                int QID = Integer.parseInt(vals.get("QID"));
//                MyQuestionActivity.addNewQuestion(QID);
                Log.e("Set User Unissued", "Success");
            }catch(Exception e){
                Log.e("Set User Unissued error", e.toString());
            }
        }
    }

    private class AddBrokenAsyncTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+ "20151202/"+ "BrokenBoxAdd");
            /*
            String str = HttpParser.parseURL("http://piloteer.wo.tc/~sub/" +
                    "AddQ.php?writer="+urls[0]+ "&LSelect=" +
                    urls[1]+"&RSelect="+urls[2]);
            */
            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
//                 parser = new XMLParser(result);
//                Map<String,String> vals = parser.simpleDecompose();
//                int QID = Integer.parseInt(vals.get("QID"));
//                MyQuestionActivity.addNewQuestion(QID);
                Log.e("report Broken", "Success");
            }catch(Exception e){
                Log.e("Report Broken error", e.toString());
            }
        }
    }

    private class LogInAsyncTask extends AsyncTask<String, Integer, String> {

        JSONParser parser;
        private User user;
        public LogInAsyncTask(User user){
            this.user = user;
        }

        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL()+ "20151202/"+ "UserLogin" +
                    "?nfctagid="+urls[0]);

            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
//                 parser = new XMLParser(result);
//                Map<String,String> vals = parser.simpleDecompose();
//                int QID = Integer.parseInt(vals.get("QID"));
//                MyQuestionActivity.addNewQuestion(QID);

                Log.e("logindata :", result);
                parser = new JSONParser();
                //parser.getUser(result,user);
                user = parser.getUser(result, user);
                //user = new User(123);

                Log.e("AsT_user : ", user.getName());
                Log.e("login", "Success");

//                notifyAll();
            }catch(Exception e){
                Log.e("login error", e.toString());
            }
        }
    }




    private class InitialConnectAsyncTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = HttpParser.parseURL("http://"+ServerAdapter.getURL());

            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            try {
//                 parser = new XMLParser(result);
//                Map<String,String> vals = parser.simpleDecompose();
//                int QID = Integer.parseInt(vals.get("QID"));
//                MyQuestionActivity.addNewQuestion(QID);
                Log.e("Initial conncetion", "Success");
            }catch(Exception e){
                Log.e("Initialconn error", e.toString());
            }
        }
    }




}

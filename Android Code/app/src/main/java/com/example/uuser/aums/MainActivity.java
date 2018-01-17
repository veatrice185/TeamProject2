package com.example.uuser.aums;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uuser.aums.bluetooth.BTCommHandler;
import com.example.uuser.aums.bluetooth.BTConn;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {


    Button buttonLoginCustomer;
    Button buttonLoginManager;
    String tagID;
    UmbrellaBox box;
    Intent intent;
    User user;
    BluetoothDevice device;
    private static final String READY_TO_NFC = "0";

//    BTCommHandler btCommHandler;
   Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagID = ArduinoAdapter.getNFCtagID();
        BTConn conn = ArduinoAdapter.getConn();
        conn.sendText(READY_TO_NFC);
//        final User user = new User();

        intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        //device = (BluetoothDevice) intent.getSerializableExtra("Device");
        box = UmbrellaBox.getInstance();
        device = ArduinoAdapter.getDevice();
        //device = (BluetoothDevice) intent.

        buttonLoginCustomer = (Button) findViewById(R.id.LoginButton);
        buttonLoginManager = (Button) findViewById(R.id.ManagerLogin);

        buttonLoginManager.setVisibility(View.INVISIBLE);
        buttonLoginCustomer.setVisibility(View.INVISIBLE);
        //box = new UmbrellaBox();


        buttonLoginCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tagID = "312";
                String[] a = {tagID, null, null};
                user = new User(tagID);
                LoginAsyncTask task = new LoginAsyncTask(user);
                task.execute(a);
            }
        });

        buttonLoginManager.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                tagID = "317";
                tagID = ArduinoAdapter.getNFCtagID();
                Log.e("NFC_TAG_ID",tagID);
//                tagID="317";
//                Log.e("NFC_TAG_ID",tagID);

                String[] a = {tagID, null, null};
                user = new User(tagID);
                LoginAsyncTask task = new LoginAsyncTask(user);
                task.execute(a);
//                try{
//                    //BTConn conn = new BTConn();
//                    //conn.connect(device);
////                    BTConn conn = ArduinoAdapter.getConn();
//
//                }catch (NullPointerException e){
//                    Log.e("MainAct","TX MSG FAIL");
//                }
//
            }
        });

        NFCReadingThread thread = new NFCReadingThread();
        thread.start();

    }

    private class LoginAsyncTask extends AsyncTask<String, Integer, String> {

        JSONParser parser;
        private User user;
        public LoginAsyncTask(User user){
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
                Log.e("logindata :", result);
                parser = new JSONParser();
                //parser.getUser(result,user);
                user = parser.getUser(result, user);
                //user = new User(123);

                Log.e("AsT_user : ", user.getName());
                Log.e("login", "Success");

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();

            }catch(Exception e){
                Log.e("login error", e.toString());
                BTConn conn = ArduinoAdapter.getConn();
                conn.sendText(READY_TO_NFC);
            }
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class NFCReadingThread extends Thread{

//        BTCommHandler handler = new BTCommHandler();
//        Message message = handler.obtainMessage(BTCommHandler.MESSAGE_RECEIVED);
        @Override
        public void run(){
            while( !ArduinoAdapter.isTagged()){ }
            tagID = ArduinoAdapter.getNFCtagID();
            Log.e("NFC_TAG_ID",tagID);
//                tagID="317";
//                Log.e("NFC_TAG_ID",tagID);

            String[] a = {tagID, null, null};
            user = new User(tagID);
            LoginAsyncTask task = new LoginAsyncTask(user);
            task.execute(a);
        }

    }

    private void Tagging(){
        tagID = ArduinoAdapter.getNFCtagID();
        Log.e("NFC_TAG_ID",tagID);
        String[] a = {tagID, null, null};
        user = new User(tagID);
        LoginAsyncTask task = new LoginAsyncTask(user);
        task.execute(a);
    }


}



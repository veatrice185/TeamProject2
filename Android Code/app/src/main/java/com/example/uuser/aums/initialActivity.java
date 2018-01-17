package com.example.uuser.aums;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uuser.aums.bluetooth.BTCommHandler;
import com.example.uuser.aums.bluetooth.BTConn;
import com.example.uuser.aums.bluetooth.BTSelectionActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class initialActivity extends Activity {


    TextView TextURL;

    Button buttonStart;

    Button buttonBluetooth;

    private final static String TAG = "BluetoothActivity";

    TextView TextDevice;

    AlertDialog.Builder builder;
    ProgressDialog mProgressDialog;
        Handler mHandler;
    AlertDialog dialog;

    boolean isClicked;
//    BluetoothDevice device;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);



        TextURL = (TextView) findViewById(R.id.textURL);

        buttonStart = (Button) findViewById(R.id.buttonInitial);

        buttonBluetooth = (Button) findViewById(R.id.button_BluetoothPage);

        TextDevice = (TextView) findViewById(R.id.textDevice);

        isClicked = false;

        builder = new AlertDialog.Builder(this);


        builder.setTitle("AUMS")
                .setMessage("IP와 블루투스 연결을 확인해주십시오.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isClicked = true;
                    }
                });


        mHandler = new Handler();



        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mProgressDialog = ProgressDialog.show(initialActivity.this, "",
                        "가동중.", true);

                mHandler.postDelayed( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                                mProgressDialog.dismiss();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog = builder.create();
                                        dialog.show();
                                        mHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    if (isClicked == false && dialog != null && dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 1000);

                                    }
                                });
                            }
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }, 1000);

            }
        } );




                    buttonStart.setOnClickListener(new View.OnClickListener()

                    {
                        @Override
                        public void onClick (View v){
                        try {
                            ServerAdapter.setURL(TextURL.getText().toString());
                            ServerAdapter.connect();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (NullPointerException e) {
                            Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "접속 실패", Toast.LENGTH_LONG);
                }
            }});



        buttonBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    BTConn conn = new BTConn();
                    //conn.onConnectionStarted(ArduinoAdapter.device);
                    changeTextDevice();
                    conn.connect(ArduinoAdapter.getDevice());
                    Log.e("BTconnect", "success");

                    ArduinoAdapter.setConn(conn);
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_LONG);
                    Log.e("err", e.toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_LONG);
                    Log.e("err", e.toString());
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_connect:
                Log.i(TAG, "menu: Connect");
                startActivity(new Intent(getApplicationContext(), BTSelectionActivity.class));
                //finish();
                return true;
            case R.id.action_disconnect:
                //Log.i(TAG, "menu: Disconnect");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeTextDevice(){
        try{
            TextDevice.setText(ArduinoAdapter.getDevice().getName());
        }catch (NullPointerException e){
            Log.e("device", "connection fail");
        }

    }

}

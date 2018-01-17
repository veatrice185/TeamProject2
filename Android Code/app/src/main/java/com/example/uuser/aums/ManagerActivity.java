package com.example.uuser.aums;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uuser.aums.bluetooth.BTConn;

public class ManagerActivity extends Activity {

    BoxButton Button1;
    BoxButton Button2;
    BoxButton Button3;
    BoxButton Button4;
    Button ButtonFinish;
    Button ButtonCollect;
    User user;
    UmbrellaBox box;
    /*********************
     * 다이얼로그
     *********************************************/
    boolean isClicked;
    Handler mHandler;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    ProgressDialog mProgressDialog;

    /*********************
     * 다이얼로그
     *********************************************/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Button1 = (BoxButton) findViewById(R.id.manage1);
        Button2 = (BoxButton) findViewById(R.id.manage2);
        Button3 = (BoxButton) findViewById(R.id.manage3);
        Button4 = (BoxButton) findViewById(R.id.manage4);
        ButtonFinish = (Button) findViewById(R.id.ButtonFinish);
        ButtonCollect = (Button) findViewById(R.id.ButtonCollect);

        Button1.setType(BoxButton.MANAGE);
        Button2.setType(BoxButton.MANAGE);
        Button3.setType(BoxButton.MANAGE);
        Button4.setType(BoxButton.MANAGE);

        final Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        //box = (UmbrellaBox) intent.getSerializableExtra("Box");
        box = UmbrellaBox.getInstance();

        /*********************다이얼로그*********************************************/

        isClicked = false;

        mHandler = new Handler();

        builder = new AlertDialog.Builder(this);
        builder.setTitle("우산관리")
                .setMessage("우산 넣거나 뽑고 확인을 눌러 주십시오.\n" +
                        "확인을 누르면 우산함이 다시 잠깁니다.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isClicked = true;

                    }
                });
        /*********************다이얼로그*********************************************/


        BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);


        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button1).start();
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button2).start();}
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button3).start();    }
        });
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button4).start();
            }
        });

        ButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "우산 관리 완료.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            }
        });

        ButtonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box.getBrokenUmbrella() == 0) {
                    Toast.makeText(getApplicationContext(), "회수할 우산이 없습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), box.getBrokenUmbrella() + "개 부서진 우산 회수 완료.", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    //intent.putExtra("User", user);
                    //startActivity(intent);
                    box.setBrokenUmbrella();
                    ServerAdapter.clearBroken();

                }

            }
        });

    }

    private void manageUmbrella(BoxButton button){
        if (box.getStatus(button.getNumber()-1) == false) {
            box.setStatus(UmbrellaBox.OCCUPIED, button.getNumber()-1);
            Toast.makeText(getApplicationContext(), "우산 추가에 성공하였습니다.", Toast.LENGTH_LONG).show();
            BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
            /**********************************
             * 서버에 우산의 갯수를 +한다.
             **********************************/
            ServerAdapter.addUmbrella();

        } else {
            box.setStatus(UmbrellaBox.EMPTY, button.getNumber()-1);
            Toast.makeText(getApplicationContext(), "우산 회수에 성공하였습니다.", Toast.LENGTH_LONG).show();
            BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
            /**********************************
             * 서버에 우산의 갯수를 -한다.
             **********************************/
            ServerAdapter.minusUmbrella();
        }
        try{
            BTConn conn = ArduinoAdapter.getConn();
            conn.sendText(String.valueOf(button.getNumber()));
        }catch (NullPointerException e){
            Log.e("ManageUmbrella", "Bluetooth Error");
        }
    }

    private void closeUmbrella(BoxButton button){

        try {
            BTConn conn = ArduinoAdapter.getConn();
            conn.sendText(String.valueOf(button.getNumber()+4));
        } catch (NullPointerException e) {
            Log.e("ManageUmbrella", "Bluetooth Error");
        }

        isClicked = false;

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class DialogThread extends Thread {
        BoxButton button;

        private DialogThread(BoxButton button) {
            this.button = button;
        }

        //        BTCommHandler handler = new BTCommHandler();
//        Message message = handler.obtainMessage(BTCommHandler.MESSAGE_RECEIVED);
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {manageUmbrella(button); }

            });
            showRentDialog();
            Log.e("RentAct", "showDialog");
            while (!isClicked) {
            }
            Log.e("RentAct", "isClicked");
            runOnUiThread(new Runnable() {
                @Override
                public void run() { closeUmbrella(button); }

            });
        }

        private void showRentDialog() {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog = ProgressDialog.show(ManagerActivity.this, "",
                            "잠시만 기다려 주십시오.", true);

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (mProgressDialog != null && mProgressDialog.isShowing()) {
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
                                                            isClicked = true;
                                                            dialog.dismiss();
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }, 5000);

                                        }
                                    });
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 1000);

                }
            });
        }


        /*********************다이얼로그*********************************************/

    }
}

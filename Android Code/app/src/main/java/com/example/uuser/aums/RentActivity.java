package com.example.uuser.aums;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uuser.aums.bluetooth.BTConn;

import android.os.Handler;

public class RentActivity extends Activity {

    BoxButton Button1;
    BoxButton Button2;
    BoxButton Button3;
    BoxButton Button4;
    Button ButtonCancel;
    //BoxButton Buttons[]
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
        setContentView(R.layout.activity_rent);

        Button1 = (BoxButton) findViewById(R.id.rent1);
        Button2 = (BoxButton) findViewById(R.id.rent2);
        Button3 = (BoxButton) findViewById(R.id.rent3);
        Button4 = (BoxButton) findViewById(R.id.rent4);
        ButtonCancel = (Button) findViewById(R.id.ButtonCancelRent);

        Button1.setType(BoxButton.RENT);
        Button2.setType(BoxButton.RENT);
        Button3.setType(BoxButton.RENT);
        Button4.setType(BoxButton.RENT);

        final Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        //box = (UmbrellaBox) intent.getSerializableExtra("Box");
        box = UmbrellaBox.getInstance();

        /*********************다이얼로그*********************************************/

        isClicked = false;

        mHandler = new Handler();

        builder = new AlertDialog.Builder(this);
        builder.setTitle("우산대여")
                .setMessage("우산을 뽑고 확인을 눌러 주십시오.\n" +
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
            public void onClick(View v) {
                new DialogThread(Button1).start();
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogThread(Button2).start();
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogThread(Button3).start();
            }
        });
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogThread(Button4).start();
            }
        });

        ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "우산대여가 취소되었습니다."
                        , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            }
        });


    }

    private void rentUmbrella(BoxButton button) {
        user.setUserStatus(User.ISSUED);
        box.setStatus(UmbrellaBox.EMPTY, button.getNumber() - 1);
        Toast.makeText(getApplicationContext(), "우산대여에 성공했습니다."
//                        +"\n반납일은 ~~입니다."
                , Toast.LENGTH_LONG).show();

        BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);


        try {
            BTConn conn = ArduinoAdapter.getConn();
            conn.sendText(String.valueOf(button.getNumber()));
        } catch (NullPointerException e) {
            Log.e("RentUmbrella", "Bluetooth Error");
        }
    }
    private void closeUmbrella(BoxButton button){
        ServerAdapter.minusUmbrella();
        ServerAdapter.setUserIssued(user.getNFCtagID(), user.getID());

        try {
            BTConn conn = ArduinoAdapter.getConn();
            conn.sendText(String.valueOf(button.getNumber()+4));
        } catch (NullPointerException e) {
            Log.e("RentUmbrella", "Bluetooth Error");
        }
        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*********************
     * 다이얼로그
     *********************************************/


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
                public void run() {rentUmbrella(button); }

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
                    mProgressDialog = ProgressDialog.show(RentActivity.this, "",
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
                                            }, 10000);

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

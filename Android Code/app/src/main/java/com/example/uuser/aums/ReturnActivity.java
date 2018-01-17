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

public class ReturnActivity extends Activity {

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
        setContentView(R.layout.activity_return);

        Button1 = (BoxButton) findViewById(R.id.return1);
        Button2 = (BoxButton) findViewById(R.id.return2);
        Button3 = (BoxButton) findViewById(R.id.return3);
        Button4 = (BoxButton) findViewById(R.id.return4);
        ButtonCancel = (Button) findViewById(R.id.ButtonCancelReturn);

        Button1.setType(BoxButton.RETURN);
        Button2.setType(BoxButton.RETURN);
        Button3.setType(BoxButton.RETURN);
        Button4.setType(BoxButton.RETURN);

        final Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        //box = (UmbrellaBox) intent.getSerializableExtra("Box");
        box = UmbrellaBox.getInstance();

        BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);

        /*********************다이얼로그*********************************************/

        isClicked = false;

        mHandler = new Handler();

        builder = new AlertDialog.Builder(this);
        builder.setTitle("우산반납")
                .setMessage("우산을 넣고 확인을 눌러 주십시오.\n" +
                        "확인을 누르면 우산함이 다시 잠깁니다.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isClicked = true;

                    }
                });
        /*********************다이얼로그*********************************************/


        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button1).start();
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button2).start();
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {new DialogThread(Button3).start();
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
//                Toast.makeText(getApplicationContext(), "우산반납이 취소되었습니다."
//                        , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            }
        });



    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void returnUmbrella(BoxButton button){
        user.setUserStatus(User.UNISSUED);
        box.setStatus(UmbrellaBox.OCCUPIED, button.getNumber() - 1);
        Toast.makeText(getApplicationContext(), "우산반납에 성공했습니다.", Toast.LENGTH_LONG).show();

        BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);

//        ServerAdapter.addUmbrella();
//        ServerAdapter.setUserUnissued(user.getNFCtagID(), user.getID());

        try{
            BTConn conn = ArduinoAdapter.getConn();
            conn.sendText(String.valueOf(button.getNumber()));
        }catch (NullPointerException e){
            Log.e("ReturnUmbrella", "Bluetooth Error");
        }

    }
    private void closeUmbrella(BoxButton button){
        ServerAdapter.addUmbrella();
        ServerAdapter.setUserUnissued(user.getNFCtagID(), user.getID());

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
                public void run() {returnUmbrella(button); }

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
                    mProgressDialog = ProgressDialog.show(ReturnActivity.this, "",
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

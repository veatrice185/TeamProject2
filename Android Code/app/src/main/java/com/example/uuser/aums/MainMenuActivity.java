package com.example.uuser.aums;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uuser.aums.bluetooth.BTConn;

public class MainMenuActivity extends Activity {

    Button ButtonRent;
    Button ButtonReturn;
    Button ButtonReport;
    Button ButtonManager;
    Button ButtonLogout;
    //Button ButtonSetting;
    TextView welcome;
    User user;
    UmbrellaBox box;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        box = UmbrellaBox.getInstance();
        //box = (UmbrellaBox) intent.getSerializableExtra("Box");

        ButtonRent = (Button) findViewById(R.id.buttonRent);
        ButtonReturn = (Button) findViewById(R.id.buttonReturn);
        ButtonReport = (Button) findViewById(R.id.buttonReport);
        ButtonManager = (Button) findViewById(R.id.ManagerButton);
        //ButtonSetting = (Button) findViewById(R.id.SettingButton);
        ButtonLogout = (Button) findViewById(R.id.ButtonLogout);
        welcome = (TextView) findViewById(R.id.welcomText);

        if(user.isManager()) {
            welcome.setText("관리자" + "님 환영합니다.");
        }else
            welcome.setText("사용자" + "님 환영합니다.");


        if (user.isManager()) {
            ButtonManager.setVisibility(View.VISIBLE);
//            ButtonSetting.setVisibility(View.VISIBLE);
        } else {
            ButtonManager.setVisibility(View.INVISIBLE);
//            ButtonSetting.setVisibility(View.INVISIBLE);
        }


        ButtonRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.isIssued()) {
                    Toast.makeText(getApplicationContext(), "이미 빌렸습니다.", Toast.LENGTH_LONG).show();
                } else {
                    intent = new Intent(getApplicationContext(), RentActivity.class);
                    intent.putExtra("User", user);
                    //intent.putExtra("Box", box);
                    startActivity(intent);

                    finish();
                }
            }
        });

        ButtonReturn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user.isIssued()) {
                    Intent intent = new Intent(getApplicationContext(), ReturnActivity.class);
                    intent.putExtra("User", user);
                    //intent.putExtra("Box", box);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "빌린 우산이 없습니다.", Toast.LENGTH_LONG).show();
            }
        });


        ButtonReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user.isIssued()) {
                    Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                    intent.putExtra("User", user);
                    //intent.putExtra("Box", box);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "빌린 우산이 없습니다.", Toast.LENGTH_LONG).show();
            }
        });

        ButtonManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                intent.putExtra("User", user);
                //intent.putExtra("Box", box);
                startActivity(intent);
                finish();
            }
        });

//        ButtonSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
//                intent.putExtra("User", user);
//                //intent.putExtra("Box", box);
//                //Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_LONG).show();
//                startActivity(intent);
//                finish();
//            }
//        });

        ButtonLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("User", user);
                //intent.putExtra("Box", box);
                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_LONG).show();
                BTConn conn = ArduinoAdapter.getConn();
                conn.sendText("0");
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



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
//        super.onActivityResult(requestCode, resultCode, intent);
//
//         // if(resultCode == RESULT_OK){
//            user = (User) intent.getSerializableExtra("User");
//            box = (UmbrellaBox) intent.getSerializableExtra("Box");
//
//            user.setUserStatus(User.ISSUED);
//            box.setStatus(UmbrellaBox.EMPTY, 0);
//            Toast.makeText(getApplicationContext(), "우산대여에 성공했습니다." +
//                    "\n반납일은 ~~입니다.", Toast.LENGTH_LONG).show();
//
////        }
//    }


}

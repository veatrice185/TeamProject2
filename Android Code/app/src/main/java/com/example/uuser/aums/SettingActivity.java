//package com.example.uuser.aums;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class SettingActivity extends Activity {
//
//    Button settingButtonConform;
//    Button settingButtonCancel;
//    TextView settingID;
//    Intent intent;
//    User user;
//    UmbrellaBox box;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//
////        final User user = new User();
//
//        intent = getIntent();
//        user = (User) intent.getSerializableExtra("User");
//        box = UmbrellaBox.getInstance();
//        settingButtonConform = (Button) findViewById(R.id.buttonSettingConform);
//        settingButtonCancel = (Button) findViewById(R.id.buttonSettingCancel);
//        settingID = (TextView) findViewById(R.id.BoxIdText);
//
//        settingButtonConform.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                box.setUmbrellaID(Integer.parseInt(settingID.getText().toString()));
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                Toast.makeText(getApplicationContext(), box.getUmbrellaID() + "로 Box ID가 세팅되었습니다.", Toast.LENGTH_LONG).show();
//                intent.putExtra("User", user);
//                startActivity(intent);
//                finish();
//            }
//
//    });
//
//        settingButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                intent.putExtra("User", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//    }
//}
//
//

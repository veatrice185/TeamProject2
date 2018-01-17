package com.example.uuser.aums;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReportActivity extends Activity {
    User user;
    UmbrellaBox box;
    Button ButtonCancel;
    Button ButtonConform;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        final Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        box = UmbrellaBox.getInstance();
        ButtonCancel = (Button) findViewById(R.id.ButtonCancelReport);
        ButtonConform = (Button) findViewById(R.id.ButtonConformReport);

        ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "우산고장신고가 취소되었습니다."
//                        , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            }
        });


        ButtonConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box.addbrokenUmbrella();
                user.setUserStatus(User.UNISSUED);
                Toast.makeText(getApplicationContext(), "우산고장신고가 접수되었습니다."
                        , Toast.LENGTH_LONG).show();

                ServerAdapter.reportBroken();
                ServerAdapter.setUserUnissued(user.getNFCtagID(), user.getID());

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
}
        //box = (UmbrellaBox) intent.getSerializableExtra("Box");

//
//    BoxButton Button1;
//    BoxButton Button2;
//    BoxButton Button3;
//    BoxButton Button4;
//    Button ButtonCancel;
//    //BoxButton Buttons[]
//    User user;
//    UmbrellaBox box;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rent);
//
//        Button1 = (BoxButton) findViewById(R.id.rent1);
//        Button2 = (BoxButton) findViewById(R.id.rent2);
//        Button3 = (BoxButton) findViewById(R.id.rent3);
//        Button4 = (BoxButton) findViewById(R.id.rent4);
//
//        Button1.setType(BoxButton.RETURN);
//        Button2.setType(BoxButton.RETURN);
//        Button3.setType(BoxButton.RETURN);
//        Button4.setType(BoxButton.RETURN);
//
//        final Intent intent = getIntent();
//        user = (User) intent.getSerializableExtra("User");
//        //box = (UmbrellaBox) intent.getSerializableExtra("Box");
//        box = UmbrellaBox.getInstance();
//
//        BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
//
//
//
//        Button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //intent.putExtra("Box", box);
//                user.setUserStatus(User.UNISSUED);
//                box.setStatus(UmbrellaBox.OCCUPIED, 0);
//                Toast.makeText(getApplicationContext(), "우산반납에 성공했습니다."
////                        +"\n반납일은 ~~입니다."
//                        , Toast.LENGTH_LONG).show();
//
//                BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
//                //intent = new Intent(getApplicationContext(), RentActivity.class);
//
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                intent.putExtra("User", user);
//                //intent.putExtra("Box", box);
//                startActivity(intent);
//                finish();
//            }
//        });
//        Button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                //intent.putExtra("Box", box);
//                user.setUserStatus(User.UNISSUED);
//                box.setStatus(UmbrellaBox.OCCUPIED, 1);
//                Toast.makeText(getApplicationContext(), "우산반납에 성공했습니다.", Toast.LENGTH_LONG).show();
//
//                BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
//                //intent = new Intent(getApplicationContext(), RentActivity.class);
//                intent.putExtra("User", user);
//                //intent.putExtra("Box", box);
//                startActivity(intent);
//                finish();
//            }
//        });
//        Button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setUserStatus(User.UNISSUED);
//                box.setStatus(UmbrellaBox.OCCUPIED, 2);
//                Toast.makeText(getApplicationContext(), "우산반납에 성공했습니다.", Toast.LENGTH_LONG).show();
//
//                BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
//
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                intent.putExtra("User", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//        Button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //intent.putExtra("Box", box);
//                user.setUserStatus(User.UNISSUED);
//                box.setStatus(UmbrellaBox.OCCUPIED, 3);
//                Toast.makeText(getApplicationContext(), "우산반납에 성공했습니다.", Toast.LENGTH_LONG).show();
//
//                BoxAdapter.boxToButton(box, Button1, Button2, Button3, Button4);
//
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                intent.putExtra("User", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        ButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "우산반납이 취소되었습니다."
//                        , Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
//                intent.putExtra("User", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//    }


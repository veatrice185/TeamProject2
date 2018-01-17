package com.example.uuser.aums;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by UUser on 2015-11-04.
 */
public class BoxButton extends Button {

    public static final boolean EMPTY = false;
    public static final boolean OCCUPIED = true;

    final String Green = "#60fbb3";
    final String Gray = "#b5b5b5";

    public static final int RENT = 0;
    public static final int RETURN = 1;
    //public static final int REPORT = 2;
    public static final int MANAGE = 3;

    private int number;
    private boolean status;
    private int type;

    public BoxButton(Context context) {
        super(context);
        status = EMPTY;
    }
    public BoxButton(Context context, AttributeSet attrs){
        super(context, attrs);
        status = EMPTY;
    }
    public BoxButton(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        status = EMPTY;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
    public void setType(int type){
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
        this.setText(String.valueOf(number)+"번");
    }
    public int getNumber(){
        return this.number;
    }

    public void setButtonColor(){
        this.setTextColor(Color.parseColor("Black"));
        if(this.type == RENT) {
            if (this.status == EMPTY) {
                this.setBackgroundColor(Color.parseColor(Gray));        //회색
                this.setEnabled(false);                                 //비활성화
                this.setNumber(number);
                this.setText(this.getText() + "\n우산없음");
            } else {
                this.setBackgroundColor(Color.parseColor(Green));       //녹색
                this.setEnabled(true);                                  //활성화
                this.setNumber(number);
                this.setText(this.getText() + "\n우산있음");
            }
        }
        else if(this.type == RETURN){
            if(this.status == OCCUPIED){
                this.setBackgroundColor(Color.parseColor(Gray));        //회색
                this.setEnabled(false);                                 //비활성화
                this.setNumber(number);
                this.setText(this.getText() + "\n우산있음");
            }
            else{
                this.setBackgroundColor(Color.parseColor(Green));       //녹색
                this.setEnabled(true);                                  //활성화
                this.setNumber(number);
                this.setText(this.getText() + "\n우산없음");
            }
        }
        else {
            if(this.status == OCCUPIED){
                this.setBackgroundColor(Color.parseColor(Gray));        //회색
                this.setEnabled(true);                                 //비활성화
                this.setNumber(number);
                this.setText(this.getText() + "\n우산있음");
            }
            else{
                this.setBackgroundColor(Color.parseColor(Green));       //녹색
                this.setEnabled(true);                                  //활성화
                this.setNumber(number);
                this.setText(this.getText() + "\n우산없음");
            }
        }
    }




}

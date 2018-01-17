package com.example.uuser.aums;

/**
 * Created by UUser on 2015-11-04.
 */
public class BoxAdapter {

    //Button button[] = new Button[4];

    public static void boxFromDB(UmbrellaBox box){

        /*여기에 DB 어댑터 부분이 들어가야함
         *그러나 아직 구현하지 않았으므로
         * 적당히 전부 빈칸으로 채운다.
         */

        for(int i=0; i<4; i++)
            box.setStatus(UmbrellaBox.OCCUPIED, i);

    }

    public static void boxToButton(UmbrellaBox box, BoxButton b1, BoxButton b2, BoxButton b3, BoxButton b4){
        b1.setStatus(box.getStatus(0));
        b2.setStatus(box.getStatus(1));
        b3.setStatus(box.getStatus(2));
        b4.setStatus(box.getStatus(3));
        b1.setNumber(1);
        b2.setNumber(2);
        b3.setNumber(3);
        b4.setNumber(4);

        b1.setButtonColor();
        b2.setButtonColor();
        b3.setButtonColor();
        b4.setButtonColor();
    }

    public static void boxToButton(UmbrellaBox box, BoxButton[] buttons, int size){
        for(int i=0; i<size; i++){
            buttons[i].setStatus(box.getStatus(i));
            buttons[i].setButtonColor();
        }
    }

}

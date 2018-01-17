package com.example.uuser.aums;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by UUser on 2015-11-03.
 */
public class LoginTask {

    public static boolean authorize(String TagID) {

        User user = new User(TagID);

        ServerAdapter.login(user, TagID);
        Log.e("user:", user.toString());
        if (!user.equals(null))
            return true;
        Log.e("LT_Authorization fail", null);
        return false;
    }

    public static User login(String tagID){
        User user = new User(tagID);

        Log.e("LT_user :", user.toString());
        try {
            ServerAdapter.login(user, tagID);
            if(!user.equals(null)) {
                Log.e("LT_afterLogin_user:", user.getName());
                return user;
            }
        }catch (NullPointerException e){
            Log.e("LT_login fail", e.toString());
            return null;
        }
        return null;
        /*
        if(ID.equals("Manager") || ID.equals("Admin") || ID.equals("admin"))
            return new User(User.MANAGER);
        else
            return  new User(User.CUSTOMER);
        */

    }

    private class LoginAsyncTask extends AsyncTask<String, Integer, String> {

        User user;

        public LoginAsyncTask(User user){
            this.user = user;
        }
        protected String doInBackground(String... urls) {
            //                        String[] a = {w,leftSel,rightSel};
            String str = null;

            if(true){
                user = LoginTask.login("customer");

                try{
                }
                catch (NullPointerException e){
                    Log.e("MainAct_Auth fail", e.toString());
                }


            }

            return str;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {

        }
    }
    //public static User login(String Tag)
}

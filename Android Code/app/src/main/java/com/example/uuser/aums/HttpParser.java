package com.example.uuser.aums;
        import android.util.Log;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * Created by UUser on 2015-06-07.
 */
public class HttpParser {

    public final static String POST = "POST";
    public final static String GET = "GET";


    public static String parseURL(String urlAddress){
        InputStream in;

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection con = null;

            con = (HttpURLConnection) url.openConnection();
            con.connect();

            in = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String reader_temp;
            while((reader_temp = reader.readLine()) != null){
                stringBuilder.append(reader_temp);
            }

            return stringBuilder.toString();

        }
        catch(Exception e){
            Log.e("httpPraseError", e.toString());
            return null;
        }

    }

    public static String parseURL(String urlAddress, String method){

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection con = null;

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.connect();

            InputStream in = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String reader_temp;
            while((reader_temp = reader.readLine()) != null){
                stringBuilder.append(reader_temp);
            }

            return stringBuilder.toString();

        }
        catch(Exception e){
            Log.e("httpPraseError", e.toString());
            return null;
        }
    }
}
package com.main.genietalk.util;

/**
 * Created by NT on 8/27/2017.
 */

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();
    String requestString = "0|||06-Dec-2017 at 5:11:40 PM|||0|||hi|||0|||9D01F285-0985-4FAA-AA87-05A6B45E7388|||";
    String url = "http://192.168.1.92/genieapptest/api/version1/api.php";
    AESCrypt aes;
    public HttpHandler() {
    }
/*"5a1d4da19465a|||06-Dec-2017 at 4:45:40 PM||||||hi|||1|||9D01F285-0985-4FAA-AA87-05A6B45E7388|||"*/
    public String makeServiceCall(String reqUrl,String PostData) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(PostData);
            wr.flush();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }
    public void okHttpAsyncCallPost(){
         MediaType MEDIA_TYPE =
                MediaType.parse("application/x-www-form-urlencoded");
        final OkHttpClient client = new OkHttpClient();
         aes = new AESCrypt();
        try{
            requestString = aes.bytesToHex(aes.encrypt(requestString));
            requestString = "q=" + requestString;
            Log.e(TAG, "Response encMsg : " + requestString);
        }catch (Exception e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE,
                requestString);

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                //.addHeader("Content-Type", "application/json")
                //.addHeader("Authorization", "Your Token")
                //.addHeader("cache-control", "no-cache")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);

            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {

                String mMessage = response.body().string();

                    try {
                       String messageAfterDecrypt = new String(aes.decrypt(mMessage));
                        Log.e(TAG, "Response messageAfterDecrypt: " + messageAfterDecrypt);

                    } catch (Exception e){
                        e.printStackTrace();
                    }

            }
        });
    }
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
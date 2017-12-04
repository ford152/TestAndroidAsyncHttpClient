package com.example.oem.test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;



public class MainActivity extends AppCompatActivity {

    TextView tW;

    public MainActivity() throws NoSuchAlgorithmException {

    }


    String run() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header("Host", "devhk3.azureedge.net")
                .sni("devhk3.azureedge.net")
                .url("https://software-download.office.microsoft.com")
                .build();

        ResponseBody body = client.newCall(request).execute().body();
        if (body == null) {
            return "body is empty";
        }
        return body.string();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tW = (TextView)findViewById(R.id.textView);
    }

    public void sendHttp(View view) {
        new MyTask().execute();
    }


    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            tW.setText(result);
            super.onPostExecute(result);
        }
    }
}

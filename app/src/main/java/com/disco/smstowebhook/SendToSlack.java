package com.disco.smstowebhook;

import android.os.AsyncTask;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import androidx.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendToSlack extends AsyncTask<String, Void, String> {
    private String urlString;

    // 생성자에서 Context 전달
    public SendToSlack(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        urlString = sharedPreferences.getString("webhook_url", "https://default-url.com");
    }

    @Override
    protected String doInBackground(String... params) {
        String data = params[0];

        try {
            Log.d("SendToSlack", urlString);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] postData = data.getBytes();
                wr.write(postData);
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

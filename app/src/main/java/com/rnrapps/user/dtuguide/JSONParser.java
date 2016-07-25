package com.rnrapps.user.dtuguide;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rohanpc on 2/18/2016.
 */
public class JSONParser {
    InputStream inputStream;
    JSONArray jsonArray;
    String data;
    StringBuilder sb;
    HttpClient client;
    HttpGet httpGet;
    HttpResponse response;
    StatusLine statusLine;
    int statuscode;
    public JSONParser() {
    }
    public JSONArray getFromURL(String url) {
        sb = new StringBuilder();
        client = new DefaultHttpClient();
        httpGet = new HttpGet(url);
        try {
            response = client.execute(httpGet);
            statusLine = response.getStatusLine();
            statuscode = statusLine.getStatusCode();
            if (statuscode == 200) {
                HttpEntity httpentity = response.getEntity();
                InputStream content = httpentity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                Log.e("==>", "Failed to download file");
            }
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonArray = new JSONArray(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
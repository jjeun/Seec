package com.seec;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;


/*
* Authors: Unknown
* Implemented by: Jesse Jeun
* Date: 10-19-2015
* Description: JSONParser class available to developers to parse JSON information.
*
*/


public class JSONParser{

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> params) {

        is = null;
        jObj = null;
        json = "";

        // Making HTTP request
        try {

            Log.i("url", url);

            // check for request method
            if(method == "POST"){
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();

                for (int i = 0; i < params.size(); i++){
                    if(i == 0){
                        url += "?" + params.get(i).getName()+"="+params.get(i).getValue()+"";
                    }
                    else {
                        url += "&" + params.get(i).getName() + "=" + params.get(i).getValue() + "";
                    }
                }

                Log.i("url", url);
                HttpPost httpPost = new HttpPost(url);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if(method == "GET"){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();

                for (int i = 0; i < params.size(); i++){
                    if(i == 0){
                        url += "?" + params.get(i).getName()+"="+params.get(i).getValue()+"";
                    }
                    else {
                        url += "&" + params.get(i).getName() + "=" + params.get(i).getValue() + "";
                    }
                }

                Log.i("url", url);
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.i("Stage 1", "Stage 1");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

                Log.i("1 ", line);
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        Log.i("Stage 2", "Stage 2");

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        Log.i("Stage 3", "Stage 3");

        // return JSON String
        return jObj;

    }
}
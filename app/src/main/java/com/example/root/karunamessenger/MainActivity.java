package com.example.root.karunamessenger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {
    private final static String JSON_URI = "http://mobevo.ext.terrhq.ru/shr/j/ru/technology.js";
    private final static int MILLISECONDS_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadJsonTask().execute(JSON_URI);
        Handler handler = new Handler();
        final Intent intent = new Intent(this, MessageListActivity.class);
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                startActivity(intent);
            }
        }, MILLISECONDS_DELAY);
    }

    private String getJSON(String uri, int delay) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[2048];

        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(delay);
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

        int count;
        do {
            count = inputStream.read(buffer);
            if (count > 0) {
                sb.append(new String(buffer, 0, count));
            }
        } while (count != -1);

        inputStream.close();
        urlConnection.disconnect();
        return sb.toString();
    }

    private class DownloadJsonTask extends AsyncTask<String, Void, JSONObject> {

        protected JSONObject doInBackground(String... urls) {
            try {
                MyDB dbHelper = new MyDB(getApplicationContext());
                String result = getJSON(urls[0], MILLISECONDS_DELAY);
                JSONObject jsonObject = new JSONObject(result);
                JSONObject technologies = jsonObject.getJSONObject("technology");
                Iterator<?> keys = technologies.keys();
                int id = 0;
                dbHelper.deleteRows();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    String title = technologies.getJSONObject(key).getString("title");
                    String picture = technologies.getJSONObject(key).getString("picture");
                    String info;
                    try {
                        info = technologies.getJSONObject(key).getString("info");
                    } catch (JSONException ex) {
                        info = null;
                    }
                    dbHelper.createRecords(id, title, picture, info);
                    id++;
                }
                return technologies;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

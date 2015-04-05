package com.example.root.karunamessenger;

import android.app.Application;
import android.content.Context;

/**
 * Created by root on 05.04.15.
 */
public class KarunaMessenger extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        KarunaMessenger.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return KarunaMessenger.context;
    }
}
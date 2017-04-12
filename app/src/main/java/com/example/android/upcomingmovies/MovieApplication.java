package com.example.android.upcomingmovies;

import android.app.Application;

/**
 * Created by S on 12/04/2017.
 */

public class MovieApplication extends Application {

    private static MovieApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MovieApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}

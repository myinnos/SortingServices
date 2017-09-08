package com.omnify.hire;

/**
 * Created by myinnos on 08/09/17.
 */

import android.app.Application;

import com.omnify.hire.extras.Remember;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(getApplicationContext(), BuildConfig.APPLICATION_ID);
    }

}

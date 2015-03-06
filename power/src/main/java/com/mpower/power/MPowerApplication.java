package com.mpower.power;

import android.app.Application;


/**
 * Application object
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class MPowerApplication extends Application {

    public static String NORMAL = "NORMAL";
    public static String MID = "MID";
    public static String OVER = "OVER";
    public static String STATE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();

        STATE = MPowerApplication.NORMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

package com.mpower.power.utils;

import com.mpower.power.pojos.User;

/**
 * Created by eranga on 3/5/15
 */
public class MPowerConstants {
    public static final User USER = new User("eranga", "123");

    public static final String API_BASE = "http://10.2.2.132:8000/";
    public static final String GCM_API = API_BASE + "api/v1/devices/";

    public static final String SENZ_SERVER = "ws://10.2.2.132:9000";

    public final static String GOOGLE_PROJECT_NO = "708670684165";
}
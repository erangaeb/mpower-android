package com.mpower.power.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mpower.power.R;

/**
 * Utility class to deal with shared preference
 *
 * @author eranga.herath@pagero.com (eranga herath)
 */
public class PreferenceUtils {

    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "app_version";

    /**
     * Save GCM registration id and current app version shared preference
     * @param context application context
     * @param registrationId device GCM registration id
     */
    public static void saveGcmRegistrationId(Context context, String registrationId) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();

        // keys should be constants as well, or derived from a constant prefix in a loop.
        editor.putString(PROPERTY_REG_ID, registrationId);
        editor.putInt(PROPERTY_APP_VERSION, getAppVersion(context));
        editor.commit();
    }

    /**
     * Get GCM registration id of the device
     * Id saved in shared preference
     * @param context application context
     * @return registration id
     */
    public static String getGcmRegistrationId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String registrationId = preferences.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }

        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = preferences.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            return "";
        }

        return registrationId;
    }

    /**
     * Get unique device id which relates with this device
     * @return android device id / this is a unique id
     */
    public static String getAndroidId(Context context) {
        return android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
    }

    /**
     * Get app version defined in Manifest,
     * Use this function to check weather app has been updated
     *
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

}

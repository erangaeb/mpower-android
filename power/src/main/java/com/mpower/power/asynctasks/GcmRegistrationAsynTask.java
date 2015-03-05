package com.mpower.power.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mpower.power.asynctasks.managers.GcmDeviceManager;
import com.mpower.power.listeners.GcmRegistrationServiceListener;
import com.mpower.power.pojos.Device;
import com.mpower.power.utils.MPowerConstants;
import com.mpower.power.utils.PreferenceUtils;

import org.apache.http.auth.AuthenticationException;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Asynchronously handle google cloud message registration
 *
 * @author eranga herat(eranga.herath@pagero.com)
 */
public class GcmRegistrationAsynTask extends AsyncTask<String, String, String> {

    private Context context;
    private GcmRegistrationServiceListener listener;
    private Device device;
    private GoogleCloudMessaging googleCloudMessaging;
    private String registrationId;

    static final String TAG = GcmRegistrationAsynTask.class.getName();

    public GcmRegistrationAsynTask(Context context, GcmRegistrationServiceListener listener, Device device) {
        this.context = context;
        this.listener = listener;
        this.device = device;
        googleCloudMessaging = GoogleCloudMessaging.getInstance(this.context);
    }

    /**
     * We are doing here
     *      1. register device in google cloud messaging
     *      2. sync device registration ID with pay2n server
     *      3. store registration ID in shared preference
     */
    @Override
    protected String doInBackground(String... params) {
        try {
            // get registration ID
            registrationId = PreferenceUtils.getGcmRegistrationId(context);
            if (registrationId.isEmpty()) {
                registrationId = googleCloudMessaging.register(MPowerConstants.GOOGLE_PROJECT_NO);
                this.device.setRegistrationId(registrationId);
            }
            Log.d(TAG, registrationId);

            PreferenceUtils.saveGcmRegistrationId(context, registrationId);
            return "1";
//            // sync registration ID with server
//            try {
//                //GcmDeviceManager.sendDeviceToMPowerService(MPowerConstants.GCM_API, device, MPowerConstants.USER);
//                PreferenceUtils.saveGcmRegistrationId(context, registrationId);
//
//                return "1";
//            } catch (AuthenticationException e) {
//                return "-1";
//            } catch (URISyntaxException e) {
//                return "-3";
//            } catch (IOException e) {
//                return "-2";
//            } catch (JSONException e) {
//                return "-3";
//            }
        } catch (IOException ex) {
            Log.e(TAG, "gcm registration fails");
            Log.e(TAG, ex.toString());

            return "-4";
        }
    }

    @Override
    protected void onPostExecute(String status) {
        listener.onPostGcmRegistration(status);
    }
}

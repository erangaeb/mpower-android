package com.mpower.power;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mpower.power.ui.HomeActivity;
import com.mpower.power.utils.MPowerConstants;

import org.json.JSONException;


/**
 * Intent service that handles GCM messages
 * This snippet processes the GCM message based on message type,
 * and posts the result in a notification
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class GcmIntentService extends IntentService {

    static final String TAG = GcmIntentService.class.getName();
    private static final int NOTIFICATION_ID = 1;

    public GcmIntentService() {
        super(MPowerConstants.GOOGLE_PROJECT_NO);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // Post notification of received message.
                Log.d(TAG, extras.getString("msg"));
                showNotification(extras.getString("msg"));
            } else {
                Log.e(TAG, "unsupported message type");
            }
        }

        // release the wake lock provided by the WakefulBroadcastReceiver
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    /**
     * Show notification when message comes from GCM,
     * There are two types of messages that need to be display
     *      1. New invoice received - when invoice received from pagero online pay2n sends a push message to mobile
     *      2. Invoice due date reminder - When have overdue invoices pay2n sends push messages to mobile
     * @param message message from GCM
     */
    private void showNotification(String message) {
        // parser message and get the text that need to be display
        // message comes as JSON string
        try {
            String notificationMessage = "Over usage switch";
            // detect consumption type according to message
            if (message.equalsIgnoreCase("OVER")) {
                MPowerApplication.STATE = MPowerApplication.OVER;
                notificationMessage = "Over usage switch";
            } else {
                MPowerApplication.STATE = MPowerApplication.NORMAL;
                notificationMessage = "Normal usage switch";
            }

            // add message and status to bundle
            Bundle extra = new Bundle();
            extra.putBoolean("NOTIFICATION_RECEIVED", true);
            extra.putString("NOTIFICATION_MESSAGE", message);

            // pending intent
            Intent notificationIntent = new Intent(this, HomeActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //notificationIntent.putExtras(extra);
            notificationIntent.putExtra("TT", 567);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            // build notification
            // NotificationMessage notificationMessage = JSONParser.getNotificationMessage(this, message);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(notificationMessage)
                    .setTicker(message)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setLights(0xff0db00d, 500, 3000);

            // notify
            NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

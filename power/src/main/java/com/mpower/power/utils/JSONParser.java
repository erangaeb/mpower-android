package com.mpower.power.utils;

import com.mpower.power.pojos.Device;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Utility class to handle JSON parsing related functions
 *
 * @author eranga.herath@pagero.com (eranga herath)
 */
public class JSONParser {

    /**
     * Create JSON string with device parameters, only add non empty attributes here
     * @param device GCM device
     * @return JSON string
     * @throws org.json.JSONException
     */
    public static String getJsonStringForCreateDevice(Device device) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        // add
        //  1. registration id
        //  2. name
        //  3. device_id
        jsonObject.put("name", "MPower");
        if(!device.getDeviceId().isEmpty()) {
            jsonObject.put("device_id", device.getDeviceId());
        }
        if(!device.getRegistrationId().isEmpty()) {
            jsonObject.put("reg_id", device.getRegistrationId());
        }

        return jsonObject.toString();
    }

    /**
     * Create JSON string with device parameters, this method use when updating GCM device
     * @param device GCM device
     * @return JSON string
     * @throws org.json.JSONException
     */
    public static String getJsonStringForUpdateDevice(Device device) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        // add all available fields
        //  1. id
        //  2. name
        //  3. add reg_id if it not empty
        // jsonObject.put("id", device.getId());
        jsonObject.put("name", "MPower");
        jsonObject.put("device_id", device.getDeviceId());
        if(!device.getRegistrationId().isEmpty()) {
            jsonObject.put("reg_id", device.getRegistrationId());
        }

        return jsonObject.toString();
    }

    /**
     * Parse server response and extract the device
     * @param serverResponse response
     * @return downloaded device
     */
    public static Device parserDeviceResponse(String serverResponse) throws JSONException {
        JSONObject object = new JSONObject(serverResponse);

        // extract response details and create device
        String id = object.getString("id").trim();
        String deviceId = object.getString("device_id").trim();

        return new Device(id, deviceId, "REG_ID");
    }

//    /**
//     * Parse incoming message and get following texts
//     *  1. tickerText
//     *  2. notificationText
//     * Incoming message comes with "type" and "args" parameters, need to determine above tow texts according these
//     * parameters
//     * @param message message from GCM
//     * @return NotificationMessage - notification message
//     */
//    public static NotificationMessage getNotificationMessage(Context context, String message) throws JSONException, UnsupportedMessageException {
//        JSONObject jsonObject = new JSONObject(message);
//        String type = jsonObject.getString("type");
//        int arg = jsonObject.getInt("args");
//
//        if(type.equalsIgnoreCase("new_invoice_received")) {
//            return new NotificationMessage(context.getString(R.string.new_invoice_received), context.getString(R.string.new_invoice_received_pagero));
//        } else if(type.equalsIgnoreCase("due_date_notification")) {
//            String tickerText = context.getString(R.string.due_date_reminder);
//            String messageText = context.getString(R.string.due_date_reminder_message, arg);
//            return new NotificationMessage(tickerText, messageText);
//        } else {
//            // unsupported text
//            throw new UnsupportedMessageException();
//        }
//    }
}

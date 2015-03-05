package com.mpower.power.asynctasks.managers;

import com.mpower.power.pojos.Device;
import com.mpower.power.pojos.User;
import com.mpower.power.utils.JSONParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Handle GCM device from backend device API
 *
 * @author eranga.herath@pagero.com (eranga herath)
 */
public class GcmDeviceManager {

    /**
     * Create/update device in pay2n
     * Actually send device with registration ID to server over HTTP
     *
     * @return device create/update status
     */
    public static void sendDeviceToPay2n(String url, Device device, User user) throws IOException,
            AuthenticationException, JSONException, URISyntaxException {
        // create device in pay2n
        //GcmDeviceManager.createGcmDevice(url, user, device);

    }

//    /**
//     * Create GCM device in order to register for GCM service
//     * @param url API url
//     * @param user current user
//     * @param device this GCM device
//     */
//    public static void createGcmDevice(String url, User user, Device device) throws URISyntaxException,
//                                                                                    AuthenticationException,
//                                                                                    IOException,
//                                                                                    JSONException {
//        // use POST request with basic authentication
//        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user.getUsername(), user.getPassword());
//
//        // POST request to server
//        URI uri = new URI(url);
//        HttpPost httpPost = new HttpPost(uri);
//        httpPost.addHeader(new BasicScheme().authenticate(credentials, httpPost));
//
//        StringEntity entity = new StringEntity(JSONParser.getJsonStringForCreateDevice(device), HTTP.UTF_8);
//        httpPost.setEntity(entity);
//
//        HttpResponse httpResponse = HttpManager.execute(httpPost);
//        int status = httpResponse.getStatusLine().getStatusCode();
//
//        // api return status 201 in successful object create
//        switch (status) {
//            case HttpStatus.SC_CREATED:
//                // successfully created device
//                break;
//            case HttpStatus.SC_UNAUTHORIZED:
//                // unauthorized request
//                throw new AuthenticationException();
//            default:
//                // cannot process request fot another reason
//                throw new CannotProcessRequestException();
//        }
//    }

//    /**
//     * Update GCM device, basically we un register GCM service from here
//     * @param url API url
//     * @param user current user
//     * @param device GCM device
//     */
//    public static void updateGcmDevice(String url, User user, Device device) throws JSONException,
//            IOException,
//            AuthenticationException,
//                                                                                    CannotProcessRequestException,
//            URISyntaxException {
//        // url creates with device id
//        // https://www.pay2n.com/api/v1/devices/<device_id>
//        String deviceLocation = url + device.getDeviceId();
//
//        // use PUT request with basic authentication
//        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user.getUsername(), user.getPassword());
//
//        // PUT request to server
//        URI uri = new URI(deviceLocation);
//        HttpPut httpPut = new HttpPut(uri);
//        httpPut.setHeader(Pay2nApplication.ACCEPT, "application/json");
//        httpPut.setHeader(Pay2nApplication.CONTENT_TYPE, "application/json");
//        httpPut.addHeader(Pay2nApplication.X_REQUESTED_WITH, "XMLHttpRequest");
//        httpPut.addHeader(new BasicScheme().authenticate(credentials, httpPut));
//
//        StringEntity entity = new StringEntity(JSONParser.getJsonStringForUpdateDevice(device), HTTP.UTF_8);
//        httpPut.setEntity(entity);
//
//        HttpResponse httpResponse = HttpManager.execute(httpPut);
//        int status = httpResponse.getStatusLine().getStatusCode();
//
//        // API return status 204 in successful object update
//        switch (status) {
//            case HttpStatus.SC_NO_CONTENT:
//                // successfully created device
//                break;
//            case HttpStatus.SC_UNAUTHORIZED:
//                // unauthorized request
//                throw new AuthenticationException();
//            default:
//                // cannot process request fot another reason
//                throw new CannotProcessRequestException();
//        }
//    }

}

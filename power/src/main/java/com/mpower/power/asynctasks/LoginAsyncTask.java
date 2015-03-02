package com.mpower.power.asynctasks;

import android.os.AsyncTask;

import com.mpower.power.listeners.LoginTaskListener;
import com.mpower.power.pojos.responses.LoginResponse;
import com.mpower.power.ui.LoginActivity;

/**
 * Async tack to process authentication functions
 * Send HTTP requests to MPower service in order to do authentication work
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class LoginAsyncTask extends AsyncTask {

    LoginActivity activity;
    LoginTaskListener loginTaskListener;

    /**
     * Init components
     * @param activity LoginActivity
     */
    public LoginAsyncTask(LoginActivity activity) {
        this.activity = activity;
        this.loginTaskListener = activity;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        // TODO send login request to backend
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        loginTaskListener.onPostLogin(new LoginResponse(200, "Login Success"));
    }
}

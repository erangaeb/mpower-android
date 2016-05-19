package com.mpower.power.asynctasks;

import android.os.AsyncTask;

/**
 * Async task to handle power API requests
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class PowerAPICallAsyncTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);

        //loginTaskListener.onPostLogin(new LoginResponse(200, "Login Success"));
    }

}

package com.mpower.power.listeners;

import com.mpower.power.pojos.responses.LoginResponse;

/**
 * Listen for login async task completion
 * When async task finish its functionality, 'onPostAuthenticate' function will be fired
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public interface LoginTaskListener {
    public void onPostLogin(LoginResponse loginResponse);
}

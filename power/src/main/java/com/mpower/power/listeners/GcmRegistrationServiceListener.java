package com.mpower.power.listeners;

/**
 * Listen for google could messaging service registration
 *
 * @author eranga.herath@pagero.com (eranga herath)
 */
public interface GcmRegistrationServiceListener {
    public void onPostGcmRegistration(String status);
}

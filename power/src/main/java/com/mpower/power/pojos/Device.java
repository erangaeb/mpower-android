package com.mpower.power.pojos;

/**
 * POJO class to hold GCM Device
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class Device {
    String id;
    String deviceId;
    String registrationId;

    public Device(String id, String registrationId, String deviceId) {
        this.id = id;
        this.registrationId = registrationId;
        this.deviceId = deviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}

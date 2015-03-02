package com.mpower.power.pojos;

import com.mpower.power.enums.PowerUsage;

/**
 * POJO to hold daily power usage
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class Summary {
    String date;
    PowerUsage usage;
    String voltage;
    String current;
    String frequency;

    public Summary(String date, PowerUsage usage, String voltage, String current, String frequency) {
        this.date = date;
        this.usage = usage;
        this.voltage = voltage;
        this.current = current;
        this.frequency = frequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PowerUsage getUsage() {
        return usage;
    }

    public void setUsage(PowerUsage usage) {
        this.usage = usage;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}

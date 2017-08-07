package com.example.root.jsonparsingwithrealm.webservices;

/**
 * Created by root on 6/8/17.
 */

public class ServiceResponse {
    boolean isStatus;
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

}

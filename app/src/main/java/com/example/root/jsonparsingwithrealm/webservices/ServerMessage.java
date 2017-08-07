package com.example.root.jsonparsingwithrealm.webservices;

/**
 * Created by root on 6/8/17.
 */

public class ServerMessage {
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String success;
    String error_code;
    String message;
}

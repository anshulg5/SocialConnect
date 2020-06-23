package com.flock.frule.model;

/**
 * This class is used as a response to return a boolean
 * status from a method along with a added message.
 */
public class Response {
    private boolean status;
    private String message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

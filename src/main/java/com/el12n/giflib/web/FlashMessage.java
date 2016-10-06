package com.el12n.giflib.web;

/**
 * Created by Alvaro De la Cruz on 10/6/2016.
 */
public class FlashMessage {

    private String message;
    private Status status;

    public FlashMessage(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public static enum Status {
        SUCCESS,
        FAILURE
    }

}

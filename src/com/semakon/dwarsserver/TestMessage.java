package com.semakon.dwarsserver;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class TestMessage {

    private String sender;
    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return sender + ": " + message;
    }

}

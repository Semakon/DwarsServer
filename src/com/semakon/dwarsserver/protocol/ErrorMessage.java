package com.semakon.dwarsserver.protocol;

/**
 * Author:  M.P. de Vries
 * Date:    28-10-2017
 */
public class ErrorMessage extends Message {

    private String error;
    private String message;

    public ErrorMessage(MessageType type) {
        super(type);
    }

    public ErrorMessage(MessageType type, String error, String message) {
        super(type);
        this.error = error;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error + " - " + message;
    }

}

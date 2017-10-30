package com.semakon.dwarsserver.protocol;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class BroadcastMessage extends Message {

    private String sender;
    private String message;

    public BroadcastMessage(MessageType type) {
        super(type);
    }

    public BroadcastMessage(MessageType type, String sender, String message) {
        super(type);
        this.sender = sender;
        this.message = message;
    }

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
        return sender + " broadcasted \"" + message + "\"";
    }

}

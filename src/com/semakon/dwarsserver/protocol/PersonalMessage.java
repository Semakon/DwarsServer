package com.semakon.dwarsserver.protocol;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class PersonalMessage extends Message {

    private String sender;
    private String recipient;
    private String message;

    public PersonalMessage(MessageType type) {
        super(type);
    }

    public PersonalMessage(MessageType type, String sender, String recipient, String message) {
        super(type);
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return sender + " sent \"" + message + "\" to " + recipient;
    }

}

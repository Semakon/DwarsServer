package com.semakon.dwarsserver.protocol;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public abstract class Message {

    private MessageType type;

    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}

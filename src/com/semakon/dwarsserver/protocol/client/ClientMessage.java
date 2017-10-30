package com.semakon.dwarsserver.protocol.client;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public abstract class ClientMessage {

    private ClientMessageType type;

    public ClientMessage(ClientMessageType type) {
        this.type = type;
    }

    public ClientMessageType getType() {
        return type;
    }

    public void setType(ClientMessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}

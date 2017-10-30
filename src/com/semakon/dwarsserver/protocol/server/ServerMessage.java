package com.semakon.dwarsserver.protocol.server;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public abstract class ServerMessage {

    private ServerMessageType type;

    public ServerMessage(ServerMessageType type) {
        this.type = type;
    }

    public ServerMessageType getType() {
        return type;
    }

    public void setType(ServerMessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}

package com.semakon.dwarsserver.protocol.server;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class LoginFail extends ServerMessage {

    private String reason;

    public LoginFail(ServerMessageType type) {
        super(type);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}

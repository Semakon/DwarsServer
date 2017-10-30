package com.semakon.dwarsserver.protocol.client;

import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class LoginAttempt extends ClientMessage {

    private String username;
    private String password;

    public LoginAttempt(ClientMessageType type) {
        super(type);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

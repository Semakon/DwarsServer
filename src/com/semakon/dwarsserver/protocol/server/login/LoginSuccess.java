package com.semakon.dwarsserver.protocol.server.login;

import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class LoginSuccess extends ServerMessage {

    private User user;

    public LoginSuccess(ServerMessageType type) {
        super(type);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.semakon.dwarsserver.protocol.server.anytimers;

import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class EditAnytimerSuccess extends ServerMessage {

    private int aid;

    public EditAnytimerSuccess(ServerMessageType type) {
        super(type);
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

}

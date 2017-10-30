package com.semakon.dwarsserver.protocol.client.anytimers;

import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class RemoveAnytimer extends ClientMessage {

    private int aid;

    public RemoveAnytimer(ClientMessageType type) {
        super(type);
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

}

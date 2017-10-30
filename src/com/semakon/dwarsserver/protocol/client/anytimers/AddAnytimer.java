package com.semakon.dwarsserver.protocol.client.anytimers;

import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class AddAnytimer extends ClientMessage {

    private Anytimer anytimer;
    private int uid;

    public AddAnytimer(ClientMessageType type) {
        super(type);
    }

    public Anytimer getAnytimer() {
        return anytimer;
    }

    public void setAnytimer(Anytimer anytimer) {
        this.anytimer = anytimer;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}

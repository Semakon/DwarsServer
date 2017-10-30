package com.semakon.dwarsserver.protocol.server.anytimers;

import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class EditAnytimerFail extends ServerMessage {

    private Anytimer anytimer;

    public EditAnytimerFail(ServerMessageType type) {
        super(type);
    }

    public Anytimer getAnytimer() {
        return anytimer;
    }

    public void setAnytimer(Anytimer anytimer) {
        this.anytimer = anytimer;
    }

}

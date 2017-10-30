package com.semakon.dwarsserver.protocol.server.anytimers;

import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class ResponseAnytimers extends ServerMessage {

    private int uid;
    private Anytimer[] anytimers;

    public ResponseAnytimers(ServerMessageType type) {
        super(type);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Anytimer[] getAnytimers() {
        return anytimers;
    }

    public void setAnytimers(Anytimer[] anytimers) {
        this.anytimers = anytimers;
    }

}

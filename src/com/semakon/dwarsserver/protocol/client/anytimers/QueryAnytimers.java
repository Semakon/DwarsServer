package com.semakon.dwarsserver.protocol.client.anytimers;

import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class QueryAnytimers extends ClientMessage {

    private int uid;

    public QueryAnytimers(ClientMessageType type) {
        super(type);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}

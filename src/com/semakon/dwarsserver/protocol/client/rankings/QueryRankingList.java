package com.semakon.dwarsserver.protocol.client.rankings;

import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    31-10-2017
 */
public class QueryRankingList extends ClientMessage {

    private int rlid;

    public QueryRankingList(ClientMessageType type) {
        super(type);
    }

    public int getRlid() {
        return rlid;
    }

    public void setRlid(int rlid) {
        this.rlid = rlid;
    }

}

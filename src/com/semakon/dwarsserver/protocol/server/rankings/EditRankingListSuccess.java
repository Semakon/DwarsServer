package com.semakon.dwarsserver.protocol.server.rankings;

import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    31-10-2017
 */
public class EditRankingListSuccess extends ServerMessage {

    private int rlid;

    public EditRankingListSuccess(ServerMessageType type) {
        super(type);
    }

    public int getRlid() {
        return rlid;
    }

    public void setRlid(int rlid) {
        this.rlid = rlid;
    }

}

package com.semakon.dwarsserver.protocol.client.rankings;

import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    31-10-2017
 */
public class QueryRankingLists extends ClientMessage {

    public QueryRankingLists(ClientMessageType type) {
        super(type);
    }

}

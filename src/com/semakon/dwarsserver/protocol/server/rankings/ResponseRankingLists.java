package com.semakon.dwarsserver.protocol.server.rankings;

import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    31-10-2017
 */
public class ResponseRankingLists extends ServerMessage {

    // TODO: change to rlid and name
    private RankingList[] rankingLists;

    public ResponseRankingLists(ServerMessageType type) {
        super(type);
    }

    public RankingList[] getRankingLists() {
        return rankingLists;
    }

    public void setRankingLists(RankingList[] rankingLists) {
        this.rankingLists = rankingLists;
    }

}

package com.semakon.dwarsserver.protocol.server.rankings;

import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    31-10-2017
 */
public class EditRankingListFail extends ServerMessage {

    private RankingList rankingList;
    private String reason;

    public EditRankingListFail(ServerMessageType type) {
        super(type);
    }

    public RankingList getRankingList() {
        return rankingList;
    }

    public void setRankingList(RankingList rankingList) {
        this.rankingList = rankingList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}

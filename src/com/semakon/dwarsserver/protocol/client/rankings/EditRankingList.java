package com.semakon.dwarsserver.protocol.client.rankings;

import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    31-10-2017
 */
public class EditRankingList extends ClientMessage {

    private RankingList rankingList;

    public EditRankingList(ClientMessageType type) {
        super(type);
    }

    public RankingList getRankingList() {
        return rankingList;
    }

    public void setRankingList(RankingList rankingList) {
        this.rankingList = rankingList;
    }

}

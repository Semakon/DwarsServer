package com.semakon.dwarsserver.protocol.server;

import com.google.gson.*;
import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.server.anytimers.EditAnytimerFail;
import com.semakon.dwarsserver.protocol.server.anytimers.EditAnytimerSuccess;
import com.semakon.dwarsserver.protocol.server.anytimers.ResponseAnytimers;
import com.semakon.dwarsserver.protocol.server.login.LoginFail;
import com.semakon.dwarsserver.protocol.server.login.LoginSuccess;
import com.semakon.dwarsserver.protocol.server.rankings.EditRankingListFail;
import com.semakon.dwarsserver.protocol.server.rankings.EditRankingListSuccess;
import com.semakon.dwarsserver.protocol.server.rankings.ResponseRankingList;
import com.semakon.dwarsserver.protocol.server.rankings.ResponseRankingLists;

import java.lang.reflect.Type;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class ServerMessageDeserializer implements JsonDeserializer<ServerMessage> {

    @Override
    public ServerMessage deserialize(final JsonElement json, final Type typeOfT,
                                     final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String jsonType = jsonObject.get("type").getAsString();
        final ServerMessageType type = ServerMessageType.valueOf(jsonType);

        ServerMessage msg;
        switch (type) {
            case LOGIN_ATTEMPT_SUCCES:
                msg = new LoginSuccess(type);
                ((LoginSuccess)msg).setUser(context.deserialize(jsonObject.get("user"), User.class));
                return msg;
            case LOGIN_ATTEMPT_FAIL:
                msg = new LoginFail(type);
                ((LoginFail)msg).setReason(jsonObject.get("reason").getAsString());
                return msg;
            case RESPONSE_ANYTIMERS:
                msg = new ResponseAnytimers(type);
                ((ResponseAnytimers)msg).setUid(jsonObject.get("uid").getAsInt());
                ((ResponseAnytimers)msg).setAnytimers(
                        context.deserialize(jsonObject.get("anytimers"), Anytimer[].class));
                return msg;
            case EDIT_ANYTIMER_SUCCESS:
                msg = new EditAnytimerSuccess(type);
                ((EditAnytimerSuccess)msg).setAid(jsonObject.get("aid").getAsInt());
                return msg;
            case EDIT_ANYTIMER_FAIL:
                msg = new EditAnytimerFail(type);
                ((EditAnytimerFail)msg).setAnytimer(
                        context.deserialize(jsonObject.get("anytimer"), Anytimer.class));
                ((EditAnytimerFail)msg).setReason(jsonObject.get("reason").getAsString());
                return msg;
            case RESPONSE_RANKING_LISTS:
                msg = new ResponseRankingLists(type);
                ((ResponseRankingLists)msg).setRankingLists(
                        context.deserialize(jsonObject.get("rankingLists"), RankingList[].class));
                return msg;
            case RESPONSE_RANKING_LIST:
                msg = new ResponseRankingList(type);
                ((ResponseRankingList)msg).setRankingList(
                        context.deserialize(jsonObject.get("rankingList"), RankingList.class));
                return msg;
            case EDIT_RANKING_LIST_SUCCESS:
                msg = new EditRankingListSuccess(type);
                ((EditRankingListSuccess)msg).setRlid(jsonObject.get("rlid").getAsInt());
                return msg;
            case EDIT_RANKING_LIST_FAIL:
                msg = new EditRankingListFail(type);
                ((EditRankingListFail)msg).setRankingList(
                        context.deserialize(jsonObject.get("rankingList"), RankingList.class));
                ((EditRankingListFail)msg).setReason(jsonObject.get("reason").getAsString());
                return msg;
            default:
                throw new JsonParseException(
                        "Message type \"" + type + "\" is undefined in " + getClass().getSimpleName());
        }
    }

}

package com.semakon.dwarsserver.protocol.client;

import com.google.gson.*;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.client.anytimers.AddAnytimer;
import com.semakon.dwarsserver.protocol.client.anytimers.EditAnytimer;
import com.semakon.dwarsserver.protocol.client.anytimers.QueryAnytimers;
import com.semakon.dwarsserver.protocol.client.anytimers.RemoveAnytimer;
import com.semakon.dwarsserver.protocol.client.rankings.*;

import java.lang.reflect.Type;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class ClientMessageDeserializer implements JsonDeserializer<ClientMessage> {

    @Override
    public ClientMessage deserialize(final JsonElement json, final Type typeOfT,
                                     final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String jsonType = jsonObject.get("type").getAsString();
        final ClientMessageType type = ClientMessageType.valueOf(jsonType);

        ClientMessage msg;
        switch (type) {
            case LOGIN_ATTEMPT:
                msg = new LoginAttempt(type);
                ((LoginAttempt)msg).setUsername(jsonObject.get("username").getAsString());
                ((LoginAttempt)msg).setPassword(jsonObject.get("password").getAsString());
                return msg;
            case QUERY_ANYTIMERS:
                msg = new QueryAnytimers(type);
                ((QueryAnytimers)msg).setUid(jsonObject.get("uid").getAsInt());
                return msg;
            case ADD_ANYTIMER:
                msg = new AddAnytimer(type);
                ((AddAnytimer)msg).setAnytimer(
                        context.deserialize(jsonObject.get("anytimer"), Anytimer.class));
                ((AddAnytimer)msg).setUid(jsonObject.get("uid").getAsInt());
                return msg;
            case REMOVE_ANYTIMER:
                msg = new RemoveAnytimer(type);
                ((RemoveAnytimer)msg).setAid(jsonObject.get("aid").getAsInt());
                return msg;
            case EDIT_ANYTIMER:
                msg = new EditAnytimer(type);
                ((EditAnytimer)msg).setAnytimer(
                        context.deserialize(jsonObject.get("anytimer"), Anytimer.class));
                return msg;
            case QUERY_RANKING_LISTS:
                msg = new QueryRankingLists(type);
                return msg;
            case QUERY_RANKING_LIST:
                msg = new QueryRankingList(type);
                ((QueryRankingList)msg).setRlid(jsonObject.get("rlid").getAsInt());
                return msg;
            case ADD_RANKING_LIST:
                msg = new AddRankingList(type);
                ((AddRankingList)msg).setRankingList(
                        context.deserialize(jsonObject.get("rankingList"), RankingList.class));
                return msg;
            case REMOVE_RANKING_LIST:
                msg = new RemoveRankingList(type);
                ((RemoveRankingList)msg).setRlid(jsonObject.get("rlid").getAsInt());
                return msg;
            case EDIT_RANKING_LIST:
                msg = new EditRankingList(type);
                ((EditRankingList)msg).setRankingList(
                        context.deserialize(jsonObject.get("rankingList"), RankingList.class));
                return msg;
            default:
                throw new JsonParseException(
                        "Message type \"" + type + "\" is undefined in " + getClass().getSimpleName());
        }
    }

}

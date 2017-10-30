package com.semakon.dwarsserver.protocol.server;

import com.google.gson.*;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.protocol.server.anytimers.EditAnytimerFail;
import com.semakon.dwarsserver.protocol.server.anytimers.EditAnytimerSuccess;
import com.semakon.dwarsserver.protocol.server.anytimers.ResponseAnytimers;

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
            default:
                throw new JsonParseException(
                        "Message type \"" + type + "\" is undefined in " + getClass().getSimpleName());
        }
    }

}

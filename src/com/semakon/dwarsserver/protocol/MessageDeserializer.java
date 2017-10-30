package com.semakon.dwarsserver.protocol;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 *
 *
 * Author:  M.P. de Vries
 * Date:    28-10-2017
 */
public class MessageDeserializer implements JsonDeserializer<Message> {

    /**
     * Deserializes a Message object and returns the appropriate subclass according to the MessageType.
     * @return the appropriate subclass of Message according to the MessageType.
     * @throws JsonParseException when the type is undefined.
     */
    @Override
    public Message deserialize(final JsonElement json, final Type typeOfT,
                               final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String jsonType = jsonObject.get("type").getAsString();
        final MessageType type = MessageType.getFromString(jsonType);
        if (type == null) {
            throw new JsonParseException("Message type \"" + jsonType + "\" is undefined");
        }

        Message msg;
        switch (type) {
            case PERSONAL:
                final String sender = jsonObject.get("sender").getAsString();
                final String recipient = jsonObject.get("recipient").getAsString();
                final String message = jsonObject.get("message").getAsString();
                msg = new PersonalMessage(type, sender, recipient, message);
                break;
            case BROADCAST:
                final String sender2 = jsonObject.get("sender").getAsString();
                final String message2 = jsonObject.get("message").getAsString();
                msg = new BroadcastMessage(type, sender2, message2);
                break;
            case ERROR:
                final String error = jsonObject.get("error").getAsString();
                final String message3 = jsonObject.get("message").getAsString();
                msg = new ErrorMessage(type, error, message3);
                break;
            case INFO:
                final String message4 = jsonObject.get("message").getAsString();
                final JsonArray jsonArgsArray = jsonObject.get("args").getAsJsonArray();
                final String[] args = new String[jsonArgsArray.size()];
                for (int i = 0; i < args.length; i++) {
                    final JsonElement jsonArg = jsonArgsArray.get(i);
                    args[i] = jsonArg.getAsString();
                }
                msg = new InfoMessage(type, message4, args);
                break;
            default:
                throw new JsonParseException("Message type \"" + jsonType + "\" is undefined");
        }

        return msg;
    }

}

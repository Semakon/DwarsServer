package com.semakon.dwarsserver.protocol.server;

import com.semakon.dwarsserver.client.TestClient;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;

/**
 * Author:  M.P. de Vries
 * Date:    1-11-2017
 */
public class ServerMessageHandler {

    private TestClient client;

    public ServerMessageHandler(TestClient client) {
        this.client = client;
    }

    public void handle(ServerMessage msg) {
        ServerMessageType type =  msg.getType();
        if (type == null) {
            System.err.println("Message type is undefined");
            return;
        }
        switch (type) {
            case LOGIN_ATTEMPT_SUCCES:
                break;
            case LOGIN_ATTEMPT_FAIL:
                break;
            case RESPONSE_ANYTIMERS:
                break;
            case EDIT_ANYTIMER_SUCCESS:
                break;
            case EDIT_ANYTIMER_FAIL:
                break;
            case RESPONSE_RANKING_LISTS:
                break;
            case RESPONSE_RANKING_LIST:
                break;
            case EDIT_RANKING_LIST_SUCCESS:
                break;
            case EDIT_RANKING_LIST_FAIL:
                break;
            default:
                System.err.println(
                        "Message type \"" + type + "\" is undefined in " + getClass().getSimpleName());
        }
    }

}

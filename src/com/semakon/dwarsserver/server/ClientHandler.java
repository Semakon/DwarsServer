package com.semakon.dwarsserver.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.semakon.dwarsserver.exceptions.InvalidUsernameException;
import com.semakon.dwarsserver.exceptions.SendingMessageException;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageDeserializer;
import com.semakon.dwarsserver.protocol.client.LoginAttempt;
import com.semakon.dwarsserver.protocol.client.anytimers.AddAnytimer;
import com.semakon.dwarsserver.protocol.client.anytimers.EditAnytimer;
import com.semakon.dwarsserver.protocol.client.anytimers.QueryAnytimers;
import com.semakon.dwarsserver.protocol.client.anytimers.RemoveAnytimer;
import com.semakon.dwarsserver.protocol.client.rankings.*;
import com.semakon.dwarsserver.protocol.old.*;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class ClientHandler extends Thread {

    // TODO: assign uid
    private int uid;
    private String username;

    private DwarsServer server;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Gson gson;

    public ClientHandler(DwarsServer server, Socket socket) {
        this.server = server;
        this.socket = socket;

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ClientMessage.class, new ClientMessageDeserializer());
        gson = builder.create();
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            while ((input = in.readLine()) != null) {
                ClientMessage msg = gson.fromJson(input, ClientMessage.class);

                // debug
                System.out.println("Received: " + msg);

                if (msg.getType() == null) {
                    System.err.println("Message type is undefined");
                    continue;
                }
                switch(msg.getType()) {
                    case LOGIN_ATTEMPT:
                        break;
                    case QUERY_ANYTIMERS:
                        break;
                    case ADD_ANYTIMER:
                        break;
                    case REMOVE_ANYTIMER:
                        break;
                    case EDIT_ANYTIMER:
                        break;
                    case QUERY_RANKING_LISTS:
                        break;
                    case QUERY_RANKING_LIST:
                        break;
                    case ADD_RANKING_LIST:
                        break;
                    case REMOVE_RANKING_LIST:
                        break;
                    case EDIT_RANKING_LIST:
                        break;
                    default:
                        System.err.println("Message type \"" + msg.getType()
                                + "\" is undefined in " + getClass().getSimpleName());
                }
            }
        } catch (IOException e) {
            System.err.println((username != null ? username : "Client") + " disconnected");
            server.clientDisconnected(this);
        }
    }



    /**
     * Encodes a Message object into Json and sends it to the output.
     * @param message the Message object sent.
     */
    public void sendMessage(ServerMessage message) {
        out.println(gson.toJson(message));
    }

    public void disconnect() throws IOException {
        out.close();
        in.close();
        socket.close();
        System.out.println("Disconnected from client");
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ClientHandler && this.uid == ((ClientHandler) obj).getUid();
    }

    public int getUid() {
        return this.uid;
    }

    public String getUsername() {
        return this.username;
    }

}

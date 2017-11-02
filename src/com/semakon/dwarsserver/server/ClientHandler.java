package com.semakon.dwarsserver.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.exceptions.InsufficientPermissionsException;
import com.semakon.dwarsserver.exceptions.SendingMessageException;
import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.protocol.client.ClientMessageHandler;
import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageDeserializer;
import com.semakon.dwarsserver.protocol.server.ServerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class ClientHandler extends Thread {

    // User details
    // TODO: assign user
    private User user;

    // Permission level
    private int permissionLevel = 0;

    // Dwars server objects
    private DwarsServer server;
    private ClientMessageHandler handler;

    // Connection tools
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson;

    public ClientHandler(DwarsServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.handler = new ClientMessageHandler(this);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ClientMessage.class, new ClientMessageDeserializer());
        gson = builder.create();
    }

    /**
     * Handles input from client.
     */
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            while ((input = in.readLine()) != null) {
                ClientMessage msg = gson.fromJson(input, ClientMessage.class);

                // debug
                System.out.println("Received: " + msg);

                try {
                    handler.handle(msg);
                } catch (InsufficientPermissionsException e) {
                    System.err.println(e.getMessage());
                    // TODO: create "InsufficientPermissions" ServerMessage and send to client
                }
            }
        } catch (IOException e) {
            System.err.println((user != null ? getUsername() : "Client") + " disconnected");
            server.clientDisconnected(this);
        }
    }

    /**
     * Encodes a ServerMessage object into Json and sends it to the output.
     * @param msg the ServerMessage object sent.
     */
    public void sendMessage(ServerMessage msg) {
        out.println(gson.toJson(msg));
    }

    /**
     * Disconnects the server from the client and closes all appropriate channels.
     */
    public void disconnect() throws IOException {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected from client");
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ClientHandler && getUid() == ((ClientHandler) obj).getUid();
    }

    public int getUid() {
        return user.getUid();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUser(User user) {
        this.user = user;
    }
}

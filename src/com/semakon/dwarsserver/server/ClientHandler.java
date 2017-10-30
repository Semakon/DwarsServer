package com.semakon.dwarsserver.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.exceptions.InvalidUsernameException;
import com.semakon.dwarsserver.exceptions.SendingMessageException;
import com.semakon.dwarsserver.protocol.*;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class ClientHandler extends Thread {

    private int uuid;
    private String username;

    private Server server;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private Gson gson;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public ClientHandler(int uuid, Server server, Socket socket) {
        this.uuid = uuid;
        this.server = server;
        this.socket = socket;

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageDeserializer());
        gson = builder.create();
    }

    public void run() {
        try {

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            while ((input = in.readLine()) != null) {
                Message msg = gson.fromJson(input, Message.class);
                System.out.println("Received: " + msg);
                if (msg.getType() == null) {
                    System.err.println("Message type is undefined");
                    continue;
                }
                switch(msg.getType()) {
                    case PERSONAL:
                        handlePersonalMessage((PersonalMessage)msg);
                        break;
                    case BROADCAST:
                        handleBroadcastMessage((BroadcastMessage) msg);
                        break;
                    case ERROR:
                        handleErrorMessage((ErrorMessage)msg);
                        break;
                    case INFO:
                        handleInfoMessage((InfoMessage)msg);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println((username != null ? username : "Client") + " disconnected");
            server.clientDisconnected(this);
        }
    }

    private void handlePersonalMessage(PersonalMessage msg) {
        try {
            server.sendToClient(msg.getRecipient(), msg);
        } catch (SendingMessageException e) {
            System.err.println(e.getMessage());
            sendMessage(new ErrorMessage(MessageType.ERROR, Info.INVALID_COMMAND, e.getMessage()));
        }
    }

    private void handleBroadcastMessage(BroadcastMessage msg) {
        try {
            server.sendToAll(msg);
        } catch (SendingMessageException e) {
            // Should not occur
            System.err.println(e.getMessage());
            sendMessage(new ErrorMessage(MessageType.ERROR, Info.INVALID_COMMAND, e.getMessage()));
        }
    }

    private void handleErrorMessage(ErrorMessage msg) {
        System.err.println("Received error from " + username + ": " + msg);
    }

    private void handleInfoMessage(InfoMessage msg) {
        switch (msg.getMessage()) {
            case Info.USERNAME:
                String[] args = msg.getArgs();
                if (args != null && args.length >= 1) {
                    String username = args[0];
                    try {
                        server.validateUsername(username);
                        // Username accepted
                        this.username = username;
                        sendMessage(new InfoMessage(
                                MessageType.INFO, Info.VALID_USERNAME, new String[]{username}));

                        // Send broadcast to all clients
                        server.sendToAll(new InfoMessage(
                                MessageType.INFO, Info.CLIENT_CONNECTED, new String[]{username}));
                    } catch (InvalidUsernameException e) {
                        // Username not accepted
                        System.err.println(socket.getInetAddress()
                                + " chose an invalid username: \"" + username + "\"");
                        sendMessage(new ErrorMessage(
                                MessageType.ERROR, Info.INVALID_USERNAME, e.getMessage()));
                    } catch (SendingMessageException e) {
                        System.err.println(e.getMessage());
                    }

                } else {
                    sendMessage(new ErrorMessage(
                            MessageType.ERROR, Info.INVALID_COMMAND, "Not enough arguments"));
                }
                break;
            default:
                System.err.println("Unknown info message");
                sendMessage(new ErrorMessage(
                        MessageType.ERROR, Info.INVALID_COMMAND, "Unknown info message"));
        }
    }

    /**
     * Encodes a Message object into Json and sends it to the output.
     * @param message the Message object sent.
     */
    public void sendMessage(Message message) {
        out.println(gson.toJson(message));
    }

    public void disconnect() throws IOException {
        // TODO: broadcast client disconnected
        out.close();
        in.close();
        socket.close();
        System.out.println("Disconnected from client");
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ClientHandler && this.uuid == ((ClientHandler) obj).getUuid();
    }

    public int getUuid() {
        return this.uuid;
    }

    public String getUsername() {
        return this.username;
    }

}

package com.semakon.dwarsserver.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.Utils;
import com.semakon.dwarsserver.protocol.old.*;
import com.semakon.dwarsserver.view.ClientTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class Client {

    private String host;
    private int port;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected;

    private String username;

    private Gson gson;

    private ClientTextView view;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        connected = false;

        // Initialize view
        view = new ClientTextView(this);
        view.start();
    }

    public void connect() {
        // Create gson builder, add custom deserializer for Message class and create gson from it
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class, new MessageDeserializer());
        gson = gsonBuilder.create();

        // Try to connect to server
        while (!connected) {
            try {
                socket = new Socket(host, port);
                view.displayMessage("Connected to " + host + " on port " + port);
                connected = true;
            } catch (IOException e) {
                System.err.println("Could not connect to " + host + " on port " + port);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        run();
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
                    view.displayError("Message type is undefined");
                    continue;
                }
                switch(msg.getType()) {
                    case PERSONAL:
                        if (username == null) continue;
                        handlePersonalMessage((PersonalMessage)msg);
                        break;
                    case BROADCAST:
                        if (username == null) continue;
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

            disconnect();
        } catch (IOException e) {
            view.displayError("Server disconnected");
            connected = false;
            connect();
        }
    }

    private void handlePersonalMessage(PersonalMessage msg) {
        view.displayPersonalMessage(msg);
    }

    private void handleBroadcastMessage(BroadcastMessage msg) {
        view.displayBroadcastMessage(msg);
    }

    private void handleErrorMessage(ErrorMessage msg) {
        String message = msg.getMessage();
        switch (msg.getError()) {
            case Info.INVALID_COMMAND:
                view.displayError("Invalid command - " + message);
                break;
            case Info.INVALID_USERNAME:
                view.displayError(message);
                break;
            default:
                view.displayError("Unknown error message received: \"" + msg.getError() + "\"");
        }
    }

    private void handleInfoMessage(InfoMessage msg) {
        String[] args = msg.getArgs();
        if (args == null || args.length < 1) {
            view.displayError("Found info message without arguments");
            return;
        }
        switch (msg.getMessage()) {
            case Info.VALID_USERNAME:
                username = args[0];
                view.displayMessage("Username \"" + username + "\" accepted");
                break;
            case Info.CLIENT_CONNECTED:
                String connectedClient = args[0];
                view.displayError(connectedClient + " connected to the server");
                break;
            case Info.CLIENT_DISCONNECTED:
                String disconnectedClient = args[0];
                view.displayError(disconnectedClient + " disconnected from the server");
                break;
            case Info.SERVER_SHUTTING_DOWN:
                String reason = args[0];
                view.displayMessage("Server is shutting down: " + reason);
                break;
            default:
                view.displayError("Unknown Info message received: \"" + msg.getMessage() + "\"");
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
        in.close();
        out.close();
        socket.close();
        gson = null;
        view.displayMessage("Disconnected from server");
    }

    public String getUsername() {
        return username;
    }

    public static void main(String[] args) {
        Client test = new Client(Utils.HOST, Utils.PORT);
        test.connect();
    }

}

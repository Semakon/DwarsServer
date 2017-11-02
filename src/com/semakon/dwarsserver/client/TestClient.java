package com.semakon.dwarsserver.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.Utils;
import com.semakon.dwarsserver.protocol.server.ServerMessageHandler;
import com.semakon.dwarsserver.protocol.client.ClientMessage;
import com.semakon.dwarsserver.protocol.client.ClientMessageDeserializer;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.protocol.server.ServerMessageDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author:  M.P. de Vries
 * Date:    1-11-2017
 */
public class TestClient {

    private String host;
    private int port;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected;

    private Gson gson;

    private ServerMessageHandler handler;

    public TestClient(String host, int port) {
        this.host = host;
        this.port = port;
        connected = false;
        handler = new ServerMessageHandler(this);
    }

    public void connect() {
        // Create gson and register adapters
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ClientMessage.class, new ClientMessageDeserializer());
        builder.registerTypeAdapter(ServerMessage.class, new ServerMessageDeserializer());
        gson = builder.create();

        // Try to connect to server
        while (!connected) {
            try {
                socket = new Socket(host, port);
                System.out.println("Connected to " + host + " on port " + port);
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

    private void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            while ((input = in.readLine()) != null) {
                ServerMessage msg = gson.fromJson(input, ServerMessage.class);

                // debug
                System.out.println("Received: " + msg);

                handler.handle(msg);
            }
        } catch (IOException e) {
            System.err.println("Server disconnected");
            connected = false;
            connect();
        }
    }

    public void sendServerMessage(ServerMessage msg) {
        out.println(gson.toJson(msg));
    }

    public void disconnect() throws IOException {
        connected = false;
        out.close();
        in.close();
        socket.close();
        gson = null;
        System.out.println("Disconnected from server");
    }

    public static void main(String[] args) {
        new TestClient(Utils.HOST, Utils.PORT).connect();
    }

}

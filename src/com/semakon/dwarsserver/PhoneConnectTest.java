package com.semakon.dwarsserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.protocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class PhoneConnectTest {

    private ServerSocket serverSocket;
    private List<PhoneHandler> clients;

    private InputHandler inputHandler;

    public PhoneConnectTest() {
        clients = new ArrayList<>();
        inputHandler = new InputHandler(this);
        inputHandler.start();
    }

    private void listen(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listening on port " + port);
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(1);
        }

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PhoneHandler client = new PhoneHandler(this, clientSocket);
                client.start();

                clients.add(client);
                System.out.println("New client connected on port " + port);
            }
        } catch (IOException e) {
            System.err.println("Failed to connect client to socket on port " + port);
        }
    }

    public void sendToAll(TestMessage msg) {
        for (PhoneHandler ph : clients) {
            sendMessage(ph, msg);
        }
    }

    public void sendMessage(PhoneHandler client, TestMessage msg) {
        client.sendMessage(msg);
    }

    private class PhoneHandler extends Thread {

        private PhoneConnectTest server;

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        private Gson gson;

        public PhoneHandler(PhoneConnectTest server, Socket socket) {
            this.server = server;
            this.socket = socket;

            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String input;
                while ((input = in.readLine()) != null) {
                    TestMessage msg = gson.fromJson(input, TestMessage.class);
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(TestMessage msg) {
            out.println(gson.toJson(msg));
        }

    }

    private class InputHandler extends Thread {

        private PhoneConnectTest server;
        private boolean stop;

        public InputHandler(PhoneConnectTest server) {
            this.server = server;
            stop = false;
        }

        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (!stop) {
                if (scanner.hasNextLine()) {
                    String[] split = scanner.nextLine().split(" ");
                    if (split.length >= 2) {
                        String sender = split[0];
                        StringBuilder builder = new StringBuilder(split[1]);
                        for (int i = 2; i < split.length; i++) {
                            builder.append(" ").append(split[i]);
                        }
                        String message = builder.toString();
                        TestMessage tm = new TestMessage();
                        tm.setSender(sender);
                        tm.setMessage(message);

                        server.sendToAll(tm);
                    } else {
                        System.err.println("[sender] [message]");
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        PhoneConnectTest test = new PhoneConnectTest();
        test.listen(4321);
    }

}

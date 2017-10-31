package com.semakon.dwarsserver.view;

import com.semakon.dwarsserver.client.Client;
import com.semakon.dwarsserver.protocol.old.*;

import java.util.Scanner;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class ClientTextView extends Thread implements ClientView {

    private Client client;
    private boolean stop;

    public ClientTextView(Client client) {
        this.client = client;
        stop = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!stop) {
            if (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" ");
                if (split.length >= 1) {
                    switch (split[0]) {
                        case CommandOld.MESSAGE:
                            handleMessage(split);
                            break;
                        case CommandOld.BROADCAST:
                            handleBroadcast(split);
                            break;
                        case CommandOld.USERNAME:
                            handleUsername(split);
                            break;
                        case CommandOld.HELP:
                            handleHelp(split);
                            break;
                        default:
                            displayError("Unknown command");
                    }
                }
            }
        }
    }

    private void handleHelp(String[] args) {
        if (args.length == 1) {
            displayMessage(CommandOld.help());
        } else if (args.length == 2){
            switch (args[1]) {
                case CommandOld.HELP:
                    displayMessage("Usage: " + CommandOld.HELP_USAGE);
                    break;
                case CommandOld.MESSAGE:
                    displayMessage("Usage: " + CommandOld.MESSAGE_USAGE);
                    break;
                case CommandOld.BROADCAST:
                    displayMessage("Usage: " + CommandOld.BROADCAST_USAGE);
                    break;
                case CommandOld.USERNAME:
                    displayMessage("Usage: " + CommandOld.USERNAME_USAGE);
                    break;
                default:
                    displayError("Not a command, use \"" + CommandOld.HELP + "\"");
            }
        } else {
            displayError("Usage: " + CommandOld.HELP_USAGE);
        }
    }

    private void handleUsername(String[] args) {
        // Check for enough arguments
        if (args.length < 2) {
            displayError("Usage: " + CommandOld.USERNAME_USAGE);
            return;
        }
        // Create new info message
        InfoMessage msg = new InfoMessage(MessageType.INFO);
        msg.setMessage(Info.USERNAME);
        msg.setArgs(new String[]{args[1]});
        // Send info message to server
        client.sendMessage(msg);
    }

    private void handleBroadcast(String[] args) {
        // Check for enough arguments
        if (args.length < 2) {
            displayError("Usage: " + CommandOld.BROADCAST_USAGE);
            return;
        }
        // Create new personal message
        BroadcastMessage msg = new BroadcastMessage(MessageType.BROADCAST);
        msg.setSender(client.getUsername());
        // Build arguments into single String
        StringBuilder builder = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; i++) {
            builder.append(" ").append(args[i]);
        }
        msg.setMessage(builder.toString());
        // Send personal message to server
        client.sendMessage(msg);
    }

    private void handleMessage(String[] args) {
        // Check for enough arguments
        if (args.length < 3) {
            displayError("Usage: " + CommandOld.MESSAGE_USAGE);
            return;
        }
        // Create new personal message
        PersonalMessage msg = new PersonalMessage(MessageType.PERSONAL);
        msg.setSender(client.getUsername());
        msg.setRecipient(args[1]);
        // Build arguments into single String
        StringBuilder builder = new StringBuilder(args[2]);
        for (int i = 3; i < args.length; i++) {
            builder.append(" ").append(args[i]);
        }
        msg.setMessage(builder.toString());
        // Send personal message to server
        client.sendMessage(msg);
    }

    @Override
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void displayPersonalMessage(PersonalMessage msg) {
        String message = String.format("%s: %s", msg.getSender(), msg.getMessage());
        displayMessage(message);
    }

    @Override
    public void displayBroadcastMessage(BroadcastMessage msg) {
        String message = String.format("BROADCAST by %s: %s", msg.getSender(), msg.getMessage());
        displayMessage(message);
    }

    @Override
    public void displayError(String msg) {
        System.err.println(msg);
    }

    @Override
    public void close() {
        stop = true;
    }

}

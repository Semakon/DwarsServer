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
                        case Command.MESSAGE:
                            handleMessage(split);
                            break;
                        case Command.BROADCAST:
                            handleBroadcast(split);
                            break;
                        case Command.USERNAME:
                            handleUsername(split);
                            break;
                        case Command.HELP:
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
            displayMessage(Command.help());
        } else if (args.length == 2){
            switch (args[1]) {
                case Command.HELP:
                    displayMessage("Usage: " + Command.HELP_USAGE);
                    break;
                case Command.MESSAGE:
                    displayMessage("Usage: " + Command.MESSAGE_USAGE);
                    break;
                case Command.BROADCAST:
                    displayMessage("Usage: " + Command.BROADCAST_USAGE);
                    break;
                case Command.USERNAME:
                    displayMessage("Usage: " + Command.USERNAME_USAGE);
                    break;
                default:
                    displayError("Not a command, use \"" + Command.HELP + "\"");
            }
        } else {
            displayError("Usage: " + Command.HELP_USAGE);
        }
    }

    private void handleUsername(String[] args) {
        // Check for enough arguments
        if (args.length < 2) {
            displayError("Usage: " + Command.USERNAME_USAGE);
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
            displayError("Usage: " + Command.BROADCAST_USAGE);
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
            displayError("Usage: " + Command.MESSAGE_USAGE);
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

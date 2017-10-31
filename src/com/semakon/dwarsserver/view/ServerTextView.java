package com.semakon.dwarsserver.view;

import java.util.Scanner;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public class ServerTextView extends Thread implements ServerView {

    private boolean stop;

    public ServerTextView() {
        stop = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!stop) {
            if (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" ");
                if (split.length >= 1) {
                    switch (split[0]) {
                        // TODO: handle cases
                        case Command.HELP:
                        case Command.SHUTDOWN:
                        case Command.KICK:
                        case Command.MESSAGE:
                        case Command.BROADCAST:
                            break;
                        default:
                            displayError("Unknown command - Type "
                                    + Command.HELP + " for a list of commands");
                    }
                }
            }
        }
    }

    @Override
    public void displayMessage(String msg) {
        System.out.println(msg);
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

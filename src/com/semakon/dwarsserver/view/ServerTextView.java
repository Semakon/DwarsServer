package com.semakon.dwarsserver.view;

import com.semakon.dwarsserver.server.Server;

import java.util.Scanner;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public class ServerTextView extends Thread implements ServerView {

    private Server server;
    private boolean stop;

    public ServerTextView(Server server) {
        this.server = server;
        stop = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (!stop) {
                if (scanner.hasNextLine()) {
                    String[] split = scanner.nextLine().split(" ");
                    // TODO: handle input
                }
            }
            // Sleep to avoid spam
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

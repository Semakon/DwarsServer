package com.semakon.dwarsserver.server;

import com.semakon.dwarsserver.Utils;
import com.semakon.dwarsserver.exceptions.InvalidUsernameException;
import com.semakon.dwarsserver.exceptions.SendingMessageException;
import com.semakon.dwarsserver.protocol.old.Info;
import com.semakon.dwarsserver.protocol.old.InfoMessage;
import com.semakon.dwarsserver.protocol.old.Message;
import com.semakon.dwarsserver.protocol.old.MessageType;
import com.semakon.dwarsserver.protocol.server.ServerMessage;
import com.semakon.dwarsserver.view.ServerTextView;
import com.semakon.dwarsserver.view.ServerView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class DwarsServer {

    private int port;
    private ServerSocket serverSocket;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private List<ClientHandler> clientHandlers;

    private ServerView view;

    public DwarsServer(int port) {
        this.port = port;
        clientHandlers = new ArrayList<>();
        view = new ServerTextView();
    }

    public void run() {
        // Start listening on server socket
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listening on port " + port);
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(1);
        }

        // Attach new clients and create a new ClientHandlerOld for every new client
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler ch = new ClientHandler(this, clientSocket);
                ch.start();

                lock.writeLock().lock();
                try {
                    clientHandlers.add(ch);
                } finally {
                    lock.writeLock().unlock();
                }
                System.out.println("New client (" + clientSocket.getInetAddress() + ") connected on port " + port);
            }
        } catch (IOException e) {
            System.err.println("Failed to attach client to socket on port " + port);
        }
        shutdown(0);
    }

    /**
     * Sends a ServerMessage to all clients.
     * @param msg The ServerMessage sent.
     * @throws SendingMessageException when an error in sending the ServerMessage occurs.
     */
    public void sendToAll(ServerMessage msg) throws SendingMessageException {
        lock.readLock().lock();
        try {
            ListIterator<ClientHandler> iter = clientHandlers.listIterator();
            while (iter.hasNext()) {
                ClientHandler ch = iter.next();
                if (ch == null) {
                    view.displayError("Empty ClientHandlerOld found, removing...");
                    iter.remove();
                } else if (ch.getUsername() == null) {
                    view.displayError("Client has not yet chosen a username");
                } else {
                    sendToClient(ch, msg);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Sends a ServerMessage object to a specific client.
     * @param recipient the recipient of the message.
     * @param msg the ServerMessage sent to the client.
     * @throws SendingMessageException when an error in sending the ServerMessage occurs.
     */
    public void sendToClient(ClientHandler recipient, ServerMessage msg)
            throws SendingMessageException {
        if (msg.getType() == null) {
            view.displayError("Error sending message - Message type is not defined");
            throw new SendingMessageException("Message type is not defined");
        }
        lock.readLock().lock();
        try {
            if (recipient == null || !clientHandlers.contains(recipient)) {
                view.displayError("Error sending message - Recipient does not exist");
                throw new SendingMessageException("Recipient does not exist");
            }
            view.displayMessage("Sending \"" + msg + "\" to " + recipient.getUsername());
            recipient.sendMessage(msg);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Sends a ServerMessage object to a specific client.
     * @param recipient the recipient of the ServerMessage.
     * @param msg the ServerMessage sent to the client.
     * @throws SendingMessageException when an error in sending the ServerMessage occurs.
     */
    public void sendToClient(String recipient, ServerMessage msg) throws SendingMessageException {
        sendToClient(getFromString(recipient), msg);
    }

    /**
     * Validates the given username. A username is valid when:
     * - it is between Utils.MIN_CHAR (inclusive) and Utils.MAX_CHAR (inclusive) characters long,
     * - it only contains letters and numbers [a-z A-Z 0-9]
     * - it is not yet in use.
     * @param username the username to be validated.
     * @throws InvalidUsernameException when the username is not valid.
     */
    public void validateUsername(String username) throws InvalidUsernameException {
        // Utils.MIN_CHAR <= length <= Utils.MAX_CHAR
        if (username.length() < Utils.MIN_CHAR || username.length() > Utils.MAX_CHAR) {
            throw new InvalidUsernameException("Username should be between "
                    + Utils.MIN_CHAR + " and " + Utils.MAX_CHAR + " characters long (inclusive)");
        }
        // Only letters and numbers
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]*$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            throw new InvalidUsernameException("Username can only contain letters and numbers");
        }
        // Username not in use
        if (getFromString(username) != null) {
            throw new InvalidUsernameException("Username already in use");
        }
    }

    /**
     * Gets the ClientHandlerOld whose username corresponds to the given String.
     * @param username the String to check against the ClientHandlerOld's usernames.
     * @return the ClientHandlerOld whose username corresponds to the given String, <code>null</code> if
     * there is no such ClientHandlerOld.
     */
    private ClientHandler getFromString(String username) {
        lock.readLock().lock();
        try {
            for (ClientHandler ch : clientHandlers) {
                if (username.equals(ch.getUsername())) return ch;
            }
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }

    /**
     * Removes a ClientHandlerOld from the list of ClientHandlers.
     * @param handler the ClientHandlerOld to be removed.
     */
    public void clientDisconnected(ClientHandler handler) {
        String username = handler.getUsername();
        lock.writeLock().lock();
        try {
            clientHandlers.remove(handler);
        } finally {
            lock.writeLock().unlock();
        }
        // TODO: broadcast client disconnected
//        try {
//            sendToAll(new InfoMessage(MessageType.INFO, Info.CLIENT_DISCONNECTED, new String[]{username}));
//        } catch (SendingMessageException e) {
//            System.err.println(e.getMessage());
//        }
    }

    /**
     * Disconnects all ClientHandlers and closes the server socket. Terminates the program with a status.
     * @param status the status the program terminates with.
     */
    public void shutdown(int status) {
        try {
            for (ClientHandler ch : clientHandlers) {
                ch.disconnect();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(status);
    }

    public static void main(String[] args) {
        DwarsServer test = new DwarsServer(Utils.PORT);
        test.run();
    }

}

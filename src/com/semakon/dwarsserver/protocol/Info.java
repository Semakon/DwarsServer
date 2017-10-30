package com.semakon.dwarsserver.protocol;

/**
 * Author:  M.P. de Vries
 * Date:    28-10-2017
 */
public class Info {

    // Info server
    /** Used to confirm validating the client's chosen username. */
    public static final String VALID_USERNAME = "ValidUsername";
    /** Used to notify clients of a new connected client. */
    public static final String CLIENT_CONNECTED = "ClientConnected";
    /** Used to notify clients a client has disconnected. */
    public static final String CLIENT_DISCONNECTED = "ClientDisconnected";
    /** Used to notify clients the server is shutting down. */
    public static final String SERVER_SHUTTING_DOWN = "ServerShuttingDown";

    // Info client
    /** Used to submit a chosen username to the server. */
    public static final String USERNAME = "Username";

    // Errors
    /** Used to notify a client that its username is invalid. */
    public static final String INVALID_USERNAME = "InvalidUsername";
    /** Used to notify a client that its issued command is invalid. */
    public static final String INVALID_COMMAND = "InvalidCommand";

}

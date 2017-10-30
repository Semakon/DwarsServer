package com.semakon.dwarsserver.protocol.server;

/**
 * Enumeration of different types of messages that can be sent from the server to a client.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public enum ServerMessageType {

    // Login attempt
    /** Sent when a client successfully logged in. */
    LOGIN_ATTEMPT_SUCCES,
    /** Sent when a client fails to log in. */
    LOGIN_ATTEMPT_FAIL,

    // Anytimer operations
    /** All queried anytimers from a user. */
    RESPONSE_ANYTIMERS,
    /** Anytimers edited successfully (edit refers to adding and removing as well). */
    EDIT_ANYTIMER_SUCCESS,
    /** Editing anytimers failed (edit refers to adding and removing as well). */
    EDIT_ANYTIMER_FAIL

}

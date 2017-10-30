package com.semakon.dwarsserver.protocol.client;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public enum ClientMessageType {

    // Login
    /** Sent when a client attempts to login. */
    LOGIN_ATTEMPT,

    // Anytimer operations
    /** Request an array of anytimers */
    QUERY_ANYTIMERS,
    /** Add an anytimer */
    ADD_ANYTIMER,
    /** Remove an anytimer */
    REMOVE_ANYTIMER,
    /** Edit an anytimer */
    EDIT_ANYTIMER

}

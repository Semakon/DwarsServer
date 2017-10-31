package com.semakon.dwarsserver.protocol.server;

/**
 * Enumeration of different types of messages that can be sent from the server to a client.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public enum ServerMessageType {

    // Login attempt
    /** Sent when a client successfully logged in */
    LOGIN_ATTEMPT_SUCCES,
    /** Sent when a client fails to log in */
    LOGIN_ATTEMPT_FAIL,

    // Anytimer operations
    /** All anytimers from a user */
    RESPONSE_ANYTIMERS,
    /** Anytimers edited successfully (edit refers to adding and removing as well) */
    EDIT_ANYTIMER_SUCCESS,
    /** Editing anytimers failed (edit refers to adding and removing as well) */
    EDIT_ANYTIMER_FAIL,

    // Ranking operations
    /** All ranking lists */
    RESPONSE_RANKING_LISTS,
    /** The ranking list queried by its rlid */
    RESPONSE_RANKING_LIST,
    /** Ranking lists edited successfully (edit refers to adding and removing as well) */
    EDIT_RANKING_LIST_SUCCESS,
    /** Editing ranking lists failed (edit refers to adding and removing as well) */
    EDIT_RANKING_LIST_FAIL

}

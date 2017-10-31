package com.semakon.dwarsserver.protocol.client;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public enum ClientMessageType {

    // Login
    /** Sent when a client attempts to login */
    LOGIN_ATTEMPT,

    // Anytimer operations
    /** Request a list of anytimers of a user */
    QUERY_ANYTIMERS,
    /** Add an anytimer */
    ADD_ANYTIMER,
    /** Remove an anytimer */
    REMOVE_ANYTIMER,
    /** Update an anytimer */
    EDIT_ANYTIMER,

    // Ranking operations
    /** Request a list of all ranking lists */
    QUERY_RANKING_LISTS,
    /** Request a ranking list by its rlid */
    QUERY_RANKING_LIST,
    /** Add a new ranking list */
    ADD_RANKING_LIST,
    /** Remove an existing ranking list */
    REMOVE_RANKING_LIST,
    /** Update a ranking list */
    EDIT_RANKING_LIST

}

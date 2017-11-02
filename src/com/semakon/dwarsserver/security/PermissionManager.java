package com.semakon.dwarsserver.security;

import com.semakon.dwarsserver.server.ClientHandler;

/**
 * Author:  M.P. de Vries
 * Date:    1-11-2017
 */
public class PermissionManager {

    /**
     * Determines whether a client has sufficient permissions to perform a certain action.
     * @param ch The ClientHandler whose permissions are checked.
     * @param permissionLevel The required permission level of the action.
     * @return <code>true</code> if the client has sufficient permissions, <code>false</code> if not.
     */
    public static boolean hasPermission(ClientHandler ch, int permissionLevel) {
        return ch.getPermissionLevel() < permissionLevel;
    }

}

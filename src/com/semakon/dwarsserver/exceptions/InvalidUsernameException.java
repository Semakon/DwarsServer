package com.semakon.dwarsserver.exceptions;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public class InvalidUsernameException extends DwarsServerException {

    public InvalidUsernameException(String reason) {
        super("Invalid username - " + reason);
    }

}

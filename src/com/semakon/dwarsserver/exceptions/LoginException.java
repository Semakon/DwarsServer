package com.semakon.dwarsserver.exceptions;

/**
 * Author:  M.P. de Vries
 * Date:    1-11-2017
 */
public class LoginException extends DwarsServerException {

    public LoginException() {
        this("Incorrect username or password");
    }

    public LoginException(String reason) {
        super("Fatal error while attempting to log in: " + reason);
    }

}

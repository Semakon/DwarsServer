package com.semakon.dwarsserver.exceptions;

/**
 * Author:  M.P. de Vries
 * Date:    2-11-2017
 */
public class InsufficientPermissionsException extends DwarsServerException {

    public InsufficientPermissionsException() {
        this("User has insufficient permissions to perform that action");
    }

    public InsufficientPermissionsException(String msg) {
        super(msg);
    }

}

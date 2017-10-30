package com.semakon.dwarsserver.exceptions;

/**
 * Author:  M.P. de Vries
 * Date:    28-10-2017
 */
public class SendingMessageException extends DwarsServerException {

    public SendingMessageException(String reason) {
        super("Error sending message - " + reason);
    }

}

package com.semakon.dwarsserver.view;

import com.semakon.dwarsserver.protocol.old.BroadcastMessage;
import com.semakon.dwarsserver.protocol.old.PersonalMessage;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public interface ClientView {

    /**
     * Displays a message on the view.
     * @param msg message sent.
     */
    void displayMessage(String msg);

    /**
     * Displays a personal message on the view sent by a user.
     * @param msg message sent.
     */
    void displayPersonalMessage(PersonalMessage msg);

    /**
     * Displays a broadcast message on the view sent by a user.
     * @param msg message sent.
     */
    void displayBroadcastMessage(BroadcastMessage msg);

    /**
     * Displays an error message on the view.
     * @param msg error message.
     */
    void displayError(String msg);

    /**
     * Closes the view.
     */
    void close();

}

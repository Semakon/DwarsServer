package com.semakon.dwarsserver.view;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public interface ServerView {

    /**
     * Displays a message on the view.
     * @param msg message sent.
     */
    void displayMessage(String msg);

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

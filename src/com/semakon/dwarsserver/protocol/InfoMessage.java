package com.semakon.dwarsserver.protocol;

import java.util.Arrays;

/**
 * Author:  M.P. de Vries
 * Date:    28-10-2017
 */
public class InfoMessage extends Message {

    private String message;
    private String[] args;

    public InfoMessage(MessageType type) {
        super(type);
    }

    public InfoMessage(MessageType type, String message, String[] args) {
        super(type);
        this.message = message;
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return getType() + " {" + message + ", " + Arrays.toString(args) + "}" ;
    }
}

package com.semakon.dwarsserver.protocol.old;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public enum MessageType {

    PERSONAL, BROADCAST, ERROR, INFO;

    public static MessageType getFromString(String type) {
        for (MessageType mt : MessageType.values()) {
            if (mt.toString().equalsIgnoreCase(type)) return mt;
        }
        return null;
    }

}

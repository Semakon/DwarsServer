package com.semakon.dwarsserver.model;

/**
 * Class that represents a user that is connected to the server.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class User {

    /** The User's user identifier */
    private int uid;
    /** The User's unique username */
    private String username;
    /** The User's display name */
    private String displayName;

    public User(int uid, String username) {
        this(uid, username, username);
    }

    public User(int uid, String username, String displayName) {
        this.uid = uid;
        this.username = username;
        this.displayName = displayName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof User && ((User) o).getUid() == uid;
    }

    @Override
    public String toString() {
        return displayName;
    }

}

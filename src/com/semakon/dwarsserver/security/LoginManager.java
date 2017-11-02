package com.semakon.dwarsserver.security;

import com.semakon.dwarsserver.database.SqlDatabase;
import com.semakon.dwarsserver.exceptions.LoginException;
import com.semakon.dwarsserver.model.User;

/**
 * Author:  M.P. de Vries
 * Date:    1-11-2017
 */
public class LoginManager {

    /**
     * Checks the database for a user with <code>username</code> and <code>password</code> and return
     * it if it exists. The password is saved as a hash.
     * @param username the User's username.
     * @param password the User's password.
     * @return The User corresponding with the username.
     * @throws LoginException if the username and/or password is incorrect
     */
    public static User getUser(String username, String password) throws LoginException {
        // Find User with username in database, otherwise throw LoginException
        User user = null;
        for (User u : SqlDatabase.getInstance().getUsers()) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }
        if (user == null) throw new LoginException();

        // Check whether password corresponds to password in database
        String databasePassword = SqlDatabase.getInstance().getPasswordByUid(user.getUid());
        if (password.equals(databasePassword)) {
            return user;
        } else {
            throw new LoginException();
        }
    }

}

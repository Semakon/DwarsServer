package com.semakon.dwarsserver.database;

/**
 * Author:  M.P. de Vries
 * Date:    2-11-2017
 */
public class DwarsAppContract {

    /** Private constructor to prevent instantiation. */
    private DwarsAppContract() {}

    public static class SqlUsers {
        public static final String TABLE_NAME = "[Users]";
        public static final String COLUMN_NAME_UID = "Uid";
        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_DISPLAY_NAME = "DisplayName";
    }

    public static class SqlPasswords {
        public static final String TABLE_NAME = "Passwords";
        public static final String COLUMN_NAME_UID = "Uid";
        public static final String COLUMN_NAME_PASSWORD = "Password";
    }

}

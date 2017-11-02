package com.semakon.dwarsserver.database;

import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.model.ranking.RankingList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class SqlDatabase implements Database {

    private static SqlDatabase instance;

    private static final String DB_IP = "localhost";
    private static final String DB_NAME = "DwarsApp";
    private static final String CONNECTION_URL = "jdbc:sqlserver://"
            + DB_IP + ";databaseName=" + DB_NAME + ";integratedSecurity=true";

    private static Connection con;

    private SqlDatabase() {
        // Private to prevent instantiation
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(CONNECTION_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static SqlDatabase getInstance() {
        if (instance == null) {
            instance = new SqlDatabase();
        }
        return instance;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", DwarsAppContract.SqlUsers.TABLE_NAME);
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Create parameters for new user
                int uid = rs.getInt(DwarsAppContract.SqlUsers.COLUMN_NAME_UID);
                String username = rs.getString(DwarsAppContract.SqlUsers.COLUMN_NAME_USERNAME);
                String displayName = rs.getString(DwarsAppContract.SqlUsers.COLUMN_NAME_DISPLAY_NAME);

                // Create new user with parameters and add it to the list
                User user = new User(uid, username, displayName);
                list.add(user);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User getUserByUid(int uid) {
        return null;
    }

    @Override
    public String getPasswordByUid(int uid) {
        return null;
    }

    @Override
    public List<Anytimer> getAnytimers() {
        return null;
    }

    @Override
    public List<RankingList> getRankingLists() {
        return null;
    }

    @Override
    public RankingList getRankingListByRlid(int rlid) {
        return null;
    }
}

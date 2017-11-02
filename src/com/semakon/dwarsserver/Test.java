package com.semakon.dwarsserver;

import com.semakon.dwarsserver.database.SqlDatabase;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(SqlDatabase.getInstance().getUsers());
    }

}

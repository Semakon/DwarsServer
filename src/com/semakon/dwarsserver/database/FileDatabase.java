package com.semakon.dwarsserver.database;

import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.model.ranking.RankingList;

import java.util.List;

/**
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class FileDatabase implements Database {
    @Override
    public List<User> getUsers() {
        return null;
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
    public RankingList[] getRankingLists() {
        return null;
    }

    @Override
    public RankingList getRankingListByRlid(int rlid) {
        return null;
    }
}

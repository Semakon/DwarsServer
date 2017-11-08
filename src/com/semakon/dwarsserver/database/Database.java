package com.semakon.dwarsserver.database;

import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.model.anytimers.Anytimer;
import com.semakon.dwarsserver.model.ranking.RankingList;

import java.util.List;

/**
 * Interface for database classes to ensure flexibility.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public interface Database {

    List<User> getUsers();

    User getUserByUid(int uid);

    String getPasswordByUid(int uid);

    List<Anytimer> getAnytimers();

    RankingList[] getRankingLists();

    RankingList getRankingListByRlid(int rlid);
}

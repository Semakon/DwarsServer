package com.semakon.dwarsserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.model.ranking.Ranking;
import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.model.User;

/**
 * Author:  M.P. de Vries
 * Date:    11-10-2017
 */
public class Test {

    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        RankingList list = new RankingList(1, "vo");
        list.addRanking(new Ranking(new User(1, "martijn"), 10));
        list.addRanking(new Ranking(new User(2, "coen"), -10));
        list.addRanking(new Ranking(new User(3, "jelly"), 1));
        String out = gson.toJson(list);
        System.out.println(out);

        RankingList in = gson.fromJson(out, RankingList.class);
        System.out.println(in);
    }

}

package com.semakon.dwarsserver.model.ranking;

import com.semakon.dwarsserver.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps track of the rankings on a certain subject.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class RankingList {

    private String name;
    private List<Ranking> rankings;

    public RankingList(String name) {
        this.name = name;
        rankings = new ArrayList<>();
    }

    /**
     * Adds points to a specific user.
     * @param user the user whose points are increased.
     * @param points the amount of points the user gets.
     */
    public void addPoints(User user, int points) {
        for (Ranking r : rankings) {
            if (r.getUser() == user) {
                r.addPoints(points);
                return;
            }
        }
    }

    /**
     * Detracts points from a specific user.
     * @param user the user whose points are decreased.
     * @param points the amount of points the user loses.
     */
    public void detractPoints(User user, int points) {
        for (Ranking r : rankings) {
            if (r.getUser() == user) {
                r.detractPoints(points);
                return;
            }
        }
    }

    /**
     * Returns the user with the most points.
     * @return the user with the most points.
     */
    public User getTop() {
        Ranking top = null;
        for (Ranking r : rankings) {
            if (top == null || r.getPoints().get() > top.getPoints().get()) top = r;
        }
        if (top == null) return null;
        return top.getUser();
    }

    /**
     * Returns the user with the least amount of points.
     * @return the user with the least amount of points.
     */
    public User getBottom() {
        Ranking bottom = null;
        for (Ranking r : rankings) {
            if (bottom == null || r.getPoints().get() < bottom.getPoints().get()) bottom = r;
        }
        if (bottom == null) return null;
        return bottom.getUser();
    }

    /**
     * Adds a new Ranking to the RankingList.
     * @param ranking the new Ranking.
     */
    public void addRanking(Ranking ranking) {
        rankings.add(ranking);
    }

    /**
     * Removes a Ranking from the RankingList.
     * @param ranking the Ranking to be removed.
     */
    public void removeRanking(Ranking ranking) {
        rankings.remove(ranking);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    @Override
    public String toString() {
        return "Ranking of " + name + " - {" + rankings + "}";
    }

}

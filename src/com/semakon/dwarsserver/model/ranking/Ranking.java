package com.semakon.dwarsserver.model.ranking;

import com.semakon.dwarsserver.model.User;

/**
 * A ranking on a certain subject of a certain user.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class Ranking {

    private User user;
    private MutableInt points;

    public Ranking(User user, int points) {
        this.user = user;
        this.points = new MutableInt(points);
    }

    /**
     * Detracts points from the User in this Ranking.
     * @param points the amount of points detracted.
     */
    public void detractPoints(int points) {
        this.points.sub(points);
    }

    /**
     * Adds points to the User in this Ranking.
     * @param points the amount of points added.
     */
    public void addPoints(int points) {
        this.points.add(points);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MutableInt getPoints() {
        return points;
    }

    public void setPoints(MutableInt points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "(" + user + ": " + points + ")";
    }
}

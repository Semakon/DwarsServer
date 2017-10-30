package com.semakon.dwarsserver.model.anytimers;

/**
 * This class represents an anytimer that has a type that decides whether it needs to be drunk or dealt.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class Anytimer {

    private int aid;
    private AnytimerType type;
    private boolean used;

    public Anytimer(int aid, AnytimerType type) {
        this.aid = aid;
        this.type = type;
        this.used = false;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public AnytimerType getType() {
        return type;
    }

    public void setType(AnytimerType type) {
        this.type = type;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "(" + aid + ": " + type + ", " + used + ")";
    }

}

package com.semakon.dwarsserver.model.ranking;

/**
 * Simple class that allows the mutation of an integer without reassigning it. Useful for i.e. Maps.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public class MutableInt {

    /** The integer represented by this class. */
    private int integer;

    /** Instantiates the class with the integer set to 0. */
    public MutableInt() {
        this(0);
    }

    /**
     * Instantiates the class with the integer set to a given amount.
     * @param integer the initial value of the integer.
     */
    public MutableInt(int integer) {
        this.integer = integer;
    }

    /** Increments the integer by 1. */
    public void inc() {
        integer++;
    }

    /** Decrements the integer by 1. */
    public void dec() {
        integer--;
    }

    /**
     * Adds a given amount to the integer.
     * @param amount the added amount.
     */
    public void add(int amount) {
        integer += amount;
    }

    /**
     * Subtracts a given amount from the integer.
     * @param amount the subtracted amount.
     */
    public void sub(int amount) {
        integer -= amount;
    }

    /**
     * Multiplies the integer by a given multiple.
     * @param multiple the multiple.
     */
    public void mul(int multiple) {
        integer = integer * multiple;
    }

    /**
     * Divides the integer by a given division.
     * @param division the division.
     */
    public void div(int division) {
        integer = integer / division;
    }

    /**
     * Returns the integer.
     * @return the integer.
     */
    public int get() {
        return integer;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return String.valueOf(integer);
    }
}

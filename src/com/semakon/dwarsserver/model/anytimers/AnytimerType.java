package com.semakon.dwarsserver.model.anytimers;

/**
 * The type of an anytimer.
 *
 * Author:  M.P. de Vries
 * Date:    30-10-2017
 */
public enum AnytimerType {

    TO_DRINK, TO_DEAL;

    @Override
    public String toString() {
        switch (this) {
            case TO_DRINK:
                return "To Drink";
            case TO_DEAL:
                return "To Deal";
            default:
                return "Error";
        }
    }
}

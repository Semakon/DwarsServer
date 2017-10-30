package com.semakon.dwarsserver.view;

/**
 * Author:  M.P. de Vries
 * Date:    29-10-2017
 */
public class Command {

    public static final String MESSAGE = "msg";
    public static final String MESSAGE_USAGE = MESSAGE + " <recipient> <message>";

    public static final String BROADCAST = "broadcast";
    public static final String BROADCAST_USAGE = BROADCAST + " <message>";

    public static final String USERNAME = "username";
    public static final String USERNAME_USAGE = USERNAME + " <username>";

    public static final String HELP = "help";
    public static final String HELP_USAGE = HELP + " [command]";

    public static String help() {
        return "Commands and their usages:\n" +
                "<> is mandatory, [] is optional\n" +
                "- " + HELP_USAGE + "\n" +
                "- " + USERNAME_USAGE + "\n" +
                "- " + MESSAGE_USAGE + "\n" +
                "- " + BROADCAST_USAGE + "\n";
    }

}

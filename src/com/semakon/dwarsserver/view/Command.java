package com.semakon.dwarsserver.view;

/**
 * Author:  M.P. de Vries
 * Date:    29-10-2017
 */
public class Command {

    public static final String SHUTDOWN = "shutdown";
    public static final String SHUTDOWN_USAGE = SHUTDOWN + " [reason]";

    public static final String KICK = "disconnect";
    public static final String KICK_USAGE = KICK + " <user> [reason]";

    public static final String MESSAGE = "msg";
    public static final String MESSAGE_USAGE = MESSAGE + " <recipient> <message>";

    public static final String BROADCAST = "broadcast";
    public static final String BROADCAST_USAGE = BROADCAST + " <message>";

    public static final String HELP = "help";
    public static final String HELP_USAGE = HELP + " [command]";

    public static String help() {
        return "Commands and their usages:\n" +
                "<> is mandatory, [] is optional\n" +
                "- " + HELP_USAGE + "\n" +
                "- " + SHUTDOWN_USAGE + "\n" +
                "- " + KICK_USAGE + "\n" +
                "- " + MESSAGE_USAGE + "\n" +
                "- " + BROADCAST_USAGE + "\n";
    }

}

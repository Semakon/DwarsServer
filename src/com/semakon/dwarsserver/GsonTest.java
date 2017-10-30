package com.semakon.dwarsserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semakon.dwarsserver.protocol.*;

/**
 * Author:  M.P. de Vries
 * Date:    27-10-2017
 */
public class GsonTest {

    public static void main(String[] args) {
        // Create gson builder, add the custom deserializer and create gson from it
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class, new MessageDeserializer());
        Gson gson = gsonBuilder.create();

        // Create broadcast message
        Message msg = new BroadcastMessage(
                MessageType.BROADCAST, "Martijn", "Hello World!");

        // Create json message and print it
        String out = gson.toJson(msg);
        System.out.println("out: " + out);

        // Deserialize json message into Message and check type
        Message in = gson.fromJson(out, Message.class);
        switch (in.getType()) {
            case PERSONAL:
                System.out.println(((PersonalMessage)in).getMessage());
                break;
            case BROADCAST:
                System.out.println(in);
                break;
            case ERROR:
                System.out.println(((ErrorMessage)in).getMessage());
                break;
            case INFO:
                System.out.println(in);
                break;
        }
    }

}

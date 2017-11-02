package com.semakon.dwarsserver.protocol.client;

import com.semakon.dwarsserver.exceptions.InsufficientPermissionsException;
import com.semakon.dwarsserver.exceptions.LoginException;
import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.protocol.client.login.LoginAttempt;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;
import com.semakon.dwarsserver.protocol.server.login.LoginFail;
import com.semakon.dwarsserver.protocol.server.login.LoginSuccess;
import com.semakon.dwarsserver.security.LoginManager;
import com.semakon.dwarsserver.security.Perm;
import com.semakon.dwarsserver.security.PermissionManager;
import com.semakon.dwarsserver.server.ClientHandler;

/**
 * Author:  M.P. de Vries
 * Date:    1-11-2017
 */
public class ClientMessageHandler {

    private ClientHandler clientHandler;

    public ClientMessageHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void handle(ClientMessage msg) throws InsufficientPermissionsException {
        ClientMessageType type = msg.getType();
        if (type == null) {
            System.err.println("Message type is undefined");
            return;
        }
        switch (type) {
            case LOGIN_ATTEMPT:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                handleLoginAttempt(msg);
                break;
            case QUERY_ANYTIMERS:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case ADD_ANYTIMER:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case REMOVE_ANYTIMER:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case EDIT_ANYTIMER:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case QUERY_RANKING_LISTS:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case QUERY_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case ADD_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case REMOVE_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            case EDIT_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                break;
            default:
                System.err.println(
                        "Message type \"" + type + "\" is undefined in " + getClass().getSimpleName());
        }
    }

    private void handleLoginAttempt(ClientMessage msg) {
        // Cast msg to appropriate ServerMessageType
        LoginAttempt loginMsg = (LoginAttempt)msg;
        String username = loginMsg.getUsername();
        String password = loginMsg.getPassword();
        try {
            // Evaluate login attempt
            User user = LoginManager.getUser(username, password);

            // Login successful, send message to client
            LoginSuccess response = new LoginSuccess(ServerMessageType.LOGIN_ATTEMPT_SUCCES);
            response.setUser(user);
            clientHandler.sendMessage(response);

            // Set user as the client handler's user
            clientHandler.setUser(user);

            // Set permission level to USER
            clientHandler.setPermissionLevel(Perm.USER);
        } catch (LoginException e) {
            // Login attempt failed
            System.err.println(e.getMessage());

            // Send message to client
            LoginFail response = new LoginFail(ServerMessageType.LOGIN_ATTEMPT_FAIL);
            response.setReason(e.getMessage());
            clientHandler.sendMessage(response);
        }
    }

}

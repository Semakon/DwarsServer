package com.semakon.dwarsserver.protocol.client;

import com.semakon.dwarsserver.database.SqlDatabase;
import com.semakon.dwarsserver.exceptions.InsufficientPermissionsException;
import com.semakon.dwarsserver.exceptions.LoginException;
import com.semakon.dwarsserver.model.User;
import com.semakon.dwarsserver.model.ranking.RankingList;
import com.semakon.dwarsserver.protocol.client.anytimers.AddAnytimer;
import com.semakon.dwarsserver.protocol.client.anytimers.EditAnytimer;
import com.semakon.dwarsserver.protocol.client.anytimers.QueryAnytimers;
import com.semakon.dwarsserver.protocol.client.anytimers.RemoveAnytimer;
import com.semakon.dwarsserver.protocol.client.login.LoginAttempt;
import com.semakon.dwarsserver.protocol.client.rankings.*;
import com.semakon.dwarsserver.protocol.server.ServerMessageType;
import com.semakon.dwarsserver.protocol.server.login.LoginFail;
import com.semakon.dwarsserver.protocol.server.login.LoginSuccess;
import com.semakon.dwarsserver.protocol.server.rankings.ResponseRankingLists;
import com.semakon.dwarsserver.security.LoginManager;
import com.semakon.dwarsserver.security.Perm;
import com.semakon.dwarsserver.security.PermissionManager;
import com.semakon.dwarsserver.server.ClientHandler;

import java.util.List;

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
        // TODO; handle cast exception by returning error message to client
        switch (type) {
            case LOGIN_ATTEMPT:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                handleLoginAttempt((LoginAttempt) msg);
                break;
            case QUERY_ANYTIMERS:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                handleQueryAnytimers((QueryAnytimers) msg);
                break;
            case ADD_ANYTIMER:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                handleAddAnytimer((AddAnytimer) msg);
                break;
            case REMOVE_ANYTIMER:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                handleRemoveAnytimer((RemoveAnytimer) msg);
                break;
            case EDIT_ANYTIMER:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                handleEditAnytimer((EditAnytimer) msg);
                break;
            case QUERY_RANKING_LISTS:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                handleQueryRankingLists((QueryRankingLists) msg);
                break;
            case QUERY_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.GUEST)) {
                    throw new InsufficientPermissionsException();
                }
                handleQueryRankingList((QueryRankingList) msg);
                break;
            case ADD_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                handleAddRankingList((AddRankingList) msg);
                break;
            case REMOVE_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                handleRemoveRankingList((RemoveRankingList) msg);
                break;
            case EDIT_RANKING_LIST:
                // Check if user has sufficient permissions
                if (PermissionManager.hasPermission(clientHandler, Perm.USER)) {
                    throw new InsufficientPermissionsException();
                }
                handleEditRankingList((EditRankingList) msg);
                break;
            default:
                System.err.println(
                        "Message type \"" + type + "\" is undefined in " + getClass().getSimpleName());
        }
    }

    private void handleLoginAttempt(LoginAttempt msg) {
        // Get related information from message
        String username = msg.getUsername();
        String password = msg.getPassword();
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

    private void handleQueryAnytimers(QueryAnytimers msg) {
        // Get related information from msg
        int uid = msg.getUid();
        // TODO: give user list of anytimers
    }

    private void handleAddAnytimer(AddAnytimer msg) {

    }

    private void handleRemoveAnytimer(RemoveAnytimer msg) {

    }

    private void handleEditAnytimer(EditAnytimer msg) {

    }

    private void handleQueryRankingLists(QueryRankingLists msg) {
        // Get all RankingLists from database
        RankingList[] rankingLists = SqlDatabase.getInstance().getRankingLists();

        // Send rankingLists to client
        ResponseRankingLists response = new ResponseRankingLists(ServerMessageType.RESPONSE_RANKING_LISTS);
        response.setRankingLists(rankingLists);
        clientHandler.sendMessage(response);
        // TODO: handle query exception
    }

    private void handleQueryRankingList(QueryRankingList msg) {

    }

    private void handleAddRankingList(AddRankingList msg) {

    }

    private void handleRemoveRankingList(RemoveRankingList msg) {

    }

    private void handleEditRankingList(EditRankingList msg) {

    }

}

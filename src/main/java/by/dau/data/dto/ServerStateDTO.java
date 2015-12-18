package by.dau.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ServerStateDTO {

    private String serverState;
    private int usersCount;
    private int gameState;

    public ServerStateDTO(){}

    public ServerStateDTO(String serverState, int usersCount, int gameState) {
        this.serverState = serverState;
        this.usersCount = usersCount;
        this.gameState = gameState;
    }

    public String getServerState() {
        return serverState;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}

package by.dau.data.state;

public class CurrentState {

    long gameStateId;
    long matchId;
    long setId;
    long gameId;
    String productName;
    float price;

    public CurrentState(){}

    public CurrentState(long gameStateId, long matchId, long setId, long gameId, String productName, float price) {
        this.gameStateId = gameStateId;
        this.matchId = matchId;
        this.setId = setId;
        this.gameId = gameId;
        this.productName = productName;
        this.price = price;
    }

    public long getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(long gameStateId) {
        this.gameStateId = gameStateId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getSetId() {
        return setId;
    }

    public void setSetId(long setId) {
        this.setId = setId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

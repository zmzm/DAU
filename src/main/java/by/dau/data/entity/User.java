package by.dau.data.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DAU_USER")
public class User {

    public static final int START_MONEY = 100;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "gamestate_id", nullable = false)
    private GameState gameState;

    @Column(name = "name")
    private String name;

    @Column(name = "money", nullable = false)
    private float money;

    @JsonCreator
    public User() {
        //this.money = START_MONEY;
    }

    public User(GameState gameState, String name, float money) {
        this.gameState = gameState;
        this.name = name;
        this.money = money;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
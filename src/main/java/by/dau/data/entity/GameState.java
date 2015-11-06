package by.dau.data.entity;

import by.dau.data.entity.listener.GameStateListener;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "DAU_STATE")
@EntityListeners(GameStateListener.class)
public class GameState {

    public static int STATE_CREATED = 0;
    public static int STATE_STARTED = 1;
    public static int STATE_STOPPED = 2;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "state")
    private int state;

    @Column(name = "created")
    private Calendar created;

    @Column(name = "modified")
    private Calendar modified;

    @JsonCreator
    public GameState() {
//        setState(STATE_CREATED);
    }

    public GameState(long id, int state) {
        this.id = id;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }
}
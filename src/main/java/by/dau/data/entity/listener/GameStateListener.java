package by.dau.data.entity.listener;

import by.dau.data.entity.GameState;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

public class GameStateListener {
    @PreUpdate
    @PrePersist
    public void setlastModified(GameState gameState) {
        Calendar now = Calendar.getInstance();

        if (gameState.getCreated() == null)
            gameState.setCreated(now);

        gameState.setModified(now);
    }

}

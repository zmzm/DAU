package by.dau.data.repository;

import by.dau.data.entity.GameState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStateRepository extends JpaRepository<GameState, Long> {
}


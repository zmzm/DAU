package by.dau.data.repository;

import by.dau.data.entity.GameState;
import by.dau.data.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("select m from Match m where m.gameState = :gameState")
    Match findByGameState(@Param("gameState")GameState gameState);
}

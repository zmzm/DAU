package by.dau.data.repository;

import by.dau.data.entity.Game;
import by.dau.data.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("select g from Game g where g.sett = :sett order by g.id")
    List<Game> findBySet(@Param("sett")Set sett);
}

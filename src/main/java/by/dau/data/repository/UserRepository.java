package by.dau.data.repository;

import by.dau.data.entity.GameState;
import by.dau.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.gameState = :gameState")
    List<User> getAllUsersByState(@Param("gameState") GameState gameState);

    @Query("select u from User u where u.gameState = :gameState and u.name = :name")
    User getUsersByStateAndName(@Param("gameState") GameState gameState, @Param("name") String name);
}

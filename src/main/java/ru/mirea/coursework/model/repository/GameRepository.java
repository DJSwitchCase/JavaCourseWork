package ru.mirea.coursework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.coursework.model.entity.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    //@Query("SELECT c.. from Game c")
    //List<Game> findAllById(Integer id);

}

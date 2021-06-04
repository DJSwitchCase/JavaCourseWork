package ru.mirea.coursework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.coursework.model.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
}

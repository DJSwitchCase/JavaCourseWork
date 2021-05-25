package ru.mirea.coursework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.practice14_25.model.entity.Level;

//@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
}

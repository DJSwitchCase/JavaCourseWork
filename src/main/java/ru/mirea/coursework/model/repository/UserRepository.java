package ru.mirea.coursework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.coursework.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
}

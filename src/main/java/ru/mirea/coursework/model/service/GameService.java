package ru.mirea.coursework.model.service;

import ru.mirea.practice14_25.model.entity.Game;

import java.util.List;

public interface GameService {
        void create(Game game);
        List<Game> readAll();
        Game read(int id);
        boolean update(Game game, int id);
        boolean delete(int id);
    }

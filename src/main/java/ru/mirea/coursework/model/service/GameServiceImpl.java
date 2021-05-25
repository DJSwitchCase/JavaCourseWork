package ru.mirea.coursework.model.service;

import org.springframework.stereotype.Service;
import ru.mirea.practice14_25.model.entity.Game;
import ru.mirea.practice14_25.model.repository.GameRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    public final GameRepository gameRepository;
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    // Хранилище клиентов
    ///private static final Map<Integer, Level> LEVEL_REPOSITORY_MAP = new HashMap<>();
    // Переменная для генерации ID клиента
    ///private static final AtomicInteger LEVEL_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Game game) {
        ///    final int levelId = LEVEL_ID_HOLDER.incrementAndGet();
        ///    level.setId(levelId);
        ///    LEVEL_REPOSITORY_MAP.put(levelId, level);
        gameRepository.save(game);
    }

    @Override
    public List<Game> readAll() {
        ///return new ArrayList<>(LEVEL_REPOSITORY_MAP.values());
        return gameRepository.findAll();
    }

    @Override
    public Game read(int id) {
        ///return LEVEL_REPOSITORY_MAP.get(id);
        return gameRepository.getById(id);
    }

    @Override
    public boolean update(Game game, int id) {
        ///if (LEVEL_REPOSITORY_MAP.containsKey(id)){
        if (gameRepository.existsById(id)) {
            game.setId(id);
            ///LEVEL_REPOSITORY_MAP.put(id, level);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        ///return LEVEL_REPOSITORY_MAP.remove(id) != null;
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

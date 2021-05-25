package ru.mirea.coursework.model.service;

import org.springframework.stereotype.Service;
import ru.mirea.practice14_25.model.entity.Level;
import ru.mirea.practice14_25.model.repository.LevelRepository;

import java.util.List;

@Service
public
class LevelServiceImpl implements LevelService {

    public final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }
    // Хранилище клиентов
    ///private static final Map<Integer, Level> LEVEL_REPOSITORY_MAP = new HashMap<>();
    // Переменная для генерации ID клиента
    ///private static final AtomicInteger LEVEL_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Level level) {
        ///    final int levelId = LEVEL_ID_HOLDER.incrementAndGet();
        ///    level.setId(levelId);
        ///    LEVEL_REPOSITORY_MAP.put(levelId, level);
        levelRepository.save(level);
    }

    @Override
    public List<Level> readAll() {
        ///return new ArrayList<>(LEVEL_REPOSITORY_MAP.values());
        return levelRepository.findAll();
    }

    @Override
    public Level read(int id) {
        ///return LEVEL_REPOSITORY_MAP.get(id);
        return levelRepository.getById(id);
    }

    @Override
    public boolean update(Level level, int id) {
        ///if (LEVEL_REPOSITORY_MAP.containsKey(id)){
        if (levelRepository.existsById(id)) {
            level.setId(id);
            ///LEVEL_REPOSITORY_MAP.put(id, level);
            levelRepository.save(level);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        ///return LEVEL_REPOSITORY_MAP.remove(id) != null;
        if (levelRepository.existsById(id)) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

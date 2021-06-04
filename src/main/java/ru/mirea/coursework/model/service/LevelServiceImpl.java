package ru.mirea.coursework.model.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.coursework.model.entity.Level;
import ru.mirea.coursework.model.repository.LevelRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Slf4j
@Transactional
public
class LevelServiceImpl implements LevelService {

    public final LevelRepository levelRepository;
    @PersistenceContext
    EntityManager em;
    private final EmailService emailService;

    public List<Level> sort() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Level> levelCriteriaQuery = cb.createQuery(Level.class);
        Root<Level> root = levelCriteriaQuery.from(Level.class);
        String SortParam = "id";
        levelCriteriaQuery.select(root).orderBy(cb.asc(root.get(SortParam)));
        //Query<Game> query = (Query<Game>) em.createQuery(gameCriteriaQuery);
        log.info("Sorted levels by " + SortParam);
        return (List<Level>) em.createQuery(levelCriteriaQuery).getResultList();

    }
    public LevelServiceImpl(LevelRepository levelRepository, EmailService emailService) {
        this.levelRepository = levelRepository;
        this.emailService = emailService;
    }
    // Хранилище клиентов
    ///private static final Map<Integer, Level> LEVEL_REPOSITORY_MAP = new HashMap<>();
    // Переменная для генерации ID клиента
    ///private static final AtomicInteger LEVEL_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Level level) {
        log.info("Saved a new level " + level);
        ///    final int levelId = LEVEL_ID_HOLDER.incrementAndGet();
        ///    level.setId(levelId);
        ///    LEVEL_REPOSITORY_MAP.put(levelId, level);
        levelRepository.save(level);
        emailService.sendSimpleMessage("Saved a new level " + level);
    }

    @Override
    public List<Level> readAll() {
        ///return new ArrayList<>(LEVEL_REPOSITORY_MAP.values());
        log.info("Found all levels");
        return levelRepository.findAll();
    }

    @Override
    public Level read(int id) {
        log.info("Got the level by id " + id);
        ///return LEVEL_REPOSITORY_MAP.get(id);
        return levelRepository.getById(id);
    }

    @Override
    public boolean update(Level level, int id) {
        log.info("Updated the level " + level + " by id " + id);
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
        log.info("Deleted the level by id " + id);
        ///return LEVEL_REPOSITORY_MAP.remove(id) != null;
        if (levelRepository.existsById(id)) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

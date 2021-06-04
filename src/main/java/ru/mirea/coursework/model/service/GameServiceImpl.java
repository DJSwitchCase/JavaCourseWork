package ru.mirea.coursework.model.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.model.entity.Game;
import ru.mirea.coursework.model.repository.GameRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@Transactional
public class GameServiceImpl implements GameService {
    public final GameRepository gameRepository;
    private final EmailService emailService;
//    private SessionFactory sessionFactory;
//    private Session session;

    @PersistenceContext
    EntityManager em;

//    {
//        assert sessionFactory != null;
//        em = sessionFactory.createEntityManager();
//    }
//    @PostConstruct
//    public void init(){
//        this.session = sessionFactory.openSession();
//    }

//    CriteriaBuilder cb = this.em.getCriteriaBuilder();
//    CriteriaQuery<Game> gameCriteriaQuery = cb.createQuery(Game.class);
//    Root<Game> root = gameCriteriaQuery.from(Game.class);

    public ArrayList<Game> sort() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Game> gameCriteriaQuery = cb.createQuery(Game.class);
        Root<Game> root = gameCriteriaQuery.from(Game.class);
        String SortParam = "name";
        gameCriteriaQuery.select(root).orderBy(cb.asc(root.get(SortParam)));
        //Query<Game> query = (Query<Game>) em.createQuery(gameCriteriaQuery);
        log.info("Sorted games by " + SortParam);
        return (ArrayList<Game>) em.createQuery(gameCriteriaQuery).getResultList();

    }

    //CriteriaBuilder builder;

    public GameServiceImpl(GameRepository gameRepository, EmailService emailService) {
        this.gameRepository = gameRepository;
        this.emailService = emailService;
    }
    // Хранилище клиентов
    ///private static final Map<Integer, Level> LEVEL_REPOSITORY_MAP = new HashMap<>();
    // Переменная для генерации ID клиента
    ///private static final AtomicInteger LEVEL_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Game game) {
        log.info("Saved a new game " + game.name);
        ///    final int levelId = LEVEL_ID_HOLDER.incrementAndGet();
        ///    level.setId(levelId);
        ///    LEVEL_REPOSITORY_MAP.put(levelId, level);
        gameRepository.save(game);
        emailService.sendSimpleMessage("Saved a new game "+ game.name);
    }

    @Override
    public List<Game> readAll() {
        ///return new ArrayList<>(LEVEL_REPOSITORY_MAP.values());
        log.info("Found all games");
        return gameRepository.findAll();
    }

    @Override
    public Game read(int id) {
        ///return LEVEL_REPOSITORY_MAP.get(id);
        log.info("Got the game by id " + id);
        return gameRepository.getById(id);
    }

    @Override
    public boolean update(Game game, int id) {
        ///if (LEVEL_REPOSITORY_MAP.containsKey(id)){
        log.info("Updated the game " + game + " by id " + id);
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
        log.info("Deleted the game by id " + id);
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

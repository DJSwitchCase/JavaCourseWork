package ru.mirea.coursework.model.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.repository.ItemRepository;

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
public class ItemServiceImpl implements ItemService {
    public final ItemRepository itemsRepository;
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

    public ArrayList<Item> sort() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> itemsCriteriaQuery = cb.createQuery(Item.class);
        Root<Item> root = itemsCriteriaQuery.from(Item.class);
        String SortParam = "name";
        itemsCriteriaQuery.select(root).orderBy(cb.asc(root.get(SortParam)));
        //Query<Game> query = (Query<Game>) em.createQuery(gameCriteriaQuery);
        log.info("Sorted games by " + SortParam);
        return (ArrayList<Item>) em.createQuery(itemsCriteriaQuery).getResultList();

    }

    //CriteriaBuilder builder;

    public ItemServiceImpl(ItemRepository itemsRepository, EmailService emailService) {
        this.itemsRepository = itemsRepository;
        this.emailService = emailService;
    }

    @Override
    public void create(Item item) {
        log.info("Saved a new item " + item.name);
        ///    final int levelId = LEVEL_ID_HOLDER.incrementAndGet();
        ///    level.setId(levelId);
        ///    LEVEL_REPOSITORY_MAP.put(levelId, level);
        itemsRepository.save(item);
        emailService.sendSimpleMessage("Saved a new item "+ item.name);
    }

    @Override
    public List<Item> readAll() {
        ///return new ArrayList<>(LEVEL_REPOSITORY_MAP.values());
        log.info("Found all items");
        return itemsRepository.findAll();
    }

    public Item findById(int id) {
        return itemsRepository.findById(id);
    }

    public Item findByName(String name) {
        return itemsRepository.findByName(name);
    }

    @Override
    public Item read(int id) {
        ///return LEVEL_REPOSITORY_MAP.get(id);
        log.info("Got the item by id " + id);
        return itemsRepository.getById(id);
    }

    @Override
    public boolean update(Item item, int id) {
        ///if (LEVEL_REPOSITORY_MAP.containsKey(id)){
        log.info("Updated the item " + item + " by id " + id);
        if (!itemsRepository.existsById(id)) {
            item.setId(id);
            ///LEVEL_REPOSITORY_MAP.put(id, level);
            itemsRepository.save(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        ///return LEVEL_REPOSITORY_MAP.remove(id) != null;
        log.info("Deleted the item by id " + id);
        if (itemsRepository.existsById(id)) {
            itemsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

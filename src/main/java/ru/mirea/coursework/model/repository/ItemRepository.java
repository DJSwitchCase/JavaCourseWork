package ru.mirea.coursework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.model.entity.Item;

import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    //@Query("SELECT c.. from Game c")
    //List<Game> findAllById(Integer id);
    List<Item> findByPrice(String price);
    Item findByName(String name);
    Item findById(int id);

}

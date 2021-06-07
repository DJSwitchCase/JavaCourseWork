package ru.mirea.coursework.model.service;

import ru.mirea.coursework.model.entity.Item;

import java.util.List;

public interface ItemService {
        void create(Item item);
        List<Item> readAll();
        Item read(int id);
        boolean update(Item game, int id);
        boolean delete(int id);
        List<Item> sort();
}

package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.repository.ItemRepository;
import ru.mirea.coursework.model.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemsController {
    public final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemsController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

//    @GetMapping("/user")
//    public String user(Map<String, Object> model){
//    final List<Game> games = gameService.readAll();
//    if (games != null && !games.isEmpty())
//        model.put("message",new ResponseEntity<>(games, HttpStatus.OK));
//    else
//        model.put("message", new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    return "user";
//    }


    //    @PostMapping
//    public ResponseEntity<?> create(@RequestBody Game game) {
//        gameService.create(game);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
    @PostMapping
    public void create(@RequestParam String name, @RequestParam String price, Map<String, Object> model) {
        Item item = new Item(name, price);
        itemRepository.save(item);
        //Iterable<Game> games = gameRepository.findAll();
        //model.put("messages", games);
    }

    @GetMapping("/hi")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }

    @GetMapping
    public ResponseEntity<List<Item>> read() {
        final List<Item> items = itemService.readAll();
        return items != null && !items.isEmpty()
                ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //новое. Получение по id
    @GetMapping("/{id}")
    public ResponseEntity<Item> read(@PathVariable(name = "id") int id) {
        final Item item = itemService.read(id);

        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestParam Item item) {
        final boolean updated = itemService.update(item, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = itemService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sort() {
        final ArrayList<Item> sorted = (ArrayList<Item>) itemService.sort();
//        return sorted;
        return sorted != null
                ? new ResponseEntity<>(sorted, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

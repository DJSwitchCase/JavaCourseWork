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

/**
 * <b>Handles GET, POST, PUT, DELETE methods used to manipulate items.</b>
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    /**
     * An instance of ItemService.
     */
    public final ItemService itemService;
    private final ItemRepository itemRepository;

    /**
     * Autowired constructor.
     */
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

    /**
     * Creates a new item and saves it to itemRepository.
     * @param name item name.
     * @param price item price.
     * @param model model.
     * @see ru.mirea.coursework.model.repository.ItemRepository
     */
    @PostMapping
    public void create(@RequestParam String name, @RequestParam String price, Map<String, Object> model) {
        Item item = new Item(name, price);
        itemRepository.save(item);
        //Iterable<Game> games = gameRepository.findAll();
        //model.put("messages", games);
    }

    /**
     * Redirects user to the hello page.
     * @return "hello".
     */
    @GetMapping("/hi")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }

    /**
     * <p>Gets all the items.</p>
     *
     * Returns a ResponseEntity<> object of one of the following statuses:<ul>
     * <li>OK: items gotten successfully.</li>
     * <li>NOT_FOUND: items not found. </li></ul>
     * @return ResponseEntity<>(items, HttpStatus.OK) or ResponseEntity<>(HttpStatus.NOT_FOUND) object.
     */
    @GetMapping
    public ResponseEntity<List<Item>> read() {
        final List<Item> items = itemService.readAll();
        return items != null && !items.isEmpty()
                ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * <p>Gets an item with its id.</p>
     *
     * Returns a ResponseEntity<> object of one of the following statuses: <ul>
     * <li>OK: item found successfully.</li>
     * <li>NOT_FOUND: item not found.</li>
     * </ul>
     * @param id item id.
     * @return new ResponseEntity<>(item, HttpStatus.OK) or ResponseEntity<>(HttpStatus.NOT_FOUND) object.
     */
    //новое. Получение по id
    @GetMapping("/{id}")
    public ResponseEntity<Item> read(@PathVariable(name = "id") int id) {
        final Item item = itemService.read(id);

        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //update

    /**
     * <p>Updates an item.</p>
     *
     * Returns a ResponseEntity<> object of one of the following statuses: <ul>
     *      * <li>OK: item updated successfully.</li>
     *      * <li>NOT_MODIFIED: item not found.</li>
     *      * </ul>
     * @param id item id.
     * @param item item.
     * @return new ResponseEntity<>(HttpStatus.OK) or new ResponseEntity<>(HttpStatus.NOT_MODIFIED).
     */
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestParam Item item) {
        final boolean updated = itemService.update(item, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * <p>Deletes an item found with its id.</p>
     *
     * Returns a ResponseEntity<> object of one of the following statuses: <ul>
     *<li>OK: item deleted successfully.</li>
     *<li>NOT_FOUND: item not deleted.</li></ul>
     * @param id item id.
     * @return new ResponseEntity<>(HttpStatus.OK) or new ResponseEntity<>(HttpStatus.NOT_MODIFIED) object.
     */
    @DeleteMapping({"{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = itemService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * <p>Sorts items using the itemService.sort() method.</p>
     *
     * Returns a ResponseEntity<> object of one of the following statuses: <ul>
     *<li>OK: items sorted successfully.</li>
     *<li>NOT_MODIFIED: items not sorted.</li></ul>
     * @return new ResponseEntity<>(sorted, HttpStatus.OK) or new ResponseEntity<>(HttpStatus.NOT_MODIFIED) object.
     */
    @GetMapping("/sort")
    public ResponseEntity<?> sort() {
        final ArrayList<Item> sorted = (ArrayList<Item>) itemService.sort();
//        return sorted;
        return sorted != null
                ? new ResponseEntity<>(sorted, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

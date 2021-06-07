package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.coursework.model.entity.Game;
import ru.mirea.coursework.model.repository.GameRepository;
import ru.mirea.coursework.model.repository.UserRepository;
import ru.mirea.coursework.model.service.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/games")
public class GameController {
    //List<Level> levels;
    //внедряем зависимость от LevelService
    public final GameService gameService;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
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
    public void create(@RequestParam String name, @RequestParam String creationDate, Map<String, Object> model){
        Game game = new Game(name, creationDate);
        gameRepository.save(game);
        //Iterable<Game> games = gameRepository.findAll();
        //model.put("messages", games);
    }

    @GetMapping("/hi")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }

    @GetMapping
//    public List<Level> getLevels() {
//        return levels;}
    //read
    public ResponseEntity<List<Game>> read() {
        final List<Game> games = gameService.readAll();
        return games != null && !games.isEmpty()
                ? new ResponseEntity<>(games, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //новое. Получение по id
    @GetMapping("/{id}")
    public ResponseEntity<Game> read(@PathVariable(name = "id") int id) {
        final Game game = gameService.read(id);

        return game != null
                ? new ResponseEntity<>(game, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Game game) {
        final boolean updated = gameService.update(game, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = gameService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sort() {
        final ArrayList<Game> sorted = (ArrayList<Game>) gameService.sort();
//        return sorted;
        return sorted != null
                ? new ResponseEntity<>(sorted, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
//    @GetMapping("/del")
//    @ResponseBody
//    public String delLevel(@RequestParam(value="name")String newName) {
//        for (Level level : levels)
//        {
//            if (level.getLevelName().equals(newName))
//            {
//                levels.remove(level);
//                return newName + " deleted successfully";
//            }
//        }
//        return newName + " not found";
//    }

//    @RequestMapping("/add")
//    @ResponseBody
//    public Object addlevel(@RequestParam(value="name")String newName, @RequestParam(value="number")int number){
//        Level x = new Level(newName, number);
//        levels.add(x);
//        return x;
//    }
}

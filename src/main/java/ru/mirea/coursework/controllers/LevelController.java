package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.practice14_25.model.entity.Level;
import ru.mirea.practice14_25.model.service.LevelService;

import java.util.List;

@Controller
@RequestMapping("/levels")
public class LevelController {
    //List<Level> levels;
    //внедряем зависимость от LevelService
    public final LevelService levelService;
    @Autowired
    public LevelController(LevelService levelService)
    {
        this.levelService = levelService;
    }

    //create
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Level level){
        levelService.create(level);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/hi")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }
    @GetMapping
//    public List<Level> getLevels() {
//        return levels;}
    //read
    public ResponseEntity<List<Level>> read(){
        final List<Level> levels = levelService.readAll();
        return levels != null && !levels.isEmpty()
                ? new ResponseEntity<>(levels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //новое. Получение по id
    @GetMapping("/{id}")
    public ResponseEntity<Level> read(@PathVariable(name = "id") int id) {
        final Level level = levelService.read(id);

        return level != null
                ? new ResponseEntity<>(level,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id")int id, @RequestBody Level level){
        final boolean updated = levelService.update(level, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping({"{id}"})
        public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean deleted = levelService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
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

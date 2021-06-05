package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.coursework.model.entity.Game;
import ru.mirea.coursework.model.entity.User;
import ru.mirea.coursework.model.repository.GameRepository;
import ru.mirea.coursework.model.repository.UserRepository;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private GameRepository gameRepository;

    //@GetMapping
    //public String userList(Model model){
        //model.addAttribute("users", userRepository.findAll());
      //  return "userList.ftlh";
    //}

    @GetMapping
    public String something(Map<String, Object> model){
        Iterable<Game> games = gameRepository.findAll();
        model.put("messages", games);
        return "admin";
    }
    @PostMapping
    public String create(@RequestParam String name, @RequestParam String creationDate, Map<String, Object> model){
        Game game = new Game(name, creationDate);
        gameRepository.save(game);
        Iterable<Game> games = gameRepository.findAll();
        model.put("messages", games);
        return "admin";
    }
}

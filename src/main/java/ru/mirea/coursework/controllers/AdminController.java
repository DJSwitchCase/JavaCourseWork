package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.coursework.model.entity.Game;
import ru.mirea.coursework.model.repository.GameRepository;
import ru.mirea.coursework.model.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public String userList(Model model){
//        model.addAttribute("users", userRepository.findAll());
//        return "userList";
//    }

    @GetMapping
    public String something(Map<String, Object> model, Model model2){
        Iterable<Game> games = gameRepository.findAll();
        model.put("messages", games);
        model2.addAttribute("users", userRepository.findAll());
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

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Game> messages; //Потому что findAll() возвращает Iterable
                                //FindByCD возвращает List, а List имплементирует Iterable
        if (filter != null && !filter.isEmpty())
            messages = gameRepository.findByCreationDate(filter);
        else
            messages = gameRepository.findAll();
        model.put("messages", messages);
        return "admin";
    }
}

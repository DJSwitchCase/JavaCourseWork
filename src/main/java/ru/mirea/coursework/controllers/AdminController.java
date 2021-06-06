package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.coursework.model.entity.Game;
import ru.mirea.coursework.model.entity.Role;
import ru.mirea.coursework.model.entity.User;
import ru.mirea.coursework.model.repository.GameRepository;
import ru.mirea.coursework.model.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public String Lists(Map<String, Object> model, Model model2){
        Iterable<Game> games = gameRepository.findAll();
        model.put("messages", games);
        model2.addAttribute("users", userRepository.findAll());
        return "admin";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
    model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @GetMapping("games/{game}")
    public String userEditForm(@PathVariable Game game, Model model){
        model.addAttribute("game", game);
        return "gameEdit";
    }



    @PostMapping
    public String create(@RequestParam String name, @RequestParam String creationDate, Map<String, Object> model){
        Game game = new Game(name, creationDate);
        gameRepository.save(game);
        Iterable<Game> games = gameRepository.findAll();
        model.put("messages", games);
        return "admin";
    }
    @PostMapping("/games/{game}")
    public String gameSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam String creationDate,
            @RequestParam ("gameId") Game game){
        game.setName(name);
        game.setCreationDate(creationDate);
//        //из enum в строку
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name).collect(Collectors.toSet());
//        //Проверяем, что форма содержит роли для пользователя.
//        // Т.к. если роль существует, то у пользователя где-то флажок установлен
//        //user.getRoles().clear();
//        for (String key : form.keySet()){
//            if (roles.contains(key)){
//                user.getRoles().add(Role.valueOf(key));
//            }
        gameRepository.save(game);
        return "redirect:/admin";
        }

    @PostMapping("{user}")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam ("userId") User user){
        user.setUsername(username);
        //из enum в строку
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name).collect(Collectors.toSet());
        //Проверяем, что форма содержит роли для пользователя.
        // Т.к. если роль существует, то у пользователя где-то флажок установлен
        //user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
        return "redirect:/admin";
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

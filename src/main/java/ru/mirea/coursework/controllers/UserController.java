package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.coursework.model.entity.Game;
import ru.mirea.coursework.model.repository.GameRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final GameRepository gameRepository;

    public UserController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping()
    public String user(Map<String, Object> model, Model model2, HttpServletRequest request){
        Iterable<Game> games = gameRepository.findAll();
        model.put("messages", games);
        return "user";
    }
}

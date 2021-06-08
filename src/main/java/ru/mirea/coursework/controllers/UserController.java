package ru.mirea.coursework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.repository.ItemRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final ItemRepository gameRepository;

    public UserController(ItemRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping()
    public String user(Map<String, Object> model, Model model2, HttpServletRequest request){
        Iterable<Item> games = gameRepository.findAll();
        model.put("messages", games);
        return "user";
    }
    @GetMapping("/purchase")
        public String purchase(){
        return "purchase";
    }
}


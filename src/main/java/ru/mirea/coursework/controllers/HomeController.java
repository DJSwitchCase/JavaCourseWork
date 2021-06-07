package ru.mirea.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.coursework.model.entity.Role;
import ru.mirea.coursework.model.entity.User;
import ru.mirea.coursework.model.repository.UserRepository;

import java.util.Collections;
import java.util.Map;

@Controller
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello.html";
    }

    @GetMapping("/registration")

    public String registration() {
        return"registration";
    }
//    @GetMapping("/user")
//    public String user(){
//        return "user";
//    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
         User userFromDB = userRepository.findByUsername(user.getUsername());
        System.out.println(userFromDB);
        System.out.println("Privet1");
         if (userFromDB != null){
            System.out.println("Privet2");
            model.put("message", "Пользователь с такими данными уже существует!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
//
//    @GetMapping("/login")
//    public String sayHello(){
//        return "login";
//    }
}

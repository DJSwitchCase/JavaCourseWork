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

/**
 * <b>Handles registration/login page redirects and registration.</b>
 */
@Controller
public class HomeController {
    private final UserRepository userRepository;

    /**
     * Initialized the userRepository field.
     */
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Redirects to the hello.html page.
     */
    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello.html";
    }

    /**
     * Redirects to the registration page.
     * @return "registration".
     */
    @GetMapping("/registration")
    public String registration() {
        return"registration";
    }
//    @GetMapping("/user")
//    public String user(){
//        return "user";
//    }

    /**
     * Checks if a registrating user exists and adds a new one to the database if it does not.
     * @param user user.
     * @param model model/
     * @return "registration" or "login"
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
         User userFromDB = userRepository.findByUsername(user.getUsername());
         if (userFromDB != null){
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

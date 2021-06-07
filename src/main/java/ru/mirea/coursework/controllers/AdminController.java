package ru.mirea.coursework.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.entity.Role;
import ru.mirea.coursework.model.entity.User;
import ru.mirea.coursework.model.repository.ItemRepository;
import ru.mirea.coursework.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController implements AuthenticationSuccessHandler {

    private final ItemRepository itemsRepository;
    private final UserRepository userRepository;

    public AdminController(ItemRepository itemsRepository, UserRepository userRepository) {
        this.itemsRepository = itemsRepository;
        this.userRepository = userRepository;
    }

//    @GetMapping
//    public String userList(Model model){
//        model.addAttribute("users", userRepository.findAll());
//        return "userList";
//    }



    @GetMapping()
    public String Main(Map<String, Object> model, Model model2, HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("USER"))
            response.sendRedirect("/user");
        Iterable<Item> items = itemsRepository.findAll();
        model.put("messages", items);
        model2.addAttribute("users", userRepository.findAll());
        return "admin";
    }
    @DeleteMapping()
    public String delete(){

        return "admin";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_USER"))
            response.sendRedirect("/userpage");
    }

    @GetMapping("items/{item}")
    public String userEditForm(@PathVariable Item item, Model model){
        model.addAttribute("item", item);
        return "itemEdit";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public String create(@RequestParam String name, @RequestParam String price, Map<String, Object> model){
        Item item = new Item(name, price);
        itemsRepository.save(item);
        Iterable<Item> items = itemsRepository.findAll();
        model.put("messages", items);
        return "redirect:/admin";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/items/{item}")
    public String itemSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam String price,
            @RequestParam ("item") Item item){
        item.setName(name);
        item.setPrice(price);
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
        itemsRepository.save(item);
        return "redirect:/admin";
        }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Item> messages; //Потому что findAll() возвращает Iterable
                                //FindByCD возвращает List, а List имплементирует Iterable
        if (filter != null && !filter.isEmpty())
            messages = itemsRepository.findByPrice(filter);
        else
            messages = itemsRepository.findAll();
        model.put("messages", messages);
        return "admin";
    }
}
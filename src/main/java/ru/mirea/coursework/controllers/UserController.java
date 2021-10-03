package ru.mirea.coursework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.coursework.model.entity.Item;
import ru.mirea.coursework.model.repository.ItemRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <b>Contains two GET-request handlers used to: </ol>
 *  <li>Get all the users,</li>
 *  <li>Redirect to the purchase page.</li>
 * </ol></b>
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * Stores an itemRepository link.
     * @see ru.mirea.coursework.model.repository.ItemRepository
     */
    private final ItemRepository itemRepository;

    /**
     * Initializes the itemRepository field.
     */
    public UserController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Finds all the users and shows them on the model.
     * @return "user".
     */
    @GetMapping()
    public String user(Map<String, Object> model){
        Iterable<Item> games = itemRepository.findAll();
        model.put("messages", games);
        return "user";
    }

    /**
     * Redirects to the purchase page.
     * @return "purchase".
     */
    @GetMapping("/purchase")
        public String purchase(){
        return "purchase";
    }
}


package io.grz.cocktail.controller.user;

import io.grz.cocktail.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ItemService itemService;

    @GetMapping("/manager")
    public void index(Model model){


    }

}

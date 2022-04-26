package io.grz.cocktail.controller.user;

import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import io.grz.cocktail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerPage(){
        return "register/register-index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login/login-index";
    }


}

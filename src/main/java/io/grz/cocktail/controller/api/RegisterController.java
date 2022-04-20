package io.grz.cocktail.controller.api;

import io.grz.cocktail.model.user.User;
import io.grz.cocktail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody User user){
        if(user.getPassword()==null) return new ResponseEntity<>("올바르지 않은 비밀번호입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        boolean result = userService.isUsernameUnique(user.getUsername());
        if (!result) return new ResponseEntity<>("이미 사용중인 닉네임입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        userService.register(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

package io.grz.cocktail.controller.api;

import io.grz.cocktail.dto.UserAuthDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserAuthDTO dto, BindingResult bindingResult){
        User user = userService.findByUsername(dto.getUsername());
        if (user!=null) return new ResponseEntity<>("이미 사용중인 이메일입니다.", HttpStatus.BAD_REQUEST);

        userService.register(dto);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}

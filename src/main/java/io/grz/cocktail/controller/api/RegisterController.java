package io.grz.cocktail.controller.api;

import io.grz.cocktail.dto.UserAuthDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserAuthDTO dto, HttpServletResponse response, BindingResult bindingResult) throws IOException {
        User user = userService.findByUsername(dto.getUsername());
        Map<String, String> dataMap = new HashMap<>();
        if (user!=null){
            log.info("Response BAD_REQUEST - Existing user tried to register : "+"username: "+dto.getUsername());
            dataMap.put("message", "이미 사용중인 이메일입니다.");
            return new ResponseEntity<>(dataMap, HttpStatus.BAD_REQUEST);
        }

        userService.register(dto);
        log.info("Response OK - New user registered : "+"username: "+dto.getUsername());
        dataMap.put("message", "회원가입이 완료되었습니다.");

        return new ResponseEntity<>(dataMap, HttpStatus.OK);
    }

}

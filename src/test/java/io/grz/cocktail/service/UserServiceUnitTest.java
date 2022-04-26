package io.grz.cocktail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.grz.cocktail.dto.UserAuthDTO;
import io.grz.cocktail.dto.UserDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// 단위테스트 (Service와 관련된 객체만 메모리에 띄우면 됨)
// @Mockito = UserRepository => mock 오브젝트로 만들어줌

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks // 해당 객체의 필드에 Mock로 등록된 멤버를 주입시켜줌
    private UserService userService;

    @Test
    public void register_test1(){

        log.info("###Started unit testing : register_test1");

        // given
        String username = "user@name";
        String password = "raw-password";
        String nickname = "registering-user";
        UserAuthDTO dto = UserAuthDTO.builder().username(username)
                                                                .password(password)
                                                                .nickname(nickname).build();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setRole("USER");
        when(userRepository.save(any())).thenReturn(user);

        // when
        User result = userService.register(dto);

        // then
        Assertions.assertEquals(0, result.getId());
        Assertions.assertEquals(username, result.getUsername());
        Assertions.assertEquals(password, result.getPassword());
        Assertions.assertEquals(nickname, result.getNickname());
        Assertions.assertEquals("USER", result.getRole());
    }


}

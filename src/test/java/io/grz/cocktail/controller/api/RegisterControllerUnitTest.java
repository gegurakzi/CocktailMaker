package io.grz.cocktail.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grz.cocktail.config.oauth.PrincipalOAuth2UserService;
import io.grz.cocktail.dto.UserAuthDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@WebMvcTest // 메모리에 Controller, Filter, ControllerAdvice가 뜸 // @WebMVCTest에 포함된 @ExtendWith(SpringExtension.class)은 스프링 환경에서 구동하도록 확장해줌 이 어노테이션이 없다면 그저 독립적 클래스로 취급됨
public class RegisterControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrincipalOAuth2UserService principalOAuth2UserService;

    @MockBean
    private UserService userService;

    @Test
    public void register_test1() throws Exception {

        log.info("###Started unit testing : register_test1");

        // given (테스트 준비)

            //받을 파라미터 지정(string-JSON)
        UserAuthDTO testDto = UserAuthDTO.builder().username("user@name").password("password").nickname("NickName").build();
        String reqBody = new ObjectMapper().writeValueAsString(testDto);
        log.info("request content : " + reqBody);

            // 반환할 user 지정
        User testUser = new User();
        testUser.setUsername("user@name");
        testUser.setPassword("encrypted-password");
        testUser.setNickname("existing-user");
        when(userService.findByUsername(testDto.getUsername())).thenReturn(testUser);

        // when (테스트 실행)

        ResultActions resultActions = mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqBody)
                .accept(MediaType.TEXT_PLAIN));

        // then (테스트 검증)

        resultActions
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("이미 사용중인 이메일입니다."))
                .andDo(print());

    }

    @Test
    public void register_test2() throws Exception {

        log.info("###Started testing : register_test2");

        // given

        UserAuthDTO testDto = UserAuthDTO.builder().username("user@name").password("password").nickname("NickName").build();
        String reqBody = new ObjectMapper().writeValueAsString(testDto);
        log.info("request content : " + reqBody);

        when(userService.findByUsername(testDto.getUsername())).thenReturn(null);

        // when

        ResultActions resultActions = mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqBody)
                .accept(MediaType.TEXT_PLAIN));

        // then (테스트 검증)

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("OK"))
                .andDo(print());

    }

}

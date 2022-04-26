package io.grz.cocktail.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.grz.cocktail.config.oauth.PrincipalOAuth2UserService;
import io.grz.cocktail.dto.UserAuthDTO;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import io.grz.cocktail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 통합테스트
// 모든  Bean을 IoC 올리고 테스트 하는것
// WebEnvironment.MOCK = 실제 톰캣을 올리는게 아니라 다른 톰캣을 사용하여 테스트
// WebEnvironment.RANDOM_PORT = 실제 프로젝트에 사용하는 톰캣으로 테스트
// @AutoConfigureMovkMvc = MockMvc를 의존성 주입 해줌
// @Transactional = 각 테스트 함수가 종료될 떄 마다 rollback시켜줌

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //
public class RegisterControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void register_test1() throws Exception {

        log.info("###Started integration testing : register_test1");

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
        testUser.setRole("USER");
        userRepository.save(testUser);

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

package io.grz.cocktail.config.jwt;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grz.cocktail.config.auth.PrincipalDetails;
import io.grz.cocktail.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFIlter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 1. username, password를 받아서 로그인을 시도함
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user.getUsername(),  user.getPassword());
            // 2. authenticationManager로 로그인 시도를 하면  PrincipalDetailService가 호출되어 loadUserByUsername() 함수가 실행됨

            Authentication authentication  = authenticationManager.authenticate(authenticationToken);
            // 3. 반환 시 authentication 객체를 세션에 담음 -> JWT는 세션을 만들 이유가 없지만 시큐리티가 제공하는 사용자의 권한 관리를 위해 만듬
            return authentication;
        } catch (IOException e) {
            log.info("JWTAuthenticationFIlter.attemptAuthentication()   : "+e.getCause());
            return null;
        } catch (NullPointerException e) {
            log.info("JWTAuthenticationFIlter.attemptAuthentication()   : Failed to Authenticate user["+e.getCause()+"]");
            try {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "해당 사용자의 가입 기록이 없습니다");} catch(IOException io){io.printStackTrace();}
            return null;
        }
    }

    // AttemptAuthentication실행 후 인증이 정상적으로 진행되었으면 successfulAuthentication 함수가 실행됨
    // 4. JWT를 발행하고 request한 사용자에게 토큰은 응답해줌
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATIONAL_TIME))
                .withClaim("id", principalDetails.getId())
                .withClaim("username", principalDetails.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader("Authorization", "Bearer "+jwtToken);
        response.setStatus(HttpStatus.OK.value());
    }
}

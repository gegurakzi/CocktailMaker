package io.grz.cocktail.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.grz.cocktail.config.auth.PrincipalDetails;
import io.grz.cocktail.model.user.User;
import io.grz.cocktail.repository.UserRepository;
import org.apache.tomcat.jni.Time;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository){
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String bearer = request.getHeader("Authorization");
        if((request.getMethod().equals("OPTIONS")) || (bearer == null) || (!bearer.startsWith("Bearer "))){
            chain.doFilter(request, response);
            return;
        }
        String token = bearer.replace("Bearer ", "");
        System.out.println(token);
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build()
                                                        .verify(token)
                                                        .getClaim("username").asString();

        if(username!=null){
            User user = userRepository.findByUsername(username);
            user.setAccessDate(new Timestamp(Time.now()));
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails.getUsername(), principalDetails.getPassword(), principalDetails.getAuthorities());
            System.out.println(authentication.getPrincipal().toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}

package io.grz.cocktail.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg="";
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            msg = "아이디 혹은 비밀번호가 맞지 않습니다.";
        } else if(exception instanceof UsernameNotFoundException){
            msg = "사용자가 존재하지 않습니다. 이메일을 확인해주세요";
        } else msg = "Unknown Exception";
        log.warn(exception.getClass()+" : "+msg);
        msg= URLEncoder.encode(msg,"UTF-8");
        setDefaultFailureUrl("/login?error=true&exception="+msg);
        super.onAuthenticationFailure(request, response, exception);
    }
}

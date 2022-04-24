package io.grz.cocktail.config.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.grz.cocktail.model.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class DebugFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //아이디와 패스워드가 정상적으로 들어와서 로그인이 완료되면 토큰을 생성, 응답해준다

        request.getParameter("username");
        request.getParameter("password");

        System.out.println("======================"+request.getParameter("username") + request.getParameter("password"));


        chain.doFilter(request, response);
    }

}

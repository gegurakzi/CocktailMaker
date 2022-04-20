package io.grz.cocktail.config.filter;


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

        String strreq = req.toString();

        System.out.println("Debug Filter :" + strreq);
        chain.doFilter(req, res);
    }

}

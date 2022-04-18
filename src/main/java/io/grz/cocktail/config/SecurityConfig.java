package io.grz.cocktail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                // DB에 role은 prefix로 ROLE_을 가져야만 Security에서 인식한다. hasRole은 ROLE_을 자동으로 prefix로 붙여 사용자의 권한을 체크한다.
                .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login/**", "/register/**").not().authenticated()

                .anyRequest().permitAll()

                //form을 이용한 자체 로그인
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/");

    }
}

package io.grz.cocktail.config;

import io.grz.cocktail.config.filter.DebugFilter;
import io.grz.cocktail.config.oauth.PrincipalOAuth2UserService;
import io.grz.cocktail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOAuth2UserService principalOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterBefore(new DebugFilter(), SecurityContextPersistenceFilter.class);
        http.csrf().disable()
                .cors()
                .and()

                .authorizeRequests()
                    .antMatchers("/user/**").authenticated()
                    // DB에 role은 prefix로 ROLE_을 가져야만 Security에서 인식한다. hasRole은 ROLE_을 자동으로 prefix로 붙여 사용자의 권한을 체크한다.
                    .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/login/**", "/register/**", "/api/**").not().authenticated()
                    .anyRequest().permitAll()

                .and()
                    .formLogin()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .loginProcessingUrl("/login/authenticate")
                        .defaultSuccessUrl("/")
                .and()
                    .logout()
                        .logoutSuccessUrl("/")

                .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .userInfoEndpoint().userService(principalOAuth2UserService);

    }
}

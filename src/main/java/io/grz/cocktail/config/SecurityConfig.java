package io.grz.cocktail.config;

import io.grz.cocktail.config.jwt.JWTAuthenticationFIlter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterAfter(new DebugFilter(), FilterSecurityInterceptor.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JWTAuthenticationFIlter(authenticationManager()))

                .authorizeRequests()
                    .antMatchers("/user/**").authenticated()
                    // DB에 role은 prefix로 ROLE_을 가져야만 Security에서 인식한다. hasRole은 ROLE_을 자동으로 prefix로 붙여 사용자의 권한을 체크한다.
                    .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/login/**", "/register/**", "/api/**").not().authenticated()
                    .anyRequest().permitAll();

    }
}

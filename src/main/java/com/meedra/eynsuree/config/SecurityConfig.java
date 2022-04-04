package com.meedra.eynsuree.config;

import com.meedra.eynsuree.implementation.AuthenticationProviderService;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableScheduling
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {



    @Autowired
    private AuthenticationProviderService authenticationProvider;

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }


    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/registration**")
//                .access("hasRole('ROLE_USER')")
//                .antMatchers("/", "/**").access("permitAll")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true).clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
//                logoutSuccessUrl("/")
//                .permitAll();
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/login*")
//                .authorizeRequests()
//                .antMatchers("/registration**", "/js/**", "/css/**", "/images/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true).clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
//                logoutSuccessUrl("/login?logout")
//                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/registration**", "/js/**", "/css/**", "/images/**"
                        ,"/home", "/login", "/css/vendor/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                logoutSuccessUrl("/login?logout")
                .permitAll();

    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }





}

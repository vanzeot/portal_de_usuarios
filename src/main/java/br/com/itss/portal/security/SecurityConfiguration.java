package br.com.itss.portal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/usuarios", true)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/login?logout=true"))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
        Para utilizar a codifica????o BCrypt, descomentar:

        return new BCryptPasswordEncoder();
         */
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return CodificacaoMd5.codificar(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return CodificacaoMd5.comparar(charSequence,s);
            }
        };
    }

}

/*

// Vers??o depreciada

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    *@Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // Autenticar todas as requisi????es
                .anyRequest().authenticated()
                // Personalizar login
                .and().formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/usuarios", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
                // Configurar logout
                .and().logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                // Configurar autnetica????o para basic
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}*/
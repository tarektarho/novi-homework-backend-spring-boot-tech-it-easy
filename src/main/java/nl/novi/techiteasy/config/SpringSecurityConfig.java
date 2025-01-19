package nl.novi.techiteasy.config;

import nl.novi.techiteasy.enums.RoleEnum;
import nl.novi.techiteasy.filter.JwtRequestFilter;
import nl.novi.techiteasy.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*  Deze security is niet de enige manier om het te doen.
    In de andere branch van deze github repo staat een ander voorbeeld
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }




    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                                auth
                                        // Publieke endpoints
                                        // Todo fix: getting the following error 500:"An unexpected error occurred: Handler dispatch failed: java.lang.NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'"
                                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                        // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
                                        //.requestMatchers("/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.POST, "/api/v1/cimodules").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cimodules/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.POST, "/api/v1/remotecontrollers").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/remotecontrollers/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.POST, "/api/v1/televisions").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/televisions/**").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.POST, "/api/v1/wallbrackets").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/wallbrackets/**").hasRole(RoleEnum.ADMIN.name())
                                        // Je mag meerdere paths tegelijk definieren
                                        .requestMatchers("/api/v1/cimodules", "/api/v1/remotecontrollers", "/api/v1/televisions", "/api/v1/wallbrackets").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())
                                        .requestMatchers("/api/v1/authenticated").authenticated()
                                        .requestMatchers("/api/v1/authenticate").permitAll()
                                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
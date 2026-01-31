package com.allcity.security;

import com.allcity.exceptions.CustomAccessDenialHandler;
import com.allcity.exceptions.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityFilter {

    private final AuthFilter authFilter;
    private final CustomAccessDenialHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // ✅ CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 🔓 PUBLIC
                        .requestMatchers("/api/auth/**", "/images/**").permitAll()

                        // 🔓 PUBLIC READ
                        .requestMatchers(HttpMethod.GET, "/api/vehicle-purchases/**").permitAll()

                        // 🔐 ADMIN ONLY (WRITE)
                        .requestMatchers(HttpMethod.POST, "/api/vehicle-purchases/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/vehicle-purchases/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/vehicle-purchases/**").hasAuthority("ADMIN")

                        // 🔐 ADMIN APIs
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")

                        // 🔐 MANAGER + ADMIN
                        .requestMatchers("/api/manager/**")
                        .hasAnyAuthority("MANAGER", "ADMIN")

                        // 🔐 USER + MANAGER + ADMIN
                        .requestMatchers("/api/user/**")
                        .hasAnyAuthority("USER", "MANAGER", "ADMIN")

                        // 🔐 AUTHENTICATED (ANY ROLE)
                        .requestMatchers(
                                "/api/bookings/**",
                                "/api/vehicles/**",
                                "/api/drivers/**"
                        ).authenticated()

                        .anyRequest().authenticated()
                )

                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ IMPORTANT: You REMOVED ROLE_ PREFIX
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}


package com.allcity.security;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.cors.CorsConfiguration;
        import org.springframework.web.cors.CorsConfigurationSource;
        import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

        import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // ✅ FIX 1: Use allowedOriginPatterns (NOT allowedOrigins)
        config.setAllowedOriginPatterns(List.of(
                "http://localhost:59753",
                "https://allcity-transport-erp-frontend.onrender.com"
        ));

        // ✅ FIX 2: Explicitly allow headers used by JWT
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "X-Requested-With"
        ));

        // ✅ FIX 3: Expose Authorization header to browser
        config.setExposedHeaders(List.of("Authorization"));

        // ✅ HTTP methods
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        // ✅ REQUIRED when using JWT + frontend
        config.setAllowCredentials(true);

        // ⏱ Optional but recommended
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}






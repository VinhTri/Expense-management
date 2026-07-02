package org.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF (Bắt buộc cho API)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Bật CORS (Để Front-end gọi không bị lỗi Cross-Origin)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 3. Phân quyền các bãi đỗ xe (Endpoints)
                .authorizeHttpRequests(auth -> auth
                        // Cho phép gọi Đăng ký, Đăng nhập thoải mái không cần vé
                        .requestMatchers("/api/auth/**").permitAll()

                        // TẤT CẢ các đường dẫn còn lại: Phải trình vé (JWT) ra mới cho vào!
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}

package com.vivek.config;

import com.vivek.entity.CarUser;
import com.vivek.repository.CarUserRepository;
import com.vivek.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SpringConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;

    @Autowired
    public SpringConfig(CustomUserDetailsService customUserDetailsService,
                        CorsConfigurationSource corsConfigurationSource){
        this.customUserDetailsService=customUserDetailsService;
        this.corsConfigurationSource=corsConfigurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf ->csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public — no login
                        .requestMatchers(
                                "/auth/**",
                                "/car/findAll",
                                "/car/findById",
                                "/car/findCompany",
                                "/car/findAllByModel",
                                "/car/findByMilageabove20",
                                "/car/findByColor",
                                "/user/save"
                        ).permitAll()

                        // USER only
                        .requestMatchers("/car/purchase/**").hasRole("USER")

                        // ADMIN only
                        .requestMatchers(
                                "/car/save",
                                "/car/update",
                                "/car/delete",
                                "/user/update",
                                "/admin/**"
                        ).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .userDetailsService(customUserDetailsService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    CommandLineRunner initUsers(CarUserRepository repo,PasswordEncoder encoder){
        return args -> {
            if(!repo.existsByEmail("admin@gmail.com")){
                CarUser admin=new CarUser();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin@123"));
                admin.setRole("ADMIN");
                repo.save(admin);

                System.out.println("ADMIN USER CREATED: admin@gmail.com / admin123");
            }
        };
    }


}

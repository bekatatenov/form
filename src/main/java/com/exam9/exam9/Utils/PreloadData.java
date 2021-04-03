package com.exam9.exam9.Utils;

import com.exam9.exam9.repository.CommentRepository;
import com.exam9.exam9.repository.ThemeRepository;
import com.exam9.exam9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class PreloadData {
    private final PasswordEncoder passwordEncoder;


    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ThemeRepository themeRepository,
                                   CommentRepository commentRepository) {
        return (args) -> {

        };
    }
}

package com.exam9.exam9.service;

import com.exam9.exam9.repository.CommentRepository;
import com.exam9.exam9.repository.ThemeRepository;
import com.exam9.exam9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ThemeRepository themeRepository,
                                   CommentRepository commentRepository) {
        return (args) -> {

        };
    }
}

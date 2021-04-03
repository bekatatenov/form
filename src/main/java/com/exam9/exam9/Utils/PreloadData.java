package com.exam9.exam9.Utils;

import com.exam9.exam9.model.Comment;
import com.exam9.exam9.model.Theme;
import com.exam9.exam9.model.User;
import com.exam9.exam9.repository.CommentRepository;
import com.exam9.exam9.repository.ThemeRepository;
import com.exam9.exam9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class PreloadData {
    private final PasswordEncoder passwordEncoder;


    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ThemeRepository themeRepository,
                                   CommentRepository commentRepository) {
        return (args) -> {
            commentRepository.deleteAll();
            themeRepository.deleteAll();
            userRepository.deleteAll();

            List<User> users = makeUsers(userRepository);
            userRepository.saveAll(users);

            List<Theme> themes = makeThemes(users);
            themeRepository.saveAll(themes);

            List<Comment> comments = makeComments(users, themes, themeRepository);
            commentRepository.saveAll(comments);


        };
    }

    private List<Comment> makeComments(List<User> users, List<Theme> themes, ThemeRepository themeRepository) {
        List<Comment> comments = new ArrayList<>();
        Random rnd = new Random();
        for (Theme t :
                themes) {
            for (int i = 0; i < 12; i++) {
                comments.add(Comment.builder()
                        .text("CommentTest" + i)
                        .time(LocalDateTime.now())
                        .user(users.get(rnd.nextInt(4) + 1))
                        .theme(t)
                        .build());
                t.setQty(t.getQty() + 1);
                themeRepository.save(t);
            }
        }
        return comments;

    }

    private List<Theme> makeThemes(List<User> users) {
        Random rnd = new Random();
        List<Theme> themes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            themes.add(Theme.builder()
                    .name("Theme" + i)
                    .qty(0)
                    .text("ThemeText" + i)
                    .time(LocalDateTime.now())
                    .user(users.get(rnd.nextInt(4) + 1))
                    .build());
        }
        return themes;
    }

    private List<User> makeUsers(UserRepository userRepository) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            users.add(User.builder()
                    .email("test@gmail" + i)
                    .password(passwordEncoder.encode("test123123"))
                    .name("user" + i)
                    .build());
        }
        return users;
    }
}

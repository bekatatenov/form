package com.exam9.exam9.service;

import com.exam9.exam9.exception.UserAlreadyRegisteredException;
import com.exam9.exam9.exception.UserNotFoundException;
import com.exam9.exam9.form.RegisterForm;
import com.exam9.exam9.model.User;
import com.exam9.exam9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User register(RegisterForm form) {
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }

        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .build();
        userRepository.save(user);
        return user;
    }
}

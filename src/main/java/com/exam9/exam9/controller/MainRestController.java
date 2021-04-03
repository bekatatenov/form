package com.exam9.exam9.controller;

import com.exam9.exam9.model.Theme;
import com.exam9.exam9.model.User;
import com.exam9.exam9.service.ThemeService;
import com.exam9.exam9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class MainRestController {
    private final UserService userService;
    private final ThemeService themeService;

    @PostMapping("/addTheme")
    public void addComment(@RequestParam("text") String text, @RequestParam("name") String name, Principal principal) {
        User byEmail = userService.findByEmail(principal.getName());
        Theme theme = Theme.builder()
                .user(byEmail)
                .time(LocalDateTime.now())
                .text(text)
                .name(name)
                .qty(0)
                .build();
        themeService.save(theme);
    }
}

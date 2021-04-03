package com.exam9.exam9.controller;


import com.exam9.exam9.model.Theme;
import com.exam9.exam9.service.CommentService;
import com.exam9.exam9.service.ThemeService;
import com.exam9.exam9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final CommentService commentService;
    private final UserService userService;
    private final ThemeService themeService;

    @GetMapping
    public String index(@RequestParam(defaultValue = "1") Integer page, Principal principal, Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        if (principal != null) {
            if (userService.existByEmail(principal.getName())) {
                model.addAttribute("user", userService.findByEmail(principal.getName()));
            }
        }
        var uri = uriBuilder.getRequestURI();

        Page<Theme> chats = themeService.findAllThemes(PageRequest.of(page - 1, 10));
        model.addAttribute("themes", chats.getContent());
        model.addAttribute("pages", chats.getTotalPages() - 1);
        model.addAttribute("page", page);
        model.addAttribute("lastPage", chats.getTotalPages()-1);
        return "index";
    }
}
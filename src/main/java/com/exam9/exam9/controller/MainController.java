package com.exam9.exam9.controller;


import com.exam9.exam9.exception.ResourceNotFoundException;
import com.exam9.exam9.form.FormConfirm;
import com.exam9.exam9.form.RegisterForm;
import com.exam9.exam9.model.Comment;
import com.exam9.exam9.model.Theme;
import com.exam9.exam9.model.User;
import com.exam9.exam9.service.CommentService;
import com.exam9.exam9.service.ThemeService;
import com.exam9.exam9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
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

        Page<Theme> chats = themeService.findAllThemes(PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "time")));
        model.addAttribute("themes", chats.getContent());
        model.addAttribute("pages", chats.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("lastPage", chats.getTotalPages());
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new FormConfirm());
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new RegisterForm());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid RegisterForm form, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasFieldErrors()) {
            throw new BindException(bindingResult);
        }
        userService.register(form);
        return "redirect:/login";
    }

    @GetMapping("/theme/{id}")
    public String showTheme(@RequestParam(defaultValue = "1") Integer page, @PathVariable Long id, Model model, Principal principal) {
        if (!themeService.existbyId(id)) {
            throw new ResourceNotFoundException("There is such Theme with id", id);
        }
        Theme byId = themeService.findById(id);
        model.addAttribute("theme", byId);

        if (principal != null) {
            if (userService.existByEmail(principal.getName())) {
                model.addAttribute("user", userService.findByEmail(principal.getName()));
            }
        }

        Page<Comment> comment = commentService.findByThemeId(id, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "time")));
        model.addAttribute("comments", comment.getContent());
        model.addAttribute("pages", comment.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("lastPage", comment.getTotalPages());
        return "showtheme";
    }
}
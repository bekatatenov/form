package com.exam9.exam9.service;

import com.exam9.exam9.model.Theme;
import com.exam9.exam9.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;

    public Page<Theme> findAllThemes(Pageable pageable) {
        return themeRepository.findAll(pageable);
    }

    public void save(Theme theme) {
        themeRepository.save(theme);
    }

    public boolean existbyId(Long id) {
        return themeRepository.existsById(id);
    }

    public Theme findById(Long id) {
        return themeRepository.findById(id).get();
    }
}
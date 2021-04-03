package com.exam9.exam9.service;

import com.exam9.exam9.model.Comment;
import com.exam9.exam9.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Page<Comment> findByThemeId(Long id, Pageable pageable) {
        return commentRepository.findAllByTheme_Id(id, pageable);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}

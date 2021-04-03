package com.exam9.exam9.repository;

import com.exam9.exam9.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findAllByTheme_Id(Long theme_id, Pageable pageable);
}

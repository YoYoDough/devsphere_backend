package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.comment.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    List<LikeComment> findByUserEmail(String email);
}

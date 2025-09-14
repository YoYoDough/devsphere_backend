package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.post.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    int countByPostId(long id);
}


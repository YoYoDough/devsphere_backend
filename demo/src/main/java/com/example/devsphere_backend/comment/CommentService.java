package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.post.Post;
import com.example.devsphere_backend.post.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public Comment addComment(Long postId, String text, String author, LocalDateTime createdAt) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(author);
        comment.setPost(post);

        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return post.getCommentsList();
    }
}

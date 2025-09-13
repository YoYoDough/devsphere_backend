package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.post.Like;
import com.example.devsphere_backend.post.Post;
import com.example.devsphere_backend.post.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final LikeCommentRepository likeRepository;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo, LikeCommentRepository likeRepository) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.likeRepository = likeRepository;
    }

    public Comment addComment(Long postId, String text, String author, Instant createdAt, Long likes) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(author);
        comment.setLikes(likes);

        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return post.getCommentsList();
    }

    public Comment likeComment(long commentId){
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setLikes(comment.getLikes() + 1);
        commentRepo.save(comment);
        return comment;
    }

    public boolean getDidUserLikePost(Long postId, String email) {
        Comment comment = commentRepo.findById(postId).get();
        return comment.getLikesList()
                .stream()
                .anyMatch(like -> like.getUser().getEmail().equals(email));
    }

    public List<Long> getLikedCommentIdsByUser(String email) {
        return likeRepository.findByUserEmail(email)
                .stream()
                .map(like -> like.getComment().getId())
                .toList();
    }
}

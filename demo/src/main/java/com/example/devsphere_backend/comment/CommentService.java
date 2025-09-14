package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.post.Like;
import com.example.devsphere_backend.post.Post;
import com.example.devsphere_backend.post.PostRepository;
import com.example.devsphere_backend.user.User;
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
        comment.setPost(post);
        comment.setText(text);
        comment.setAuthor(author);
        comment.setLikes(likes);
        comment.setCreatedAt(createdAt);

        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return post.getCommentsList();
    }

    public Comment likeComment(long commentId, User user){
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Check if user already liked
        boolean alreadyLiked = comment.getLikesList().stream()
                .anyMatch(like -> like.getUser().getId() == (user.getId()));
        if (!alreadyLiked) {
            LikeComment like = new LikeComment();
            like.setComment(comment);
            like.setUser(user);
            likeRepository.save(like);

            comment.getLikesList().add(like); // optional if you fetch list later
            comment.setLikes(comment.getLikes() + 1); // increment likes count if you use a field
            commentRepo.save(comment);
        }

        return comment; // always return updated comment
    }

    public Comment unlikeComment(Long commentId, User user) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        // Find the like entry
        comment.getLikesList().stream()
                .filter(like -> like.getUser().getId() == user.getId())
                .findFirst()
                .ifPresent(like -> {
                    comment.getLikesList().remove(like);
                    likeRepository.delete(like); // remove from DB
                    comment.setLikes(Math.max(0, comment.getLikes() - 1));
                });

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

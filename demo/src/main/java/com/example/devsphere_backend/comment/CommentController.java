package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.mapper_utility.PostMapper;
import com.example.devsphere_backend.post.PostDTO;
import com.example.devsphere_backend.user.User;
import com.example.devsphere_backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    @Autowired
    public CommentController(CommentService commentService, UserRepository userRepository) {
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long postId,
            @RequestBody Comment commentRequest) {
        Comment saved = commentService.addComment(postId, commentRequest.getText(), commentRequest.getAuthor(), commentRequest.getCreatedAt(), commentRequest.getLikes());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long postId, @RequestParam String email) {
        User user = userRepository.findUserByEmail(email).get();
        List<Comment> comments = commentService.getCommentsForPost(postId);
        List<CommentDTO> commentDTOList = comments.stream()
                .map(comment -> PostMapper.toCommentDTO(comment, user.getId())) // don’t include comments
                .toList();
        return ResponseEntity.ok(commentDTOList);
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<Comment> likeComment(@PathVariable Long commentId, @RequestParam String email) {
        User user =userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Comment updatedComment = commentService.likeComment(commentId, user);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}/like")
    public ResponseEntity<Comment> unlikeComment(
            @PathVariable Long commentId,
            @RequestParam String email) {
        User user =userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Comment updatedComment = commentService.unlikeComment(commentId, user);
        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping("/liked")
    public ResponseEntity<List<Long>> getLikedComments(@RequestParam String email) {
        List<Long> likedCommentIds = commentService.getLikedCommentIdsByUser(email);
        return ResponseEntity.ok(likedCommentIds);
    }


}

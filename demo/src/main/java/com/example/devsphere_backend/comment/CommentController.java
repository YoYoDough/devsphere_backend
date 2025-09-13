package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.mapper_utility.PostMapper;
import com.example.devsphere_backend.post.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long postId,
            @RequestBody Comment commentRequest) {
        Comment saved = commentService.addComment(postId, commentRequest.getText(), commentRequest.getAuthor(), commentRequest.getCreatedAt(), commentRequest.getLikes());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsForPost(postId);
        List<CommentDTO> commentDTOList = comments.stream()
                .map(comment -> PostMapper.toCommentDTO(comment)) // don’t include comments
                .toList();
        return ResponseEntity.ok(commentDTOList);
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<Comment> likeComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.likeComment(commentId));
    }

    @GetMapping("/liked")
    public ResponseEntity<List<Long>> getLikedComments(@RequestParam String email) {
        List<Long> likedCommentIds = commentService.getLikedCommentIdsByUser(email);
        return ResponseEntity.ok(likedCommentIds);
    }


}

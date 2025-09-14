package com.example.devsphere_backend.mapper_utility;

import com.example.devsphere_backend.comment.Comment;
import com.example.devsphere_backend.comment.CommentDTO;
import com.example.devsphere_backend.post.Post;
import com.example.devsphere_backend.post.PostDTO;

public class PostMapper {
    public static PostDTO toDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setAuthor(post.getAuthor());
        dto.setContent(post.getContent());
        dto.setCodeContent(post.getCodeContent());
        dto.setImageURL(post.getImageURL());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setLikesCount(post.getLikesCount());
        dto.setCommentsCount(post.getCommentsList().size());


        return dto;
    }

    public static CommentDTO toCommentDTO(Comment comment, Integer currentUserId) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setAuthor(comment.getAuthor());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setLikes(comment.getLikes());

        if (currentUserId != null) {
            boolean likedByUser = comment.getLikesList().stream()
                    .anyMatch(like -> like.getUser().getId() == (currentUserId));
            dto.setLikedByUser(likedByUser);
        }
        return dto;
    }
}

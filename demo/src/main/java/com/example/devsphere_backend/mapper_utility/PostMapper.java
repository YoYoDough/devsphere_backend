package com.example.devsphere_backend.mapper_utility;

import com.example.devsphere_backend.comment.Comment;
import com.example.devsphere_backend.comment.CommentDTO;
import com.example.devsphere_backend.post.Post;
import com.example.devsphere_backend.post.PostDTO;

public class PostMapper {
    public static PostDTO toDTO(Post post, boolean includeComments) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setAuthor(post.getAuthor());
        dto.setContent(post.getContent());
        dto.setCodeContent(post.getCodeContent());
        dto.setImageURL(post.getImageURL());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setLikesCount(post.getLikesCount());
        dto.setCommentsCount(post.getCommentsList().size());


        if (includeComments) {
            dto.setCommentsList(
                    post.getCommentsList().stream()
                            .map(PostMapper::toCommentDTO)
                            .toList()
            );
        }

        return dto;
    }

    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setAuthor(comment.getAuthor());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setLikes(comment.getLikes());
        return dto;
    }
}

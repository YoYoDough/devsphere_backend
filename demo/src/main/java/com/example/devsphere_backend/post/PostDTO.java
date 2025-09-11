package com.example.devsphere_backend.post;

import com.example.devsphere_backend.comment.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
    private int id;
    private String author;
    private String content;
    private String imageURL;
    private String codeContent;
    private LocalDateTime createdAt;
    private int commentsCount;

    private long likesCount;

    // Optional: only include full comments when fetching a single post
    private List<CommentDTO> commentsList; // rename field to match getter/setter
    // Getters / Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public String getCodeContent() { return codeContent; }
    public void setCodeContent(String codeContent) { this.codeContent = codeContent; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public int getCommentsCount() { return commentsCount; }
    public void setCommentsCount(int commentsCount) { this.commentsCount = commentsCount; }

    public List<CommentDTO> getCommentsList() { return commentsList; }
    public void setCommentsList(List<CommentDTO> commentsList) { this.commentsList = commentsList; }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

}

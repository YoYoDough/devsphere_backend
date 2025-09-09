package com.example.devsphere_backend.post;

import com.example.devsphere_backend.comment.Comment;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;

    @Column(columnDefinition = "TEXT") // <-- allows long code content
    private String content;
    private String imageURL;

    @Column(columnDefinition = "TEXT") // <-- allows long code content
    private String codeContent;

    private LocalDateTime createdAt;
    private int likes;
    private int commentsCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentsList = new ArrayList<>();

    public Post(){}

    public Post(int id, String author, String content, String imageURL, String codeContent, LocalDateTime createdAt, int likes, int commentsCount, List<Comment> commentsList){
        this.id = id;
        this.author = author;
        this.content = content;
        this.imageURL = imageURL;
        this.codeContent = codeContent;
        this.createdAt = createdAt;
        this.likes = likes;
        this.commentsCount = commentsCount;
        this.commentsList = commentsList;
    }

    public Post(String author, String content, String imageURL, String codeContent, LocalDateTime createdAt, int likes, int commentsCount, List<Comment> commentsList){
        this.author = author;
        this.content = content;
        this.imageURL = imageURL;
        this.codeContent = codeContent;
        this.createdAt = createdAt;
        this.likes = likes;
        this.commentsCount = commentsCount;
        this.commentsList = commentsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", createdAt=" + createdAt +
                ", likes=" + likes +
                ", comments=" + commentsCount +
                ", commentsList=" + commentsList +
                '}';
    }

}

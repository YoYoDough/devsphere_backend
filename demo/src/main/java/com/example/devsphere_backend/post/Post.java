package com.example.devsphere_backend.post;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
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
    private int comments;

    public Post(){}

    public Post(int id, String author, String content, String imageURL, String codeContent, LocalDateTime createdAt, int likes, int comments){
        this.id = id;
        this.author = author;
        this.content = content;
        this.imageURL = imageURL;
        this.codeContent = codeContent;
        this.createdAt = createdAt;
        this.likes = likes;
        this.comments = comments;
    }

    public Post(String author, String content, String imageURL, String codeContent, LocalDateTime createdAt, int likes, int comments){
        this.author = author;
        this.content = content;
        this.imageURL = imageURL;
        this.codeContent = codeContent;
        this.createdAt = createdAt;
        this.likes = likes;
        this.comments = comments;
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

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
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
                ", comments=" + comments +
                '}';
    }
}

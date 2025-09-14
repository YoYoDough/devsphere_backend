package com.example.devsphere_backend.post;

import com.example.devsphere_backend.comment.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Instant;
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

    private Instant createdAt;
    private int commentsCount;

    // Likes list — one-to-many
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> commentsList = new ArrayList<>();

    public Post(){}

    public Post(int id, String author, String content, String imageURL, String codeContent, Instant createdAt, int commentsCount, List<Comment> commentsList){
        this.id = id;
        this.author = author;
        this.content = content;
        this.imageURL = imageURL;
        this.codeContent = codeContent;
        this.createdAt = createdAt;
        this.commentsCount = commentsCount;
        this.commentsList = commentsList;
    }

    public Post(String author, String content, String imageURL, String codeContent, Instant createdAt, int commentsCount, List<Comment> commentsList){
        this.author = author;
        this.content = content;
        this.imageURL = imageURL;
        this.codeContent = codeContent;
        this.createdAt = createdAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

    public Long getLikesCount() {
        if (likesList == null){
            return 0L;
        }
        Long size = (long) likesList.size();
        return size;
    }

    public void addLike(Like like) {
        likesList.add(like);
        like.setPost(this);
    }

    public List<Like> getLikesList() {
        if (likesList == null) likesList = new ArrayList<>();
        return likesList;
    }
    public void setLikesList(List<Like> likesList) { this.likesList = likesList; }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", createdAt=" + createdAt +
                ", likesCount=" + getLikesCount() +
                ", comments=" + commentsCount +
                ", commentsList=" + commentsList +
                '}';
    }

}

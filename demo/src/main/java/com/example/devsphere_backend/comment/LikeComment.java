package com.example.devsphere_backend.comment;

import com.example.devsphere_backend.post.Post;
import com.example.devsphere_backend.user.User;
import jakarta.persistence.*;

@Entity
@Table( name = "comment_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "comment_id"}) // prevent duplicates
)
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LikeComment(){}

    public LikeComment(long id, Comment comment, User user){
        this.id = id;
        this.comment = comment;
        this.user = user;
    }

    public LikeComment(Comment comment, User user){
        this.comment = comment;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LikeComment{id=" + id + ", userId=" + (user != null ? user.getId() : null) + "}";
    }
}

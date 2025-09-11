package com.example.devsphere_backend.post;

import com.example.devsphere_backend.user.User;
import jakarta.persistence.*;

@Entity
@Table( name = "post_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"}) // prevent duplicates
)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // or store a String username if no User entity

    public Like() {}

    public Like(int id, Post post, User user){
        this.id = id;
        this.post = post;
        this.user = user;
    }

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "post=" + post +
                ", user=" + user +
                '}';
    }
}

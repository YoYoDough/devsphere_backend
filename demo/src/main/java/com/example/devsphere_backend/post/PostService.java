package com.example.devsphere_backend.post;

import com.example.devsphere_backend.user.User;
import com.example.devsphere_backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Page<Post> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return postRepository.findAll(pageable);
    }

    public Post insertPost(Post post) {
        postRepository.save(post);
        return post;
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    public long getLikeCount(Long postId) {
        Post post = postRepository.getReferenceById(postId);
        return post.getLikesCount();
    }

    public void unlikePost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.getLikesList().removeIf(like -> like.getUser().getId() == (user.getId()));
        postRepository.save(post);
    }

    public void likePost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        boolean alreadyLiked = post.getLikesList().stream()
                .anyMatch(like -> like.getUser().getId() == (user.getId()));
        if (alreadyLiked){
            System.out.println("User already liked post!");
            return; // user already liked
        }

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        post.getLikesList().add(like);
        postRepository.save(post);
    }

    public boolean getDidUserLikePost(Long postId,
                                      String email) {
        Post post = postRepository.findById(postId).get();
        return post.getLikesList()
                .stream()
                .anyMatch(like -> like.getUser().getEmail().equals(email));
    }
}

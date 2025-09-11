package com.example.devsphere_backend.post;


import com.example.devsphere_backend.mapper_utility.PostMapper;
import com.example.devsphere_backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.devsphere_backend.user.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://localhost:3000") // your Next.js dev URL
public class PostController {
        private final PostService postService;

        private UserRepository userRepository;

        @Autowired
        public PostController(PostService postService, UserRepository userRepository) {
            this.postService = postService;
            this.userRepository = userRepository;
        }

        // ✅ Get paginated posts (without full comments list)
        @GetMapping("/get")
        public ResponseEntity<List<PostDTO>> getPosts(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "5") int size) {

            if (size < 1) size = 5;
            if (page < 0) page = 0;

            List<Post> posts = postService.getPosts(page, size).getContent();
            List<PostDTO> dtoList = posts.stream()
                    .map(post -> PostMapper.toDTO(post, false)) // don’t include comments
                    .toList();

            return ResponseEntity.ok(dtoList);
        }

        // ✅ Insert a new post
        @PostMapping
        public ResponseEntity<PostDTO> insertPost(@RequestBody Post post) {
            Post saved = postService.insertPost(post);
            return ResponseEntity.ok(PostMapper.toDTO(saved, false));
        }

        // ✅ Get a single post with full comments list
        @GetMapping("/{id}")
        public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(PostMapper.toDTO(post, true)); // include comments
        }

        @GetMapping("/{postId}/liked")
        public boolean didUserLikePost(@PathVariable Long postId,
                                       @RequestParam String email){
            return postService.getDidUserLikePost(postId, email);
        }

        @PostMapping("/{postId}/like")
        public ResponseEntity<?> likePost(@PathVariable Long postId,
                                          @RequestParam String email) {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            System.out.println("Liking Post");
            postService.likePost(postId, user);
            return ResponseEntity.ok("Post liked");
        }

        @DeleteMapping("/{postId}/unlike")
        public ResponseEntity<?> unlikePost(@PathVariable Long postId,
                                            @RequestParam String email) {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            System.out.println("Deleting Like");
            postService.unlikePost(postId, user);
            return ResponseEntity.ok("Post unliked");
        }

        @GetMapping("/{postId}/likes")
        public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
            long count = postService.getLikeCount(postId);
            return ResponseEntity.ok(count);
        }
}

package com.example.devsphere_backend.post;


import com.example.devsphere_backend.mapper_utility.PostMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://localhost:3000") // your Next.js dev URL
public class PostController {
        private final PostService postService;

        public PostController(PostService postService) {
            this.postService = postService;
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
                    .map(post -> PostMapper.toDTO(post, true)) // don’t include comments
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
}

package com.example.devsphere_backend.post;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://localhost:3000") // your Next.js dev URL
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/get")
    public List<Post> getPosts(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size){
        if (size < 1) size = 5; // fallback
        if (page < 0) page = 0;  // fallback
        System.out.println(postService.getPosts(page, size).getContent());
        return postService.getPosts(page, size).getContent();
    }

    @PostMapping
    public void insertPosts(@RequestBody Post post){
        postService.insertPost(post);
    }
}

package bees.HoneyTip.controller;

import bees.HoneyTip.domain.Post;
import bees.HoneyTip.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("post")
    @ResponseBody
    public Post getPost(@RequestParam("id") Long id) {
        return postService.findOne(id).get();
    }

    @GetMapping("posts")
    @ResponseBody
    public List<Post> getPostAll() {
        return postService.findPosts();
    }

    @PostMapping("post")
    @ResponseBody
    public Long post(@RequestParam("title") String title, @RequestParam("contents") String contents) {
        Post post = new Post();
        post.setTitle(title);
        post.setContents(contents);

        return postService.posting(post);
    }

    @PutMapping("post")
    @ResponseBody
    public Post post(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("contents") String contenst) {
        Long postId = postService.updatePost(id, title, contenst);
        return postService.findOne(postId).get();
    }
}

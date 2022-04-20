package bees.HoneyTip.controller;

import bees.HoneyTip.domain.Category;
import bees.HoneyTip.domain.Post;
import bees.HoneyTip.service.CategoryService;
import bees.HoneyTip.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;

    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/post/{postId}")
    @ResponseBody
    public Post getPost(@PathVariable("postId") Long postId) {
        return postService.findOne(postId).get();
    }

//    @PostMapping("post")
//    @ResponseBody
//    public Long post(@RequestParam("title") String title, @RequestParam("contents") String contents) {
//        Post post = new Post();
//        post.setTitle(title);
//        post.setContents(contents);
//
//        return postService.posting(post);
//    }

//    @PostMapping("/post")
//    @ResponseBody
//    public Long post(@RequestBody Map<String, String> param) {
//        System.out.println(param);
//        Post post = new Post();
//        String title = param.get("title");
//        String contents = param.get("contents");
//
//        post.setTitle(title);
//        post.setContents(contents);
//
//        return postService.posting(post);
//    }

    @PostMapping("/post")
    @ResponseBody
    public Long post(@RequestBody Post post) {

        // set category object
        Category category = categoryService.findOne(post.getCategory().getId())
                .orElseThrow(IllegalArgumentException::new);
        post.setCategory(category);

        return postService.posting(post);
    }

    @PutMapping("/post/{postId}")
    @ResponseBody
    public Post post(@PathVariable("postId") Long id, @RequestBody Post post) {
        // TODO 관리자 또는 게시자만 수정 가능
        String title = post.getTitle();
        String contents = post.getContents();

        Long postId = postService.updatePost(id, title, contents);
        return postService.findOne(postId).get();
    }

    @DeleteMapping("/post/{postId}")
    @ResponseBody
    public String delete(@PathVariable("postId") Long id){
        // TODO 관리자 또는 게시자만 삭제 가능
        Post post = new Post();
        post.setId(id);

        postService.delete(post);
        return "success";
    }

    @GetMapping("posts")
    @ResponseBody
    public List<Post> getPostAll() {
        return postService.findPosts();
    }

}

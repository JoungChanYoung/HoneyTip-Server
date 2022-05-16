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
        return postService.findOne(postId).orElseThrow(IllegalArgumentException::new);
    }

    @PostMapping("/post")
    @ResponseBody
    public Post post(@RequestBody Post post) {

//         set category object
        Category category = categoryService.getCategory(post.getCategory().getId());
//                 TODO 없는 경우 에러 추가,  에러 발생시 custom return 어떻게 하지?
        post.setCategory(category);

        return postService.posting(post);
    }

    @PutMapping("/post/{postId}")
    @ResponseBody
    public Post post(@PathVariable("postId") Long id, @RequestBody Post post) {
        // TODO 관리자 또는 게시자만 수정 가능 추가
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/post/{postId}")
    @ResponseBody
    public String delete(@PathVariable("postId") Long id) {
        // TODO 관리자 또는 게시자만 삭제 가능 추가
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

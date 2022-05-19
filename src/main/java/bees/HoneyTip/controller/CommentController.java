package bees.HoneyTip.controller;

import bees.HoneyTip.domain.Comment;
import bees.HoneyTip.domain.Post;
import bees.HoneyTip.service.CommentService;
import bees.HoneyTip.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/post/{postId}/comment")
    @ResponseBody
    public Comment upload(@PathVariable("postId") Long postId, @RequestBody Comment comment) {
        Post post = postService.findOne(postId).get();
        comment.setPost(post);

        return commentService.save(comment);
    }

    @GetMapping("/post/{postId}/comment")
    @ResponseBody
    public List<Comment> getComment(@PathVariable("postId") Long postId){
        return commentService.findComments(postId);
    }
}

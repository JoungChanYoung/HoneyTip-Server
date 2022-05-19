package bees.HoneyTip.service;

import bees.HoneyTip.domain.Comment;
import bees.HoneyTip.repository.CommentRepository;
import bees.HoneyTip.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){

        // set createdAt
        LocalDateTime now = LocalDateTime.now();
        comment.setCreatedAt(now);

        // set refId
        Long refId = comment.getRefId();
        if (refId == null){
            System.out.println("refId 는 ㄴnulldelEK!!!!");
            comment.setRefId(1L); // TODO 본인의 id
        }

        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> findComments(Long postId){
        return commentRepository.findByPostId(postId);
    }
}

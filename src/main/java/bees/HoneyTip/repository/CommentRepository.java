package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(@Param(value="id_post") Long postId);
}

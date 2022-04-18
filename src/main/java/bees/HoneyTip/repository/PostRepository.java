package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    Post save(Post post);
    Optional<Post> update(Long id, Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    String deleteById(Long id); // TODO 삭제 성공시 반환 타입 뭐해야되지?
    int count();
}

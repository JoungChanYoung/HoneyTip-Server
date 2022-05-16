package bees.HoneyTip.service;

import bees.HoneyTip.domain.Post;
import bees.HoneyTip.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시물 등록
    public Long posting(Post post) {

        // set time now
        LocalDateTime now = LocalDateTime.now();
        post.setCreatedAt(now);

        postRepository.save(post);
        return post.getId();
    }

    // 게시물 가져오기
    public Optional<Post> findOne(Long postId) {
        Post findPost = postRepository.findById(postId).get();

        // 조회수 증가
        findPost.setHits(findPost.getHits()+1);
        postRepository.update(postId, findPost);

        return Optional.of(findPost);
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    // 제목, 내용 업데이트
    public Optional<Post> updatePost(Long postId, Post post) {
        Post modifiedPost = postRepository.findById(postId).get();

        modifiedPost.setTitle(post.getTitle());
        modifiedPost.setContents(post.getContents());

        // 수정시간 입력
        LocalDateTime now = LocalDateTime.now();
        modifiedPost.setModifiedAt(now);

        return postRepository.update(postId, modifiedPost);

    }

    // 게시물 삭제
    public void delete(Post post) {
        postRepository.deleteById(post.getId());
    }

    // 게시물 개수 가져오기
    public int count() {
        return postRepository.count();
    }
}

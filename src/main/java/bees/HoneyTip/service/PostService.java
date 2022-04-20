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
        post.setCreatedTime(now);

        postRepository.save(post);
        return post.getId();
    }

    // 게시물 가져오기
    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    // 제목, 내용 업데이트
    public Long updatePost(Long postId, String title, String contents) {
        Post modifiedPost = findOne(postId).get();

        modifiedPost.setTitle(title);
        modifiedPost.setContents(contents);

        // 수정시간 입력
        LocalDateTime now = LocalDateTime.now();
        modifiedPost.setModifiedTime(now);

        postRepository.update(postId, modifiedPost);

        return modifiedPost.getId();
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

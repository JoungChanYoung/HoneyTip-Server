package bees.HoneyTip.service;

import bees.HoneyTip.domain.Post;
import bees.HoneyTip.repository.MemoryPostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    MemoryPostRepository memoryRepository;
    PostService postService;

    @BeforeEach
    public void beforeEach(){
        memoryRepository = new MemoryPostRepository();
        postService = new PostService(memoryRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryRepository.clearStore();
    }

    @Test
    void 게시물등록() {
        // given
        Post post = new Post();
        post.setTitle("테스트제목");

        // when
        Long saveId = postService.posting(post);

        // then
        Post findPost = postService.findOne(saveId).get();
        Assertions.assertThat(post.getTitle()).isEqualTo(findPost.getTitle());

        System.out.println(findPost.getCreatedTime());
    }

    @Test
    void 게시물수정(){
        // given
        Post post = new Post();
        post.setTitle("제목1");
        post.setContents("내용1");
        Long saveId = postService.posting(post);

        // when
        String new_title = "제목2";
        String new_contents = "내용2";
        postService.updatePost(saveId, new_title, new_contents);

        // then
        Post findPost = postService.findOne(saveId).get();
        Assertions.assertThat(findPost.getTitle()).isEqualTo(new_title);
        Assertions.assertThat(findPost.getContents()).isEqualTo(new_contents);

        Assertions.assertThat(postService.count()).isEqualTo(1);

        System.out.println(findPost.getTitle() + findPost.getContents() + findPost.getCreatedTime());
    }


    @Test
    void 게시물삭제() {
        // given
        Post post = new Post();
        Long saveId = postService.posting(post);

        // when
        postService.delete(post);

        // then
        Assertions.assertThat(postService.count()).isEqualTo(0);
    }
}
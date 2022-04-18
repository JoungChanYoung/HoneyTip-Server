package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MemoryPostRepository implements PostRepository{

    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Post save(Post post) {

        post.setId(++sequence);

        // save
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Optional<Post> update(Long id, Post post) {
        store.replace(id, post);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Post> findAll() {
        // TODO findAll 대신 Paging이 필요한거 아닌가..?
        return new ArrayList<>(store.values());
    }

    @Override
    public String deleteById(Long id) {
        store.remove(id);
        return "success";
    }

    @Override
    public int count() {
        return store.size();
    }

    public void clearStore(){
        store.clear();
    }

}

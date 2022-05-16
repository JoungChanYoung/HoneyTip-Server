//package bees.HoneyTip.repository;
//
//import bees.HoneyTip.domain.Post;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class MemoryPostRepository implements PostRepository{
//
//    private static final Map<Long, Post> store = new HashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public Optional<Post> save(Post post) {
//
//        // 기존에 키가 없으면 추가, 있으면 수정
//
//        // 수정
//        if (store.containsKey(post.getId())){
//            store.replace(post.getId(), post);
//            return Optional.ofNullable(store.get(post.getId()));
//        }
//
//        // 추가
//        else{
//            post.setId(++sequence);
//
//            store.put(post.getId(), post);
//            return Optional.of(post);
//        }
//    }
//
//    @Override
//    public Optional<Post> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    @Override
//    public List<Post> findAll() {
//        // TODO findAll 대신 Paging이 필요한거 아닌가..?
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        store.remove(id);
//    }
//
//    @Override
//    public long count() {
//        return store.size();
//    }
//
//    public void clearStore(){
//        store.clear();
//    }
//
//}

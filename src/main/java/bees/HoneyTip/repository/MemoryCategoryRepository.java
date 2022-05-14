//package bees.HoneyTip.repository;
//
//import bees.HoneyTip.domain.Category;
//import bees.HoneyTip.domain.Post;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class MemoryCategoryRepository implements CategoryRepository{
//
//    private static final Map<Long, Category> store = new HashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public Category save(Category category) {
//        category.setId(++sequence);
//
//        store.put(category.getId(), category);
//        return category;
//    }
//
//    @Override
//    public Optional<Category> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    @Override
//    public Optional<Category> findByName(String name) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Category> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public String deleteById(Long id) {
//        store.remove(id);
//        return "success";
//    }
//
//}

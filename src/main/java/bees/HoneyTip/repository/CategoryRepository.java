package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findAll();
    String deleteById(Long id);
}

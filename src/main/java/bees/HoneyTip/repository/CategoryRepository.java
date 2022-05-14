package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Category save(Category category);
//    Optional<Category> findById(Long id);
//    Optional<Category> findByName(String name);
//    List<Category> findAll();
//    String deleteById(Long id);
}

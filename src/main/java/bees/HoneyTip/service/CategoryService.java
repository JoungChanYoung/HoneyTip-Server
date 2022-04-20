package bees.HoneyTip.service;

import bees.HoneyTip.domain.Category;
import bees.HoneyTip.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Long add(Category category){

        categoryRepository.save(category);
        return category.getId();
    }

    // 게시물 가져오기
    public Optional<Category> findOne(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public String delete(Category category){
        return categoryRepository.deleteById(category.getId());
    }

    public List<Category> findCategorys(){
        return categoryRepository.findAll();
    }
}

package bees.HoneyTip.service;//package bees.HoneyTip.service;
//
//import bees.HoneyTip.domain.Category;
//import bees.HoneyTip.repository.CategoryRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CategoryService {
//    private final CategoryRepository categoryRepository;
//
//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    public Long add(Category category){
//
//        categoryRepository.save(category);
//        return category.getId();
//    }
//
//    // 게시물 가져오기
//    public Optional<Category> findOne(Long categoryId) {
//        return categoryRepository.findById(categoryId);
//    }
//
//    public String delete(Category category){
//        return categoryRepository.deleteById(category.getId());
//    }
//
//    public List<Category> findCategorys(){
//        return categoryRepository.findAll();
//    }
//}

import bees.HoneyTip.domain.Category;
import bees.HoneyTip.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(new Category()); // TODO throw error로 수정
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getCategoryAll(){
        return categoryRepository.findAll();
    }

    public Long getCategoryLength(){
        return categoryRepository.count();
    }
}

package bees.HoneyTip.controller;

import bees.HoneyTip.domain.Category;
import bees.HoneyTip.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    @ResponseBody
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/category/{categoryId}")
    @ResponseBody
    public Category getCategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/categorys")
    @ResponseBody
    public List<Category> getCategoryAll() {
        return categoryService.getCategoryAll();
    }

}

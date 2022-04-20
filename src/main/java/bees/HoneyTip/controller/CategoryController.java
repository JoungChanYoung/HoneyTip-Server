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
    public Long addCategory(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @GetMapping("/categorys")
    @ResponseBody
    public List<Category> getCategoryAll(){
        return categoryService.findCategorys();
    }

}

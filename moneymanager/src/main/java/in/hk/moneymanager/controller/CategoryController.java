package in.hk.moneymanager.controller;

import in.hk.moneymanager.dto.CategoryDto;
import in.hk.moneymanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // save the category
    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // get the category by current user
//    @GetMapping
//    public ResponseEntity<List<CategoryDto>> getCategories() {
//        return ResponseEntity.ok(categoryService.getCategoriesForCurrentUser());
//    }

    // get categories by type
//    @GetMapping("/{type}")
//    public ResponseEntity<List<CategoryDto>> getCategoriesByTypeForCurrentUser(@PathVariable String type) {
//        return ResponseEntity.ok(categoryService.getCategoriesByTypeForCurrentUser(type));
//    }

    // updating the category
//    @PutMapping("/{id}")
//    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
//        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
//    }
}
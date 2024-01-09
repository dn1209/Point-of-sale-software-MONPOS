//package com.example.demo.controller;
//
//
//import com.example.demo.model.Category;
//import com.example.demo.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("v1/category")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//    @GetMapping("{id}")
//    public ResponseEntity<Category> findCategory(@PathVariable Integer id){
//        Optional<Category> categoryOptional = categoryService.findCategory(id);
//        return categoryOptional.map(ResponseEntity::ok)
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//    @GetMapping
//    public ResponseEntity<List<Category>> findAll(){
//        return ResponseEntity.ok(categoryService.findAll());
//    }
//    @PostMapping
//    public ResponseEntity<Category> save(@RequestBody Category category){
//        return ResponseEntity.ok(categoryService.save(category));
//    }
//    @PutMapping
//    public ResponseEntity<Category> update(@RequestBody Category category) {
//        return ResponseEntity.ok(categoryService.update(category));
//    }
//
//    @DeleteMapping
//    public void delete(@RequestBody Category category) {
//        categoryService.delete(category);
//    }
//}

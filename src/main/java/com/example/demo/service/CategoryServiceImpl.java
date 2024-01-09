//package com.example.demo.service;
//
//import com.example.demo.model.Category;
//import com.example.demo.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//public class CategoryServiceImpl implements CategoryService{
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Override
//    public Optional<Category> findCategory(Integer id) {
//        return categoryRepository.findById(id);
//    }
//
//    @Override
//    public List<Category> findAll() {
//        return categoryRepository.findAll();
//    }
//
//    @Override
//    public Category save(Category category) {
//        return categoryRepository.save(category);
//    }
//
//    @Override
//    public Category update(Category category) {
//        return categoryRepository.save(category);
//    }
//
//    @Override
//    public void delete(Category category) {
//        categoryRepository.delete(category);
//
//    }
//}

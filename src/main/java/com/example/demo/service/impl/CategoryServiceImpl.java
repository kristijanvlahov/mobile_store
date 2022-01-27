package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category create(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category = new Category(name);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category category = new Category(name);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void delete(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}

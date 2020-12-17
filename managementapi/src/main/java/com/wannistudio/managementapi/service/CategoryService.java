package com.wannistudio.managementapi.service;

import com.wannistudio.managementapi.domain.Category;
import com.wannistudio.managementapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long enroll(Category category){
        validateDuplicateCategory(category);
        categoryRepository.save(category);
        return category.getId();
    }

    private void validateDuplicateCategory(Category category) {
        List<Category> categories = categoryRepository.findByName(category.getCategoryName());
        if(!categories.isEmpty()){
            throw new IllegalStateException("이미 존재하는 카테고리 입니다.");
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(Long categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Transactional
    public void update(Long id, String name, String description) {
        Category category = categoryRepository.findOne(id);
        category.setCategoryName(name);
        category.setDescription(description);
    }
}

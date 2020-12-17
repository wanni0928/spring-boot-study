package com.wannistudio.managementapi.repository;

import com.wannistudio.managementapi.domain.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category findOne(Long categoryId){
        return em.find(Category.class, categoryId);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public List<Category> findByName(String name) {
        return em.createQuery("select c from Category c where c.categoryName = :name", Category.class)
                .setParameter("name", name)
                .getResultList();
    }
}

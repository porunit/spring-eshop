package com.springeshop.repositories;

import com.springeshop.data.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findFirstById(long id);

    Category findFirstByName(String name);
}

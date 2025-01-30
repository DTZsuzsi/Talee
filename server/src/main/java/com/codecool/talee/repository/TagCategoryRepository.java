package com.codecool.talee.repository;

import com.codecool.talee.model.tags.TagCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagCategoryRepository extends JpaRepository<TagCategory, Long> {
    Optional<TagCategory> findById(long id);
}

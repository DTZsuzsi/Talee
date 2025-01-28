package com.codecool.talee.repository;

import com.codecool.talee.model.tags.TagCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagCategoryRepository extends JpaRepository<TagCategory, Long> {
    TagCategory findById(long id);

}

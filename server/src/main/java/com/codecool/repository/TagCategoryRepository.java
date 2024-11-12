package com.codecool.repository;

import com.codecool.model.tags.TagCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagCategoryRepository extends JpaRepository<TagCategory, Long> {
TagCategory findById(long id);
boolean deleteById(long id);

}

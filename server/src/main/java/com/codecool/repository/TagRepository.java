package com.codecool.repository;

import com.codecool.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findById(long id);
    List<Tag>findByTagCategoryId(long tagCategoryId);
    Tag findByName(String name);
}

package com.codecool.talee.repository;

import com.codecool.talee.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag>findByTagCategoryId(long tagCategoryId);
    Optional<Tag> findByName(String name);
}

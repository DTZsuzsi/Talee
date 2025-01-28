package com.codecool.talee.service;

import com.codecool.talee.DTO.tag.TagCategoryDTO;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.tags.TagCategory;
import com.codecool.talee.repository.TagCategoryRepository;
import com.codecool.talee.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagCategoryService {
    private final TagCategoryRepository tagCategoryRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TagCategoryService(TagCategoryRepository tagCategoryRepository, TagRepository tagRepository) {
        this.tagCategoryRepository = tagCategoryRepository;
        this.tagRepository = tagRepository;
    }
    public List<TagCategoryDTO> getAllTagCategories() {
        List<TagCategory> tagCategories = tagCategoryRepository.findAll();
        List<TagCategoryDTO> tagCategoriesDTO = new ArrayList<>();
        for (TagCategory tagCategory : tagCategories) {
            TagCategoryDTO tagCategoryDTO= new TagCategoryDTO(tagCategory.getId(), tagCategory.getName(), tagCategory.getColor());
            tagCategoriesDTO.add(tagCategoryDTO);
        }
        return tagCategoriesDTO;
    }

    public long createNewTagCategory(TagCategoryDTO tagCategoryDTO) {
        TagCategory tagCategory = new TagCategory(tagCategoryDTO.name(), tagCategoryDTO.color());
       return tagCategoryRepository.save(tagCategory).getId();
    }

    @Transactional
    public boolean deleteCategoryById(Long categoryId) {
        List<Tag> tags = tagRepository.findByTagCategoryId(categoryId);
        if (!tags.isEmpty()) {
            throw new RuntimeException("Cannot delete TagCategory with linked Tags.");
        }
        tagCategoryRepository.deleteById(categoryId);
        return true;
    }

}

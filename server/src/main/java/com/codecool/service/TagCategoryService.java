package com.codecool.service;

import com.codecool.DTO.tag.TagCategoryDTO;
import com.codecool.model.tags.TagCategory;
import com.codecool.repository.TagCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagCategoryService {
    private final TagCategoryRepository tagCategoryRepository;

    @Autowired
    public TagCategoryService(TagCategoryRepository tagCategoryRepository) {
        this.tagCategoryRepository = tagCategoryRepository;
    }
    public List<TagCategoryDTO> getAllTagCategories() {
        List<TagCategory> tagCategories = tagCategoryRepository.findAll();
        List<TagCategoryDTO> tagCategoriesDTO = new ArrayList<>();
        for (TagCategory tagCategory : tagCategories) {
            TagCategoryDTO tagCategoryDTO= new TagCategoryDTO(tagCategory.getId(), tagCategory.getName(), tagCategory.getColor(),
                    tagCategory.getCreatedAt());
            tagCategoriesDTO.add(tagCategoryDTO);
        }
        return tagCategoriesDTO;
    }

    public long createNewTagCategory(TagCategoryDTO tagCategoryDTO) {
        TagCategory tagCategory = new TagCategory(tagCategoryDTO.name(), tagCategoryDTO.color());
       return tagCategoryRepository.save(tagCategory).getId();
    }
}

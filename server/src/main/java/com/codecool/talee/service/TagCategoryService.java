package com.codecool.talee.service;

import com.codecool.talee.DTO.tag.TagCategoryDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.model.tags.TagCategory;
import com.codecool.talee.repository.TagCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagCategoryService {
    private final TagCategoryRepository tagCategoryRepository;

    @Autowired
    public TagCategoryService(TagCategoryRepository tagCategoryRepository) {
        this.tagCategoryRepository = tagCategoryRepository;
    }

    public List<TagCategoryDTO> getAllTagCategories() {
        return tagCategoryRepository.findAll().stream()
                .map(tagCategory -> new TagCategoryDTO(tagCategory.getId(), tagCategory.getName(), tagCategory.getColor()))
                .toList();
    }

    public long createNewTagCategory(TagCategoryDTO tagCategoryDTO) {
        TagCategory tagCategory = new TagCategory(tagCategoryDTO.name(), tagCategoryDTO.color());
        return tagCategoryRepository.save(tagCategory).getId();
    }

    public boolean deleteById(Long id) {
        return tagCategoryRepository.findById(id)
                .map(category -> {
                    tagCategoryRepository.delete(category);
                    return true;
                })
                .orElseThrow(() -> new EntityNotFoundException("TagCategory", id));
    }
}


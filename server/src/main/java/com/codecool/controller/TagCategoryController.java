package com.codecool.controller;

import com.codecool.DTO.tag.TagCategoryDTO;
import com.codecool.service.TagCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tagcategories")
public class TagCategoryController {
    private final TagCategoryService tagCategoryService;

    @Autowired
    public TagCategoryController(TagCategoryService tagCategoryService) {
        this.tagCategoryService = tagCategoryService;
    }
    @GetMapping
    public List<TagCategoryDTO> getAllTagCategories() {
        return tagCategoryService.getAllTagCategories();
    }

    @PostMapping
    public long addTagCategory(@RequestBody TagCategoryDTO tagCategoryDTO) {
        return tagCategoryService.createNewTagCategory(tagCategoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public boolean deleteTagCategory(@PathVariable long categoryId) {
        return tagCategoryService.deleteCategoryById(categoryId);
    }
}

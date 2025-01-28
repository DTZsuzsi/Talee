package com.codecool.talee.controller;

import com.codecool.talee.DTO.tag.TagCategoryDTO;
import com.codecool.talee.service.TagCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Long> addTagCategory(@RequestBody TagCategoryDTO tagCategoryDTO) {
        return new ResponseEntity<>(tagCategoryService.createNewTagCategory(tagCategoryDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public Boolean deleteTagCategory(@PathVariable long categoryId) {
        return tagCategoryService.deleteCategoryById(categoryId);
    }
}

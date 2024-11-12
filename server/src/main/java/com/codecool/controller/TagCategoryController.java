package com.codecool.controller;

import com.codecool.DTO.tagDTO.TagCategoryDTO;
import com.codecool.service.TagCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

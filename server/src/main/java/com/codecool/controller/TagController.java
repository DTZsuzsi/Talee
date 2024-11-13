package com.codecool.controller;

import com.codecool.DTO.tagDTO.TagDTO;
import com.codecool.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

   @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

@GetMapping
    public List<TagDTO> getTags() {
       return tagService.getAllTags();
}

@PostMapping
    public long addTag(@RequestBody TagDTO tagDTO) {
       return tagService.addTag(tagDTO);
}
}




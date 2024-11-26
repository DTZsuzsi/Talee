package com.codecool.controller;

import com.codecool.DTO.tag.TaginFrontendDTO;
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
    public List<TaginFrontendDTO> getTags() {
        return tagService.getAllTags();
    }

    @PostMapping
    public long addTag(@RequestBody TaginFrontendDTO taginFrontendDTO) {
        return tagService.addTag(taginFrontendDTO);
    }

    @DeleteMapping("/{tagId}")
    public boolean deleteById(@PathVariable long tagId) {
        return tagService.deleteById(tagId);
    }

}





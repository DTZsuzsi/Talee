package com.codecool.talee.controller;

import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Long> addTag(@RequestBody TagDTO taginFrontendDTO) {
        return new ResponseEntity<>(tagService.addTag(taginFrontendDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tagId}")
    public boolean deleteById(@PathVariable long tagId) {
        return tagService.deleteById(tagId);
    }

}





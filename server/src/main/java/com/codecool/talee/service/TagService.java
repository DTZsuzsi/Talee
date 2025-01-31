package com.codecool.talee.service;

import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.mapper.TagMapper;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.tags.TagCategory;
import com.codecool.talee.repository.TagCategoryRepository;
import com.codecool.talee.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagCategoryRepository tagCategoryRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagService(TagRepository tagRepository, TagCategoryRepository tagCategoryRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::tagToTagDTO)
                .distinct()
                .toList();
    }

    public long addTag(TagDTO tagDTO) {
        TagCategory tagCategory = tagCategoryRepository.findById(tagDTO.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("TagCategory", tagDTO.categoryId()));

        Tag newTag = new Tag(tagDTO.name(), tagCategory);
        return tagRepository.save(newTag).getId();
    }

    public boolean deleteById(long id) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.delete(tag);
                    return true;
                })
                .orElseThrow(() -> new EntityNotFoundException("Tag", id));
    }
}

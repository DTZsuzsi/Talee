package com.codecool.service;

import com.codecool.DTO.tag.TagDTO;
import com.codecool.model.tags.Tag;
import com.codecool.model.tags.TagCategory;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagCategoryRepository tagCategoryRepository;
    @Autowired
    public TagService(TagRepository tagRepository, TagCategoryRepository tagCategoryRepository) {
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;
    }
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> {
                    Long categoryId = (tag.getCategory() != null) ? tag.getCategory().getId() : 1;
                    return new TagDTO(tag.getId(), tag.getName(), categoryId, tag.getCreatedAt());
                })
                .collect(Collectors.toList());
    }


    public long addTag(TagDTO tagDTO) {
       TagCategory tagCategory=tagCategoryRepository.findById(tagDTO.categoryId());
        Tag newTag=new Tag(tagDTO.name(), tagCategory);
        return tagRepository.save(newTag).getId();
    }
}

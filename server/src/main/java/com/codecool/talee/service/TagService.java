package com.codecool.talee.service;

import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.mapper.TagMapper;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.tags.TagCategory;
import com.codecool.talee.repository.TagCategoryRepository;
import com.codecool.talee.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagCategoryRepository tagCategoryRepository;
    private final TagMapper tagMapper = TagMapper.INSTANCE;

    @Autowired
    public TagService(TagRepository tagRepository, TagCategoryRepository tagCategoryRepository) {
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;
    }

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::tagToTagDTO)
                .collect(Collectors.toList());
    }

    public long addTag(TagDTO tagInFrontendDTO) {
       TagCategory tagCategory=tagCategoryRepository.findById(tagInFrontendDTO.categoryId());
        Tag newTag=new Tag(tagInFrontendDTO.name(), tagCategory);
        return tagRepository.save(newTag).getId();
    }

    @Transactional
    public boolean deleteById(long id) {
        tagRepository.deleteById(id);
        return true;

    }

}

package com.codecool.service;

import com.codecool.DTO.tag.TaginEventDTO;
import com.codecool.mapper.TagMapper;
import com.codecool.model.tags.Tag;
import com.codecool.model.tags.TagCategory;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagCategoryRepository tagCategoryRepository;
    private final TagMapper tagMapper=TagMapper.INSTANCE;
    @Autowired
    public TagService(TagRepository tagRepository, TagCategoryRepository tagCategoryRepository) {
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;
    }
    public List<TaginEventDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::tagToTaginEventDTO)
                .collect(Collectors.toList());
    }


    public long addTag(TaginEventDTO taginEventDTO) {
       TagCategory tagCategory=tagCategoryRepository.findById(taginEventDTO.categoryId());
        Tag newTag=new Tag(taginEventDTO.name(), tagCategory);
        return tagRepository.save(newTag).getId();
    }

    @Transactional
    public boolean deleteById(long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

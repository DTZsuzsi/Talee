package com.codecool.service;

import com.codecool.DTO.tagDTO.TagDTO;
import com.codecool.model.tags.Tag;
import com.codecool.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        List<TagDTO> tagDTOS = new ArrayList<>();
        for (Tag tag : tags) {
            TagDTO tagDTO=new TagDTO(tag.getId(), tag.getName(), tag.getCategory(), tag.getCreatedAt());
            tagDTOS.add(tagDTO);
        }
        return tagDTOS;
    }
}

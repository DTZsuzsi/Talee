package com.codecool.talee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.mapper.TagMapper;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.tags.TagCategory;
import com.codecool.talee.repository.TagCategoryRepository;
import com.codecool.talee.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {
    @Mock
    private TagRepository tagRepository;

    @Mock
    private TagCategoryRepository tagCategoryRepository;

    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private TagService tagService;

    private Tag mockTag;
    private TagDTO mockTagDTO;

    @BeforeEach
    void setUp() {
        mockTag = new Tag("TestTag", null);
        mockTagDTO = new TagDTO(1L, "TestTag", 1L, Color.BLUE);
    }

    @Test
    void getAllTags_ReturnsTagDTOList() {
        when(tagRepository.findAll()).thenReturn(List.of(mockTag));
        when(tagMapper.tagToTagDTO(mockTag)).thenReturn(mockTagDTO);

        List<TagDTO> tags = tagService.getAllTags();

        assertEquals(1, tags.size());
        verify(tagRepository).findAll();
    }

    @Test
    void addTag_WhenCategoryExists_ReturnsTagId() {
        TagCategory tagCategory = new TagCategory("TestCategory", Color.BLUE);
        when(tagCategoryRepository.findById(1L)).thenReturn(Optional.of(tagCategory));
        when(tagRepository.save(any(Tag.class))).thenReturn(mockTag);

        long tagId = tagService.addTag(mockTagDTO);

        assertEquals(mockTag.getId(), tagId);
        verify(tagRepository).save(any(Tag.class));
    }

    @Test
    void deleteById_WhenTagExists_ReturnsTrue() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(mockTag));

        assertTrue(tagService.deleteById(1L));
        verify(tagRepository).delete(mockTag);
    }
}
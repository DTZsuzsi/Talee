package com.codecool.talee.service;

import com.codecool.talee.DTO.tag.TagCategoryDTO;
import com.codecool.talee.model.tags.TagCategory;
import com.codecool.talee.repository.TagCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagCategoryServiceTest {
    @Mock
    private TagCategoryRepository tagCategoryRepository;

    @InjectMocks
    private TagCategoryService tagCategoryService;

    private TagCategory mockTagCategory;
    private TagCategoryDTO mockTagCategoryDTO;

    @BeforeEach
    void setUp() {
        mockTagCategory = new TagCategory("TestCategory", "black");
        mockTagCategoryDTO = new TagCategoryDTO(1L, "TestCategory", "black");
    }

    @Test
    void getAllTagCategories_ReturnsCategoryDTOList() {
        when(tagCategoryRepository.findAll()).thenReturn(List.of(mockTagCategory));

        List<TagCategoryDTO> categories = tagCategoryService.getAllTagCategories();

        assertEquals(1, categories.size());
        verify(tagCategoryRepository).findAll();
    }

    @Test
    void createNewTagCategory_ReturnsCategoryId() {
        when(tagCategoryRepository.save(any(TagCategory.class))).thenReturn(mockTagCategory);

        long categoryId = tagCategoryService.createNewTagCategory(mockTagCategoryDTO);

        assertEquals(mockTagCategory.getId(), categoryId);
        verify(tagCategoryRepository).save(any(TagCategory.class));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT) // This makes stubbing less strict
    void deleteCategoryById_WhenTagExists_ReturnsTrue() {
        when(tagCategoryRepository.findById(any())).thenReturn(Optional.of(mockTagCategory));

        assertTrue(tagCategoryService.deleteById(1L));
        verify(tagCategoryRepository).delete(mockTagCategory);
    }
}
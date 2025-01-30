package com.codecool.talee.controller;

import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "user", roles = {"USER"})
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    String newTag = """
            {
                "id": 0,
                "name": "Sample Tag",
                "color": {
                    "r": 255,
                    "g": 255,
                    "b": 255
                }
            }
            """;

    @Test
    void getTags_returns200AndEmptyList() throws Exception {
        when(tagService.getAllTags()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void addTag_returns201() throws Exception {
        when(tagService.addTag(any(TagDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/api/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTag))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteById_returns200() throws Exception {
        when(tagService.deleteById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/tags/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}

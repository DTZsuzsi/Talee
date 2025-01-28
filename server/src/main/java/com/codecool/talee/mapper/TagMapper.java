package com.codecool.talee.mapper;

import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.model.tags.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(source = "tagCategory.id", target = "categoryId")
    @Mapping(source = "tagCategory.color", target = "color")
    TagDTO tagToTagDTO(Tag tag);

    @Mapping(target = "tagCategory.id", source = "categoryId")
    @Mapping(target = "tagCategory.color", source = "color")
    Tag tagDTOToTag(TagDTO tagDTO);
}





package com.codecool.mapper;

import com.codecool.DTO.tag.TagDTO;
import com.codecool.model.tags.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper//(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
TagDTO tagToTagDTO(Tag tag);
Tag tagDTOToTag(TagDTO tagDTO);
}





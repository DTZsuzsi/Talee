package com.codecool.mapper;

import com.codecool.DTO.tag.TagDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.model.tags.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper//(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDTO tagToTagDTO(Tag tag);

    Tag tagDTOToTag(TagDTO tagDTO);

    @Mapping(source = "tagCategory.id", target = "categoryId")
    @Mapping(source = "tagCategory.color", target = "color")
    TaginFrontendDTO tagToTaginFrontendDTO(Tag tag);

    @Mapping(target = "tagCategory.id", source = "categoryId")
    @Mapping(target = "tagCategory.color", source = "color")
    Tag tagInFrontendDTOsToTag(TaginFrontendDTO taginFrontendDTO);
}





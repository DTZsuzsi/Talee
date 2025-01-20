package com.codecool.mapper;

import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.model.tags.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);


    @Mapping(source = "tagCategory.id", target = "categoryId")
    @Mapping(source = "tagCategory.color", target = "color")
    TaginFrontendDTO tagToTaginFrontendDTO(Tag tag);

    @Mapping(target = "tagCategory.id", source = "categoryId")
    @Mapping(target = "tagCategory.color", source = "color")
    Tag tagInFrontendDTOsToTag(TaginFrontendDTO taginFrontendDTO);
}





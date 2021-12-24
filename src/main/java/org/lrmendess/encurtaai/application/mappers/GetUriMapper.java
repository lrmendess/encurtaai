package org.lrmendess.encurtaai.application.mappers;

import org.lrmendess.encurtaai.application.dtos.GetUriOutput;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface GetUriMapper {
    
    GetUriOutput mapToDto(Uri uri);

}

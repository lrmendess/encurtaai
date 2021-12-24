package org.lrmendess.encurtaai.application.mappers;

import org.lrmendess.encurtaai.application.dtos.CreateUriInput;
import org.lrmendess.encurtaai.application.dtos.CreateUriOutput;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CreateUriMapper {

    Uri mapToEntity(CreateUriInput from);
    CreateUriOutput mapToDto(Uri from);

}

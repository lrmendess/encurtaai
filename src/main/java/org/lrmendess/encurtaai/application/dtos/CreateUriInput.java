package org.lrmendess.encurtaai.application.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUriInput {

    @URL
    @NotEmpty
    private String originalUri;
    
}

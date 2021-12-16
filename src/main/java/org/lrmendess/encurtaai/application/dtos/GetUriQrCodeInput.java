package org.lrmendess.encurtaai.application.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUriQrCodeInput {
    
    private long id;

    @URL
    @NotEmpty
    private String baseUri;

}

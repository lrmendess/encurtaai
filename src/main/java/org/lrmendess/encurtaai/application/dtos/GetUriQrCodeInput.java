package org.lrmendess.encurtaai.application.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUriQrCodeInput {
    
    private long id;

    @URL
    @NotEmpty
    private String baseUri;

}

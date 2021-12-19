package org.lrmendess.encurtaai.application.dtos;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUriOutput {

    private Long id;

    private String originalUri;
    
    private String shortPath;

    private ZonedDateTime createdAt;
    
    private ZonedDateTime updatedAt;
    
    private boolean deleted;
    
}

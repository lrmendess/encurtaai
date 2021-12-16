package org.lrmendess.encurtaai.application.dtos;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUriOutput {

    private int id;
    
    private String originalUri;
    
    private String shortPath;

    private boolean deleted;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
    
}

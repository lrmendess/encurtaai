package org.lrmendess.encurtaai.application.dtos;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUriOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String originalUri;
    
    private String shortPath;

    private ZonedDateTime createdAt;
    
    private ZonedDateTime updatedAt;

    private boolean deleted;
    
}

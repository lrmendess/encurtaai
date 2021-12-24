package org.lrmendess.encurtaai.application.usecases;

import java.util.Optional;

import org.lrmendess.encurtaai.application.dtos.GetUriOutput;
import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.application.mappers.GetUriMapper;
import org.lrmendess.encurtaai.application.interfaces.GetUri;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUriImpl implements GetUri {

    @Autowired
    private UriRepository uriRepository;

    @Autowired
    private GetUriMapper getUriMapper;

    @Override
    public GetUriOutput handle(Long id) {
        Optional<Uri> uri = uriRepository.findById(id);
        
        if (uri.isEmpty()) {
            return null;
        }

        return getUriMapper.mapToDto(uri.get());
    }
    
}

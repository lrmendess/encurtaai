package org.lrmendess.encurtaai.application.usecases;

import java.util.Optional;

import org.lrmendess.encurtaai.application.interfaces.DeleteUri;
import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUriImpl implements DeleteUri {

    @Autowired
    private UriRepository uriRepository;

    @Override
    public Void handle(Long input) {
        Optional<Uri> optional = uriRepository.findById(input);

        if (optional.isPresent()) {
            Uri uri = optional.get();
            uri.setDeleted(true);
            
            uriRepository.save(uri);
        }
        
        return null;
    }
    
}

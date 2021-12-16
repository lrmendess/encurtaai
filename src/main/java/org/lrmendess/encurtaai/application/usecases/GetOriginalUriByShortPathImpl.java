package org.lrmendess.encurtaai.application.usecases;

import java.util.Optional;

import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.application.interfaces.GetOriginalUriByShortPath;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOriginalUriByShortPathImpl implements GetOriginalUriByShortPath {

    @Autowired
    private UriRepository uriRepository;

    @Override
    public String handle(String input) {
        Optional<Uri> optional = uriRepository.findByShortPath(input);

        if (optional.isEmpty()) {
            return null;
        }

        Uri uri = optional.get();

        if (uri.isDeleted()) {
            return null;
        }

        return uri.getOriginalUri();
    }
    
}

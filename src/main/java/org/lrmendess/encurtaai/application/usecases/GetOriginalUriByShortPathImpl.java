package org.lrmendess.encurtaai.application.usecases;

import java.util.Optional;

import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.application.interfaces.GetOriginalUriByShortPath;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GetOriginalUriByShortPathImpl implements GetOriginalUriByShortPath {

    @Autowired
    private UriRepository uriRepository;

    @Override
    @Cacheable(cacheNames = "RedirectUri", key = "#shortPath")
    public String handle(String shortPath) {
        Optional<Uri> optional = uriRepository.findByShortPath(shortPath);

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

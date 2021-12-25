package org.lrmendess.encurtaai.application.usecases;

import java.util.Optional;

import org.lrmendess.encurtaai.application.interfaces.DeleteUri;
import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class DeleteUriImpl implements DeleteUri {

    @Autowired
    private UriRepository uriRepository;

    @Override
    @Caching(evict = {
        @CacheEvict(cacheNames = "Uri", key = "#id"),
        @CacheEvict(cacheNames = "UriQrCode", key = "#id"),
        @CacheEvict(cacheNames = "RedirectUri", key = "T(org.lrmendess.encurtaai.application.utils.Base62Converter).fromBase10(#id)")
    })
    public Void handle(Long id) {
        Optional<Uri> optional = uriRepository.findById(id);

        optional.ifPresent((uri) -> {
            uri.setDeleted(true);
            uriRepository.save(uri);
        });

        return null;
    }
    
}

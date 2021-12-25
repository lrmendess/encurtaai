package org.lrmendess.encurtaai.application.usecases;

import javax.transaction.Transactional;

import org.lrmendess.encurtaai.application.dtos.CreateUriInput;
import org.lrmendess.encurtaai.application.dtos.CreateUriOutput;
import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.application.mappers.CreateUriMapper;
import org.lrmendess.encurtaai.application.utils.Base62Converter;
import org.lrmendess.encurtaai.application.interfaces.CreateUri;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CreateUriImpl implements CreateUri {
    
    @Autowired
    private UriRepository uriRepository;

    @Autowired
    private CreateUriMapper createUriMapper;

    @Override
    @Transactional
    @Caching(evict = {
        @CacheEvict(cacheNames = "Uri", key = "#result.id"),
        @CacheEvict(cacheNames = "UriQrCode", key = "#result.id"),
        @CacheEvict(cacheNames = "RedirectUri", key = "#result.shortPath")
    })
    public CreateUriOutput handle(CreateUriInput input) {
        Uri uri = createUriMapper.mapToEntity(input);

        uriRepository.save(uri);

        String shortPath = Base62Converter.fromBase10(uri.getId());

        uri.setShortPath(shortPath);

        uriRepository.save(uri);

        return createUriMapper.mapToDto(uri);
    }

}

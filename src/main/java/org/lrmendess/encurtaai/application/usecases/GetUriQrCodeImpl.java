package org.lrmendess.encurtaai.application.usecases;

import java.util.Optional;

import org.lrmendess.encurtaai.application.dtos.GetUriQrCodeInput;
import org.lrmendess.encurtaai.application.interfaces.GetUriQrCode;
import org.lrmendess.encurtaai.application.interfaces.UriRepository;
import org.lrmendess.encurtaai.application.utils.QrCodeGenerator;
import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GetUriQrCodeImpl implements GetUriQrCode {

    @Autowired
    private UriRepository uriRepository;

    @Override
    @Cacheable(cacheNames = "UriQrCode", key = "#input.id")
    public String handle(GetUriQrCodeInput input) {
        Optional<Uri> uri = uriRepository.findById(input.getId());

        if (uri.isEmpty()) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        builder.append(input.getBaseUri());
        builder.append("/");
        builder.append(uri.get().getShortPath());

        return QrCodeGenerator.generateQrCodeBase64Png(builder.toString());
    }
    
}

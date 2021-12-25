package org.lrmendess.encurtaai.webapi.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.lrmendess.encurtaai.application.dtos.CreateUriInput;
import org.lrmendess.encurtaai.application.dtos.CreateUriOutput;
import org.lrmendess.encurtaai.application.dtos.GetUriOutput;
import org.lrmendess.encurtaai.application.dtos.GetUriQrCodeInput;
import org.lrmendess.encurtaai.application.interfaces.CreateUri;
import org.lrmendess.encurtaai.application.interfaces.DeleteUri;
import org.lrmendess.encurtaai.application.interfaces.GetUri;
import org.lrmendess.encurtaai.application.interfaces.GetUriQrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/uris")
public class UrisController {

    @Autowired
    private CreateUri createUri;

    @Autowired
    private GetUri getUri;

    @Autowired
    private GetUriQrCode getUriQrCode;

    @Autowired
    private DeleteUri deleteUri;

    @PostMapping
    public ResponseEntity<CreateUriOutput> createUri(@RequestBody @Valid CreateUriInput input, HttpServletRequest req) {
        CreateUriOutput output = createUri.handle(input);

        URI getUriEndpoint = ServletUriComponentsBuilder.fromRequest(req)
            .path("/{id}").buildAndExpand(output.getId())
            .toUri();
        
        return ResponseEntity.created(getUriEndpoint).body(output);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUriOutput> getUri(@PathVariable long id) {
        GetUriOutput output = getUri.handle(id);

        if (output == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(output);
    }

    @GetMapping("{id}/qrcode")
    public ResponseEntity<String> getUriQrCode(@PathVariable long id, HttpServletRequest req) {
        String baseUri = ServletUriComponentsBuilder.fromRequest(req)
            .replacePath(null).build()
            .toString();
        
        GetUriQrCodeInput getUriQrCodeInput = new GetUriQrCodeInput(id, baseUri);
        
        String qrCode = getUriQrCode.handle(getUriQrCodeInput);

        if (qrCode == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(qrCode);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUri(@PathVariable long id) {
        deleteUri.handle(id);
        return ResponseEntity.noContent().build();
    }

}

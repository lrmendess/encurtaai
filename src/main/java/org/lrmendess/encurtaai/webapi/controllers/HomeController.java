package org.lrmendess.encurtaai.webapi.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.lrmendess.encurtaai.application.interfaces.GetOriginalUriByShortPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private GetOriginalUriByShortPath getOriginalUriByShortPath;

    @GetMapping("{shortPath}")
    public ResponseEntity<Void> redirect(@PathVariable String shortPath) throws URISyntaxException {
        String originalUri = getOriginalUriByShortPath.handle(shortPath);

        if (originalUri == null) {
            return ResponseEntity.notFound().build();
        }

        URI uri = new URI(originalUri);

        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }

}

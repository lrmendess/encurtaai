package org.lrmendess.encurtaai.webapi.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.lrmendess.encurtaai.application.interfaces.GetOriginalUriByShortPath;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void redirect(@PathVariable String shortPath, HttpServletResponse response) throws IOException {
        String originalUri = getOriginalUriByShortPath.handle(shortPath);

        if (originalUri == null) {
            response.setStatus(404);
        } else {
            response.sendRedirect(originalUri);
        }
    }

}

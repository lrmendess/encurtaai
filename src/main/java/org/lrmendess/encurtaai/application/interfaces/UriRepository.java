package org.lrmendess.encurtaai.application.interfaces;

import java.util.Optional;

import org.lrmendess.encurtaai.domain.entities.Uri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UriRepository extends JpaRepository<Uri, Long> {
    
    Optional<Uri> findByShortPath(String shortPath);

}

package com.ninos.service.endpoints;

import com.ninos.service.endpoints.documentation.CacheEndpointDocumentation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * endpoint for to manage the caches of the service
 */
@RestController
@RequestMapping(
        path = "/api/v1/caches",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CacheEndpoint implements CacheEndpointDocumentation {

    @Override
    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @CacheEvict(cacheNames = {"iin_cache", "clearing_costs_cache"}, allEntries = true)
    public ResponseEntity<Void> deleteCaches() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.ninos.service.endpoints.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface CacheEndpointDocumentation {

    @Operation(summary = "Clear caches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The clearing cost was deleted"),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<Void> deleteCaches();
}

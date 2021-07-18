package com.ninos.service.endpoints.documentation;

import com.ninos.service.model.requests.CardCostRequest;
import com.ninos.service.model.requests.CreateClearingCostRequest;
import com.ninos.service.model.requests.UpdateClearingCostRequest;
import com.ninos.service.model.responses.ClearingCostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClearingCostEndpointDocumentation {

/*
 * postinterview-project
 */
    @Operation(summary = "Get clearing costs for a card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return clearing cost", content = @Content(schema = @Schema(implementation = ClearingCostResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<ClearingCostResponse> postCardCost(CardCostRequest cardCostRequest);

/*
 * getClearingCosts
 */
    @Operation(summary = "Get all the clearing costs (cost per country) of the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return clearing costs mapping", content = @Content(schema = @Schema(implementation = ClearingCostResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<List<ClearingCostResponse>> getClearingCosts();

/*
 * getClearingCostsForCountry
 */
    @Operation(summary = "Get the clearing costs for the provided country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the clearing costs for this country", content = @Content(schema = @Schema(implementation = ClearingCostResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<ClearingCostResponse> getClearingCostsForCountry(String country);

/*
 * createClearing
 */
    @Operation(summary = "Create a new clearing cost for a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The clearing cost was created"),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<Void> createClearingCost(CreateClearingCostRequest createClearingCostRequest);

/*
 * deleteClearingCost
 */
    @Operation(summary = "Delete the clearing cost for a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The clearing cost was deleted"),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<Void> deleteClearingCost(String country);

/*
 * updateClearingCost
 */
    @Operation(summary = "Update the clearing cost for a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The clearing cost was updated", content = @Content(schema = @Schema(implementation = ClearingCostResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not authorized to access this endpoint"),
            @ApiResponse(responseCode = "403", description = "User not allowed to perform this action")
    })
    ResponseEntity<ClearingCostResponse> updateClearingCost(
            String country,
            UpdateClearingCostRequest updateClearingCostRequest
    );
}

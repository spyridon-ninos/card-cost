package com.ninos.service.endpoints;

import com.ninos.core.business.api.CostApi;
import com.ninos.core.business.logic.CardUtils;
import com.ninos.core.business.model.ClearingCost;
import com.ninos.service.endpoints.documentation.ClearingCostEndpointDocumentation;
import com.ninos.service.model.requests.CardCostRequest;
import com.ninos.service.model.requests.CreateClearingCostRequest;
import com.ninos.service.model.requests.UpdateClearingCostRequest;
import com.ninos.service.model.responses.ClearingCostResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * endpoints related to the clearing costs of the system (CRUD and information retrieval)
 */
@RestController
@RequestMapping(
        path = "/api/v1/clearings",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ClearingCostEndpoint implements ClearingCostEndpointDocumentation {

    private final Logger logger = LoggerFactory.getLogger(ClearingCostEndpoint.class);

    private final CostApi costApi;

    @Autowired
    public ClearingCostEndpoint(
            CostApi costApi
    ) {
        this.costApi = costApi;
    }

    @Override
    @PostMapping(path = "/payment-cards-cost")
    public ResponseEntity<ClearingCostResponse> postCardCost(@RequestBody @Valid CardCostRequest cardCostRequest) {

        var iin = CardUtils.getIinFromCard(cardCostRequest.getCardNumber());
        logger.debug("Got cost clearing request for IIN: {}", iin);

        var costClearing = costApi.getCost(iin);

        if (costClearing.isPresent()) {

            var clearingCostResponse = new ClearingCostResponse(costClearing.get());
            logger.debug("Returning: {}", clearingCostResponse);

            return ResponseEntity.ok(clearingCostResponse);

        } else {

            logger.error("Could not identify clearing costs for IIN: {}", iin);
            var clearingCostResponse = new ClearingCostResponse();
            clearingCostResponse.setCountry("N/A");
            clearingCostResponse.setCost(new BigDecimal("0"));

            return ResponseEntity.notFound()
                                 .build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClearingCostResponse>> getClearingCosts() {

        var response = costApi.getAvailableCosts()
                              .stream()
                              .map(ClearingCostResponse::new)
                              .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/{country}")
    public ResponseEntity<ClearingCostResponse> getClearingCostsForCountry(
            @PathVariable("country") @NotEmpty final String country
    ) {
        var clearingCost = costApi.getCostForCountry(country);
        var clearingCostResponse = new ClearingCostResponse(clearingCost);

        return ResponseEntity.ok(clearingCostResponse);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createClearingCost(@RequestBody @Valid CreateClearingCostRequest createClearingCostRequest) {

        logger.debug("Creating clearing request with the following details: {}", createClearingCostRequest);

        costApi.createClearing(createClearingCostRequest.toModel());

        URI uri = URI.create("/api/v1/clearings/" + createClearingCostRequest.getCountry());
        return ResponseEntity.created(uri)
                             .build();
    }

    @Override
    @DeleteMapping(path = "/{country}")
    public ResponseEntity<Void> deleteClearingCost(@PathVariable("country") @NotEmpty String country) {

        logger.debug("Requested to delete the clearing cost for {}", country);

        costApi.deleteClearing(country);

        return ResponseEntity.noContent()
                             .build();
    }

    @Override
    @PutMapping(
            path = "/{country}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClearingCostResponse> updateClearingCost(
            @PathVariable("country") @NotEmpty String country,
            @RequestBody @Valid UpdateClearingCostRequest updateClearingCostRequest
    ) {

        logger.debug("Requested to update the clearing cost for {} to {}", country, updateClearingCostRequest.getCost());

        var updatedClearingCost = costApi.updateClearingCost(new ClearingCost(country, updateClearingCostRequest.getCost()));
        logger.debug("Updated the clearing cost: {}", updatedClearingCost);

        return ResponseEntity.ok(new ClearingCostResponse(updatedClearingCost));
    }
}

package com.ninos.core.business.logic;

import com.ninos.core.business.model.ClearingCost;
import com.ninos.core.business.spi.ClearingCostRepository;
import com.ninos.core.business.spi.IssuerIdentificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * the clearing cost service - responsible to manage clearing costs in our system
 */
@Service
public final class ClearingCostService {

    private final Logger logger = LoggerFactory.getLogger(ClearingCostService.class);

    private final IssuerIdentificationService issuerIdentificationService;
    private final ClearingCostRepository clearingCostRepository;

    @Autowired
    public ClearingCostService(
            IssuerIdentificationService issuerIdentificationService,
            ClearingCostRepository clearingCostRepository
    ) {
        this.issuerIdentificationService = issuerIdentificationService;
        this.clearingCostRepository = clearingCostRepository;
    }

    public Optional<ClearingCost> getCost(String iin) {

        var country = issuerIdentificationService.identifyCountry(iin);

        if (country.isPresent()) {

            logger.debug("IIN {} maps to country: {}", iin, country.get());

            var clearingCost = clearingCostRepository.getCostForCountry(country.get());

            logger.debug("Returning cleaning: {}", clearingCost);

            return Optional.of(clearingCost); // we don't expect costClearing to be null

        }

        return Optional.empty();
    }
}

package com.ninos.core.business.api;

import com.ninos.core.business.logic.ClearingCostService;
import com.ninos.core.business.model.ClearingCost;
import com.ninos.core.business.spi.ClearingCostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * the business layer API for the clearing cost service & repository
 */
@Component
public class CostApi {

    private final Logger logger = LoggerFactory.getLogger(CostApi.class);

    private final ClearingCostService clearingCostService;
    private final ClearingCostRepository clearingCostRepository;

    @Autowired
    public CostApi(
            ClearingCostService clearingCostService,
            ClearingCostRepository clearingCostRepository
    ) {
        this.clearingCostService = clearingCostService;
        this.clearingCostRepository = clearingCostRepository;
    }

    @Cacheable(cacheNames = "iin_cache")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Optional<ClearingCost> getCost(String iin) {

        logger.debug("Fetching clearing cost for IIN: {}", iin);

        var clearingCost = clearingCostService.getCost(iin);

        logger.debug("Returning: {}", clearingCost.isPresent() ? clearingCost.get() : "N/A");

        return clearingCost;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ClearingCost getCostForCountry(String country) {

        logger.debug("Fetching clearing cost for country: {}", country);

        var clearingCost = clearingCostRepository.getCostForCountry(country);

        logger.debug("Returning: {}", clearingCost);

        return clearingCost;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    public void createClearing(ClearingCost clearingCost) {

        logger.debug("Creating clearing cost: {}", clearingCost);

        clearingCostRepository.saveClearingCost(clearingCost);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteClearing(String country) {
        clearingCostRepository.deleteByCountry(country);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ClearingCost updateClearingCost(ClearingCost clearingCost) {

        var updatedClearingCost = clearingCostRepository.updateClearingCost(clearingCost);

        logger.info("Updated clearing cost: {}", updatedClearingCost);

        return updatedClearingCost;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ClearingCost> getAvailableCosts() {

        logger.debug("Fetching all available clearing costs");

        var clearingCosts = clearingCostRepository.getAvailableClearingCosts();

        logger.debug("Returning: {}", clearingCosts);

        return clearingCosts;
    }
}

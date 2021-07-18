package com.ninos.core.business.spi;

import com.ninos.core.business.model.ClearingCost;

import java.util.List;

/**
 * SPI for the clearing cost repository
 */
public interface ClearingCostRepository {
    ClearingCost getCostForCountry(String country);
    ClearingCost saveClearingCost(ClearingCost clearingCost);
    void deleteByCountry(String country);
    ClearingCost updateClearingCost(ClearingCost clearingCost);
    List<ClearingCost> getAvailableClearingCosts();
}

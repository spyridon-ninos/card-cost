package com.ninos.core.business.logic;

import com.ninos.core.business.model.ClearingCost;
import com.ninos.core.business.spi.ClearingCostRepository;
import com.ninos.core.business.spi.IssuerIdentificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClearingCostServiceTest {

    private ClearingCostService clearingCostService;
    private IssuerIdentificationService issuerIdentificationService;
    private ClearingCostRepository clearingCostRepository;

    private final String AMERICAN_EXPRESS = "378282246310005"; // taken from: https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm

    private final Optional<String> grOptional = Optional.of("GR");
    private final ClearingCost grClearingCost = new ClearingCost("GR", new BigDecimal("10"));

    @BeforeEach
    public void beforeEach() {
        issuerIdentificationService = mock(IssuerIdentificationService.class);
        when(issuerIdentificationService.identifyCountry(anyString())).thenReturn(grOptional);

        clearingCostRepository = mock(ClearingCostRepository.class);
        when(clearingCostRepository.getCostForCountry(anyString())).thenReturn(grClearingCost);

        clearingCostService = new ClearingCostService(issuerIdentificationService, clearingCostRepository);
    }

    @Test
    @DisplayName("Get cost for a valid card")
    public void getCostValidCard() {
        Optional<ClearingCost> actual = clearingCostService.getCost(CardUtils.getIinFromCard(AMERICAN_EXPRESS));
        Optional<ClearingCost> expected = Optional.of(grClearingCost);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get cost from a failed request to the BinList")
    public void getCostFailedRequest() {
        when(issuerIdentificationService.identifyCountry(anyString())).thenReturn(Optional.empty());
        Optional<ClearingCost> actual = clearingCostService.getCost(AMERICAN_EXPRESS);
        Optional<ClearingCost> expected = Optional.empty();

        assertEquals(expected, actual);
    }
}

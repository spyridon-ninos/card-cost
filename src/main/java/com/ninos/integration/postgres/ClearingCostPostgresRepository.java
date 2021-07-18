package com.ninos.integration.postgres;

import com.ninos.core.business.model.ClearingCost;
import com.ninos.core.business.spi.ClearingCostRepository;
import com.ninos.integration.postgres.jpa.entities.ClearingCostJpaEntity;
import com.ninos.integration.postgres.jpa.ClearingCostJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * postgres implementation of the clearing cost repository
 */
@Repository
public class ClearingCostPostgresRepository implements ClearingCostRepository {

    private final Logger logger = LoggerFactory.getLogger(ClearingCostPostgresRepository.class);

    private final ClearingCostJpaRepository clearingCostJpaRepository;

    @Autowired
    public ClearingCostPostgresRepository(
            ClearingCostJpaRepository clearingCostJpaRepository
    ) {
        this.clearingCostJpaRepository = clearingCostJpaRepository;
    }

    @Override
    public ClearingCost getCostForCountry(final String country) {

        logger.debug("Searching for {}", country);

        var clearingCost = clearingCostJpaRepository.findClearingCostJpaEntityByCountry(country)
                                                    .map(ClearingCostJpaEntity::toModel)
                                                    .orElseGet(() -> clearingCostJpaRepository.findClearingCostJpaEntityByCountry("OTHERS")
                                                                                              .map(ClearingCostJpaEntity::toModel)
                                                                                              .orElse(new ClearingCost("N/A", new BigDecimal("0"))));

        logger.debug("Returning cost clearing: {}", clearingCost);

        return clearingCost;
    }

    @Override
    @Transactional
    public ClearingCost saveClearingCost(final ClearingCost clearingCost) {
        var result = clearingCostJpaRepository.save(new ClearingCostJpaEntity(clearingCost));
        return result.toModel();
    }

    @Override
    @Transactional
    public void deleteByCountry(final String country) {
        clearingCostJpaRepository.deleteClearingCostJpaEntityByCountry(country);
    }

    @Override
    @Transactional
    public ClearingCost updateClearingCost(final ClearingCost clearingCost) {

        clearingCostJpaRepository.updateClearingCostByCountry(clearingCost.getCountry(), clearingCost.getCost());

        return getCostForCountry(clearingCost.getCountry());
    }

    @Override
    public List<ClearingCost> getAvailableClearingCosts() {
        return clearingCostJpaRepository.findAll()
                                        .stream()
                                        .map(ClearingCostJpaEntity::toModel)
                                        .collect(Collectors.toList());
    }
}

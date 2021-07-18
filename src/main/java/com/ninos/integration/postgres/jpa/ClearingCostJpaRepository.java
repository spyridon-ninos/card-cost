package com.ninos.integration.postgres.jpa;

import com.ninos.integration.postgres.jpa.entities.ClearingCostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * jpa implementation of the clearingCostRepository, provides basic CRUD capabilities
 */
public interface ClearingCostJpaRepository extends JpaRepository<ClearingCostJpaEntity, Long> {

    Optional<ClearingCostJpaEntity> findClearingCostJpaEntityByCountry(String country);

    void deleteClearingCostJpaEntityByCountry(String country);

    @Modifying
    @Transactional
    @Query("UPDATE ClearingCost c SET c.cost = :cost WHERE c.country = :country")
    void updateClearingCostByCountry(@Param("country") String country, @Param("cost") BigDecimal cost);
}

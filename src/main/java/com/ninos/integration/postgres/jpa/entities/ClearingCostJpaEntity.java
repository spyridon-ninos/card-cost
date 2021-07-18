package com.ninos.integration.postgres.jpa.entities;

import com.ninos.core.business.model.ClearingCost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * models a clearing cost row from the clearing_costs table in the db
 */
@Entity(name = "ClearingCost")
@Table(name = "clearing_costs")
public class ClearingCostJpaEntity {

    @Id
    @Column(name = "country")
    private String country;

    @Column(name = "cost")
    private BigDecimal cost;

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

    public ClearingCost toModel() {
        return new ClearingCost(country, cost);
    }

    public ClearingCostJpaEntity() {
    }

    public ClearingCostJpaEntity(ClearingCost clearingCost) {
        country = clearingCost.getCountry();
        cost = clearingCost.getCost();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClearingCostJpaEntity that = (ClearingCostJpaEntity) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, cost);
    }

    @Override
    public String toString() {
        return "ClearingCostJpaEntity{" +
                "country='" + country + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}

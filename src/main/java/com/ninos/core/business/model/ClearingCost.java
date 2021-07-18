package com.ninos.core.business.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

/**
 * models the clearing cost of a country
 */
public final class ClearingCost implements Comparator<ClearingCost> {

    private String country;
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

    public ClearingCost() {
    }

    public ClearingCost(final String country, final BigDecimal cost) {
        this.country = country;
        this.cost = cost;
    }

    @Override
    public int compare(final ClearingCost o1, final ClearingCost o2) {
        return Comparator.comparing(ClearingCost::getCountry)
                         .compare(o1, o2);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClearingCost clearingCost = (ClearingCost) o;
        return Objects.equals(country, clearingCost.country) &&
                Objects.equals(cost, clearingCost.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, cost);
    }

    @Override
    public String toString() {
        return "ClearingCost{" +
                "country='" + country + '\'' +
                ", cost=" + cost +
                '}';
    }
}

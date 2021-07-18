package com.ninos.service.model.responses;

import com.ninos.core.business.model.ClearingCost;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * models the response from the call to the /payment-cards-cost endpoint
 */
@Schema(
        name = "ClearingCostResponse",
        description = "Response that included the clearing cost value of a country"
)
public final class ClearingCostResponse {

    @JsonProperty("country")
    @NotEmpty
    private String country;

    @JsonProperty("cost")
    @NotNull
    @Min(0)
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
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

    public ClearingCostResponse() {
    }

    public ClearingCostResponse(ClearingCost clearingCost) {
        this.country = clearingCost.getCountry();
        this.cost = clearingCost.getCost();
    }

    // put here mainly for testing
    public ClearingCost asClearingCost() {
        return new ClearingCost(country, cost);
    }

    @Override
    public String toString() {
        return "ClearingCostResponse{" +
                "country='" + country + '\'' +
                ", cost=" + cost +
                '}';
    }
}

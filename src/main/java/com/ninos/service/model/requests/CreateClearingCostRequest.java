package com.ninos.service.model.requests;

import com.ninos.core.business.model.ClearingCost;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * models the request sent by POSTing to the /api/v1/clearings endpoint
 */
@Schema(
        name = "CreateClearingCostRequest",
        description = "Request to create a new clearing cost value based on the provided country"
)
public final class CreateClearingCostRequest {

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

    public ClearingCost toModel() {
        return new ClearingCost(country, cost);
    }
}

package com.ninos.service.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * models the request sent ny PUTing to the /api/v1/clearings/<country> endpoint
 */
@Schema(
        name = "UpdateClearingCostRequest",
        description = "Request to update the clearing cost value of a provided country"
)
public final class UpdateClearingCostRequest {

    @JsonProperty("cost")
    @NotNull
    @Min(0)
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "UpdateClearingCostRequest{" +
                "cost='" + cost + '\'' +
                '}';
    }
}

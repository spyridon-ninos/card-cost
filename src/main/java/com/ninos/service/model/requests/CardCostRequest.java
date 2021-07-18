package com.ninos.service.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotEmpty;

/**
 * models the request to the /payment-cards-cost endpoint
 */
@Schema(
        name = "interview-projectRequest",
        description = "Request to get the clearing cost based on a credit card number"
)
public final class CardCostRequest {

    @JsonProperty("card_number")
    @NotEmpty
    @CreditCardNumber
    private String cardNumber;

    public CardCostRequest() {
    }

    public CardCostRequest(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "interview-projectRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}

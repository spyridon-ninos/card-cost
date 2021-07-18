package com.ninos.core.business.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardUtilsTest {

    // taken from: https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
    private static final String AMERICAN_EXPRESS = "378282246310005";
    private static final String INVALID_CARD = "1234567891234567";

    @Test
    @DisplayName("Credit Card is valid")
    public void ccIsValid() {
        assertTrue(CardUtils.isValid(AMERICAN_EXPRESS));
    }

    @Test
    @DisplayName("Credit Card is invalid")
    public void ccIsInvalid() {
        assertFalse(CardUtils.isValid(INVALID_CARD));
    }

    @Test
    @DisplayName("Get IIN from valid card")
    public void iinFromValidCard() {
        assertEquals("378282", CardUtils.getIinFromCard(AMERICAN_EXPRESS));
    }

    @Test
    @DisplayName("Get IIN from invalid card")
    public void iinFromInvalidCard() {
        assertThrows(IllegalArgumentException.class, () -> CardUtils.getIinFromCard(INVALID_CARD));
    }
}

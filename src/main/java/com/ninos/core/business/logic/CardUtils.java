package com.ninos.core.business.logic;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

/**
 * utility methods for credit cards
 */
public final class CardUtils {

    public static boolean isValid(String card) {
        return LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(card);
    }

    public static String getIinFromCard(String card) {
        if (!isValid(card)) {
            throw new IllegalArgumentException("Invalid credit card number");
        }

        return card.substring(0, 6);
    }
}

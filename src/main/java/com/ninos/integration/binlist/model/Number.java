package com.ninos.integration.binlist.model;

/**
 *
 * models the "number" section of the BinList response
 *
 * From the site:
 *
 * <pre>
 *   "number": {
 *     "length": 16,
 *     "luhn": true
 *   }
 * </pre>
 */
public final class Number {

    private int length;
    private boolean luhn;

    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public boolean isLuhn() {
        return luhn;
    }

    public void setLuhn(final boolean luhn) {
        this.luhn = luhn;
    }

    @Override
    public String toString() {
        return "Number{" +
                "length=" + length +
                ", luhn=" + luhn +
                '}';
    }
}

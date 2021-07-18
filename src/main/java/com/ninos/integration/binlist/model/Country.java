package com.ninos.integration.binlist.model;

/**
 *
 * models the section "country" from the BinList response
 *
 * From the site:
 *
 * <pre>
 *   "country": {
 *     "numeric": "208",
 *     "alpha2": "DK",
 *     "name": "Denmark",
 *     "emoji": "🇩🇰",
 *     "currency": "DKK",
 *     "latitude": 56,
 *     "longitude": 10
 *   }
 * </pre>
 */
public final class Country {

    private int numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private int latitude;
    private int longitude;

    public int getNumeric() {
        return numeric;
    }

    public void setNumeric(final int numeric) {
        this.numeric = numeric;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(final String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(final String emoji) {
        this.emoji = emoji;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(final int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(final int longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Country{" +
                "numeric=" + numeric +
                ", alpha2='" + alpha2 + '\'' +
                ", name='" + name + '\'' +
                ", emoji='" + emoji + '\'' +
                ", currency='" + currency + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

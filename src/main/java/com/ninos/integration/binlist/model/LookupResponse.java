package com.ninos.integration.binlist.model;

/**
 *
 * models the response from the BinList site
 *
 * From the site:
 *
 * <pre>
 * {
 *   "number": {
 *     "length": 16,
 *     "luhn": true
 *   },
 *   "scheme": "visa",
 *   "type": "debit",
 *   "brand": "Visa/Dankort",
 *   "prepaid": false,
 *   "country": {
 *     "numeric": "208",
 *     "alpha2": "DK",
 *     "name": "Denmark",
 *     "emoji": "🇩🇰",
 *     "currency": "DKK",
 *     "latitude": 56,
 *     "longitude": 10
 *   },
 *   "bank": {
 *     "name": "Jyske Bank",
 *     "url": "www.jyskebank.dk",
 *     "phone": "+4589893300",
 *     "city": "Hjørring"
 *   }
 * }
 * </pre>
 */
public final class LookupResponse {

    private Number number;
    private String scheme;
    private String type;
    private String brand;
    private boolean prepaid;
    private Country country;
    private Bank bank;

    public Number getNumber() {
        return number;
    }

    public void setNumber(final Number number) {
        this.number = number;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public boolean isPrepaid() {
        return prepaid;
    }

    public void setPrepaid(final boolean prepaid) {
        this.prepaid = prepaid;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(final Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "LookupResponse{" +
                "number=" + number +
                ", scheme='" + scheme + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", prepaid=" + prepaid +
                ", country=" + country +
                ", bank=" + bank +
                '}';
    }
}

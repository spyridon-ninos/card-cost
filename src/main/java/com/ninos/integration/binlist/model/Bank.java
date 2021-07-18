package com.ninos.integration.binlist.model;

/**
 *
 * models the "bank" section of the BinList response
 *
 * From the site:
 *
 * <pre>
 *   "bank": {
 *     "name": "Jyske Bank",
 *     "url": "www.jyskebank.dk",
 *     "phone": "+4589893300",
 *     "city": "Hjørring"
 *   }
 * </pre>
 */
public final class Bank {

    private String name;
    private String url;
    private String phone;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

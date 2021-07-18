package com.ninos.core.business.spi;

import java.util.Optional;

/**
 * SPI for the issuer identification service
 */
public interface IssuerIdentificationService {
    Optional<String> identifyCountry(String iin);
}

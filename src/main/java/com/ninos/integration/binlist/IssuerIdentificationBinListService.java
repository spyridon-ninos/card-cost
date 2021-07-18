package com.ninos.integration.binlist;

import com.ninos.core.business.spi.IssuerIdentificationService;
import com.ninos.integration.binlist.model.Country;
import com.ninos.integration.binlist.model.LookupResponse;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Optional;

/**
 * implementation of the IssuerIdentificationService SPI, using the BinList site as the
 * identification provider
 */
@Component
public class IssuerIdentificationBinListService implements IssuerIdentificationService {

    private final Logger logger = LoggerFactory.getLogger(IssuerIdentificationBinListService.class);

    // BinList SLA: 10 per min, burst of 10
    private final Refill binListSlaRefill = Refill.greedy(10, Duration.ofMinutes(1));
    private final Bucket bucket = Bucket4j.builder()
                                          .addLimit(Bandwidth.classic(10, binListSlaRefill))
                                          .build();

    private final String BASE_URL = "https://lookup.binlist.net/";

    private final RestTemplate restTemplate;

    @Autowired
    public IssuerIdentificationBinListService(
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<String> identifyCountry(final String iin) {

        // enforce BinList SLAs
        if (bucket.tryConsume(1)) {

            logger.debug("Getting info from BitList, for the IIN: {}", iin);

            var headers = new HttpHeaders();
            headers.add("Accept-Version", "3");

            var httpEntity = new HttpEntity<LookupResponse>(headers);
            ResponseEntity<LookupResponse> response;

            try {
                response = restTemplate.exchange(BASE_URL + iin, HttpMethod.GET, httpEntity, LookupResponse.class);
            } catch (Exception ex) {
                logger.error("Received exception: {}", ex.getMessage());
                logger.debug("{}", ex.getMessage(), ex);
                return Optional.empty();
            }

            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.error("Error when looking up IIN. Status: {}", response.getStatusCode());
                return Optional.empty();
            }

            logger.debug("Retrieved: {}", response.getBody());

            return Optional.ofNullable(response.getBody())
                           .map(LookupResponse::getCountry)
                           .map(Country::getAlpha2);
        }

        logger.error("Reached traffic throttle (10 req/min). Skipping request for IIN: {}", iin);

        return Optional.empty();
    }
}

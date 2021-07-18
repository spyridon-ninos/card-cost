package com.ninos.service.endpoints;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Functional System Integration Test (FSIT) for the ClearingCostEndpoint class
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = { "classpath:application-integration-test.properties" })
@Import(ClearingCostEndpointFSIT.ClearingCostEndpointTestConfiguration.class)
public class ClearingCostEndpointFSIT {

    @TestConfiguration
    public static class ClearingCostEndpointTestConfiguration {

        @Bean
        public DataSource getDataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName("org.h2.Driver");
            dataSourceBuilder.url("jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
            dataSourceBuilder.username("interview-projectuser");
            dataSourceBuilder.password("password");
            return dataSourceBuilder.build();
        }
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Get the clearing costs for GR")
    public void readGr() {

        final String url = "http://localhost:" + port + "/health";

        var response = testRestTemplate.getForEntity(url, HealthResponse.class);

        final var actual = Optional.ofNullable(response.getBody())
                             .map(HealthResponse::getStatus)
                             .orElse("N/A");

        final var expected = "UP";

        assertEquals(expected, actual);
    }

}

// for testing only
class HealthResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

package com.ninos.service.endpoints;

import com.ninos.core.business.api.CostApi;
import com.ninos.core.business.model.ClearingCost;
import com.ninos.service.model.requests.CardCostRequest;
import com.ninos.service.model.responses.ClearingCostResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Component Integration Test (CIT) for the ClearingCostEndpoint class
 */
@WebMvcTest(ClearingCostEndpoint.class)
@TestPropertySource(locations = { "classpath:application-integration-test.properties" })
public class ClearingCostEndpointCIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CostApi costApi;

    private final String AMERICAN_EXPRESS = "378282246310005"; // taken from: https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm
    private final ClearingCost grClearingCost = new ClearingCost("GR", new BigDecimal("10"));
    private final CardCostRequest cardCostRequest = new CardCostRequest(AMERICAN_EXPRESS);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        when(costApi.getCost(anyString())).thenReturn(Optional.of(grClearingCost));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void postCardCost() throws Exception {

        var requestBody = objectMapper.writeValueAsString(cardCostRequest);

        var requestBuilder = post("/api/v1/clearings/payment-cards-cost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        var mvcResult = mockMvc.perform(requestBuilder)
                               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                               .andExpect(status().is2xxSuccessful())
                               .andReturn();

        var response = mvcResult.getResponse()
                                .getContentAsString();

        var clearingCostResponse = objectMapper.readValue(response, ClearingCostResponse.class);
        var actual = clearingCostResponse.asClearingCost();

        assertEquals(grClearingCost, actual);
    }
}

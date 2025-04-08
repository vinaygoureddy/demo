package com.example.demo.challenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FetchLoginService {

    @Autowired
    private RestTemplate restTemplate;

    public String fetchLogin(String threeDSServerTransID) {
        try {
            // Make a GET request to the external service
            // Configure the URL of the other service's endpoint that returns the string
            String externalServiceUrl = "https://intense-falls-74604-086831f5c8f8.herokuapp.com/api/login/"+threeDSServerTransID;
            ResponseEntity<String> response = restTemplate.exchange(externalServiceUrl, HttpMethod.GET, null, // No request body needed for a GET request
                    String.class // Specify the expected response type as String
            );

            // Check if the request was successful (HTTP status code 2xx)
            if (response.getStatusCode().is2xxSuccessful()) {
                // Get the string from the response body
                return response.getBody();
            } else {
                log.error("Error fetching string. Status code: " + response.getStatusCode());
                return null; // Or throw a custom exception
            }
        } catch (Exception e) {
            log.error("Exception while fetching string: " + e.getMessage());
            return null; // Or throw a custom exception
        }
    }
}

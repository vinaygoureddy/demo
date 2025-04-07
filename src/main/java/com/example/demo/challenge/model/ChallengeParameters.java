package com.example.demo.challenge.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeParameters {
    private String submitChallengeToUrl;
    private String threeDSServerTransID;
    private String cres;
    private String actionUrl;
}

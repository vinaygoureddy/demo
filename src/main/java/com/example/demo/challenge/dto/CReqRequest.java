package com.example.demo.challenge.dto;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.example.demo.challenge.enums.ChallengeAction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CReqRequest {
    private ChallengeAction action;
    private String data;
    private String creq;
    private String challengeDataEntry;
    private String whitelistingDataEntry;
    private String trustListDataEntry;
    private String threeDSServerTransID;
    private String threeDSSessionData;
    private String deviceBindingDataEntry;

    public boolean hasAction() {
        return action != null;
    }
}

package com.example.demo.challenge.service;

import com.example.demo.challenge.dto.CReqRequest;
import com.example.demo.challenge.exception.TransientSystemException;
import com.example.demo.challenge.model.CRes;
import com.example.demo.challenge.model.ChallengeParameters;
import com.example.demo.challenge.utils.JsonUtil;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class ChallengeService {

    private final MustacheTemplatesConfigurationService mustacheTemplatesConfigurationService;
    private final JsonUtil jsonUtil;
    private final String completeChallengeUrl;

    public ChallengeService(MustacheTemplatesConfigurationService mustacheTemplatesConfigurationService,
                            JsonUtil jsonUtil,
                            @Value("${app.complete-challenge-url}") String completeChallengeUrl) {
        this.mustacheTemplatesConfigurationService = mustacheTemplatesConfigurationService;
        this.jsonUtil = jsonUtil;
        this.completeChallengeUrl = completeChallengeUrl;
    }

    public String doChallenge(CReqRequest cReqRequest) {
        if (cReqRequest.hasAction()) {
            return switch (cReqRequest.getAction()) {
                case dataEntered -> completeChallenge();
                default -> throw new TransientSystemException("The selected action for challenge transfer is not valid.");
            };
        }

        return initialChallenge(cReqRequest);
    }

    private String completeChallenge() {
        Template template = mustacheTemplatesConfigurationService.getTemplate(MustacheTemplatesConfigurationService.CHALLENGES_COMPLETION_NOTIFICATION);
        ChallengeParameters challengeParameters = ChallengeParameters.builder()
                .actionUrl(completeChallengeUrl)
                .cres(Base64.getUrlEncoder().withoutPadding().encodeToString(jsonUtil.toJson(
                        CRes.builder()
                                .acsTransID(UUID.randomUUID().toString())
                                .build()
                ).getBytes(StandardCharsets.UTF_8)))
                .build();
        String result = template.execute(challengeParameters);
        log.debug("Challenge result: {}", result);
        return result;
    }

    private String initialChallenge(CReqRequest cReqRequest) {
        Template template = mustacheTemplatesConfigurationService.getTemplate(MustacheTemplatesConfigurationService.BROWSER_OTP_CHALLENGE);
        ChallengeParameters challengeParameters = ChallengeParameters.builder()
                .submitChallengeToUrl("/api/v1/browser_challenges")
                .threeDSServerTransID(cReqRequest.getThreeDSServerTransID())
                .build();

        return template.execute(challengeParameters);
    }
}

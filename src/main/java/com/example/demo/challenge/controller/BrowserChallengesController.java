package com.example.demo.challenge.controller;

import com.example.demo.challenge.dto.CReqRequest;
import com.example.demo.challenge.enums.ChallengeAction;
import com.example.demo.challenge.enums.MessageType;
import com.example.demo.challenge.exception.InvalidDataElementException;
import com.example.demo.challenge.exception.TransientSystemException;
import com.example.demo.challenge.model.CReq;
import com.example.demo.challenge.service.ChallengeService;
import com.example.demo.challenge.service.FetchLoginService;
import com.example.demo.challenge.utils.Cache;
import com.example.demo.challenge.utils.JsonUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class BrowserChallengesController {

    private final ChallengeService challengeService;
    private final JsonUtil jsonUtil;
    private final FetchLoginService fetchLoginService;
    private Cache cache = new Cache();

    public BrowserChallengesController(ChallengeService challengeService, JsonUtil jsonUtil, FetchLoginService fetchLoginService) {
        this.challengeService = challengeService;
        this.jsonUtil = jsonUtil;
        this.fetchLoginService = fetchLoginService;
    }

    @PostMapping(value = "api/v1/browser_challenges", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String browserChallenges(@RequestParam(required = false) String action, @RequestParam(required = false) String creq, @RequestParam(required = false) String challengeDataEntry, @RequestParam(required = false) String whitelistingDataEntry, @RequestParam(required = false) String trustListDataEntry, @RequestParam(required = false) String threeDSServerTransID, @RequestParam(required = false) String threeDSSessionData, @RequestParam(required = false) String deviceBindingDataEntry) {
        log.debug("Entered 'browserChallenges', action: {}, creq: {}, challengeDataEntry: {}, " + "threeDSServerTransID: {}, threeDSSessionData: {}, deviceBindingDataEntry: {}", action, creq, challengeDataEntry, threeDSServerTransID, threeDSSessionData, deviceBindingDataEntry);

        if (StringUtils.isBlank(creq) && StringUtils.isBlank(action) && StringUtils.isBlank("threeDSServerTransID")) {
            throw new InvalidDataElementException(MessageType.CREQ, "creq");
        }
        CReqRequest cReqRequest= cache.getFromCache(threeDSServerTransID);
        if(cReqRequest!=null) {
            log.info("found matching cReqRequest: {}", cReqRequest);
           return challengeService.doChallenge(cReqRequest);
        }

        CReqRequest.CReqRequestBuilder cReqRequestBuilder = CReqRequest.builder().action(ChallengeAction.getByValue(action)).data(creq).challengeDataEntry(challengeDataEntry).whitelistingDataEntry(whitelistingDataEntry).trustListDataEntry(trustListDataEntry).threeDSServerTransID(threeDSServerTransID).threeDSSessionData(threeDSSessionData).deviceBindingDataEntry(deviceBindingDataEntry);
        if (StringUtils.isNotBlank(creq)) {
            try {
                CReq cReq = jsonUtil.fromJson(new String(Base64.getDecoder().decode(creq)), CReq.class);
                if(cache.getFromCache(cReq.getThreeDSServerTransID())==null){
                    //TODO: find a better way for this
                    cReqRequestBuilder.threeDSServerTransID(cReq.getThreeDSServerTransID());
                    cache.addToCache(cReq.getThreeDSServerTransID(),cReqRequestBuilder.build());
                    log.info("Initializing browser challenges");
                    //return fetchLoginService.fetchLogin(cReq.getThreeDSServerTransID());
                }
                cReqRequestBuilder.threeDSServerTransID(cReq.getThreeDSServerTransID());

            } catch (Exception e) {
                // can not issue an RReq here as there's no transaction known to find DS URL.
                throw new TransientSystemException("Failed to decode/parse CReq", e, MessageType.CREQ);
            }
        }


        return challengeService.doChallenge(cReqRequestBuilder.build());
    }

}

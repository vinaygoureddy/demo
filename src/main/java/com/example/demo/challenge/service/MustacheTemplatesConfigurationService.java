package com.example.demo.challenge.service;


import com.example.demo.challenge.exception.AppInitializationException;
import com.example.demo.challenge.utils.IOUtils;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class MustacheTemplatesConfigurationService {

    public static final String BROWSER_OTP_CHALLENGE = "browser-otp-challenge";
    public static final String CHALLENGES_COMPLETION_NOTIFICATION = "challenges-completion-notification";

    private final String[] sources = {
            BROWSER_OTP_CHALLENGE,
            CHALLENGES_COMPLETION_NOTIFICATION
    };

    private final Map<String, Template> templates;

    public MustacheTemplatesConfigurationService() {
        templates = new HashMap<>();
        Arrays.asList(sources).forEach(this::processTemplate);
    }

    public Template getTemplate(String fileName) {
        return templates.get(fileName);
    }

    private void processTemplate(String templateFile) {
        try {
            String markup = IOUtils.readClasspathResourceAsString("/templates/" + templateFile + ".html");
            Template template = Mustache.compiler().escapeHTML(false).compile(markup);
            templates.put(templateFile, template);
        } catch (IOException e) {
            throw new AppInitializationException("Failed to compile mustache template from '" + templateFile + "' file", e);
        }
    }
}

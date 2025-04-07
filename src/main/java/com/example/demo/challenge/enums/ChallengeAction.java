package com.example.demo.challenge.enums;

import javax.tools.JavaCompiler;

public enum ChallengeAction {
    dataEntered, cancelled, resend;

    public static ChallengeAction getByValue(String value) {
        for (ChallengeAction action : values()) {
            if (action.name().equals(value)) {
                return action;
            }
        }
        return null;
    }
}

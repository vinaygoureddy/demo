/*
 Copyright © mSIGNIA, Incorporated, 2007.  All rights reserved.

 Software is protected by one or more of U.S. Patent No. 9,559,852, 9294448, 8,817,984,
 international patents and others pending. For more information see www.mSIGNIA.com.  User agrees
 that they will not them self, or through any affiliate, agent or other third-party, entity or
 other business structure remove, alter, cover or obfuscate any copyright notices or other
 proprietary rights notices of mSIGNIA or its licensors.  User agrees that they will not them
 self, or through any affiliate, agent or other third party, entity or other business structure
 (a) reproduce, sell, lease, license or sublicense this software or any part thereof, (b)
 decompile, disassemble, re-program, reverse engineer or otherwise attempt to derive or modify
 this software in whole or in part, (c) write or develop any derivative software or any other
 software program based upon this software, (d) provide, copy, transmit, disclose, divulge, or
 make available to, or permit use of this software by any third party or entity or machine without
 software owner's prior written consent, (e) circumvent or disable any security or other
 technological features or measures used by this software.
*/

package com.example.demo.challenge.enums;

public enum MessageType {

    AREQ("AReq", "areq"),
    ARES("ARes", "ares"),
    PREQ("PReq", "preq"),
    RREQ("RReq", "rreq"),
    RRES("RRes", "rres"),
    ERRO("Erro", "erro"),
    CREQ("CReq", "creq"),
    CRES("CRes", "cres"),
    OREQ("OReq", "oreq"),
    ORES("ORes", "ores");

    private final String messageCode;
    private final String schemaCode;

    MessageType(String messageCode, String schemaCode) {
        this.messageCode = messageCode;
        this.schemaCode = schemaCode;
    }

    public static MessageType lookupByMessageCode(String code) {
        for (MessageType messageType : values()) {
            if (messageType.messageCode.equals(code)) {
                return messageType;
            }
        }
        return null;
    }

    public static boolean isAReq(String messageType) {
        return isMessageCodeMatchType(messageType, AREQ);
    }

    public static boolean isErro(String messageType) {
        return isMessageCodeMatchType(messageType, ERRO);
    }

    public static boolean isCreq(String messageType) {
        return isMessageCodeMatchType(messageType, CREQ);
    }

    public static boolean isOreq(String messageType) {
        return isMessageCodeMatchType(messageType, OREQ);
    }

    public static boolean isMessageCodeMatchType(String messageType, MessageType type) {
        MessageType targetType = lookupByMessageCode(messageType);
        return type != null && targetType == type;
    }

    public String messageCode() {
        return messageCode;
    }

    public String schemaCode() {
        return schemaCode;
    }
}
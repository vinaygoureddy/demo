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

import lombok.Getter;

@Getter
public enum ErrorCode {

    DATA_DECRYPTION_FAILURE(
            "Data Decryption Failure",
            "302",
            "Data could not be decrypted by the receiving system due to technical or other reason"),

    MESSAGE_VERSION_NUMBER_NOT_SUPPORTED(
            "Message Version Number Not Supported",
            "102",
            "Message Version Number received is not valid for the receiving component."),

    ACCESS_DENIED_INVALID_ENDPOINT("Access Denied, " +
            "Invalid Endpoint",
            "303",
            "Access Denied, Invalid Endpoint"),

    MESSAGE_RECEIVED_INVALID(
            "Message Received Invalid",
            "101",
            "Message Received Invalid"),

    TRANSACTION_TIMED_OUT(
            "Transaction Timed Out",
            "402",
            "Transaction timed-out"),

    SYSTEM_CONNECTION_FAILURE(
            "System Connection Failure",
            "405",
            "System connection failure"),

    PERMANENT_SYSTEM_FAILURE(
            "Permanent System Failure",
            "404",
            "Permanent system failure"),

    TRANSIENT_SYSTEM_FAILURE(
            "Transient System Failure",
            "403",
            "Transient system failure"),

    MERCHANT_CATEGORY_CODE_NOT_VALID_FOR_PAYMENT_SYSTEM(
            "Merchant Category Code (MCC) Not Valid for Payment System",
            "306",
            "Merchant Category Code (MCC) not valid for Payment System."),

    TRANSACTION_DATA_NOT_VALID(
            "Transaction data not valid",
            "305",
            "Cardholder Account Number is not in a range belonging to an issuer"),

    INVALID_FIDO_CREDENTIALS(
            "Transaction data not valid",
            "305",
            "Invalid FIDO credentials"),

    INVALID_DATA_ELEMENT(
            "Format of one or more Data Elements is Invalid according to the Specification",
            "203",
            "Format of one or more Data Elements is Invalid according to the Specification"),

    SERIAL_NUMBER_NOT_VALID(
            "Serial Number not Valid",
            "307",
            "Serial Number not Valid"),

    REQUIRED_DATA_ELEMENT_MISSING(
            "A required message element is missing from the message.",
            "201",
            "A required message element is missing from the message."),

    TRANSACTION_ID_NOT_RECOGNIZED(
            "Transaction ID Not Recognised",
                    "301",
                    "Transaction ID received is not valid for the receiving component.");

    private final String errorCodeText;
    private final String errorCode;
    private final String errorDescription;


    ErrorCode(String errorCodeText, String errorCode, String errorDescription) {
        this.errorCodeText = errorCodeText;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public static ErrorCode byErroCode(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getErrorCode().equals(code)) {
                return errorCode;
            }
        }
        return null;
    }
}

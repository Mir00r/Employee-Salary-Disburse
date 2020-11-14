package com.mir00r.salarydisburse.domains.common.services;

public interface SmsService {
    boolean sendSms(String phoneNumber, String message);
}

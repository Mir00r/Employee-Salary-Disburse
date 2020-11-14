package com.mir00r.salarydisburse.domains.common.services;

public interface MailService {
    boolean sendEmail(String email, String subject, String message);
}

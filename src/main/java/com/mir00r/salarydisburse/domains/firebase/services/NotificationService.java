package com.mir00r.salarydisburse.domains.firebase.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mir00r.salarydisburse.domains.firebase.models.dto.PushNotification;
import com.mir00r.salarydisburse.exceptions.invalid.InvalidException;
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException;
import com.mir00r.salarydisburse.exceptions.unknown.UnknownException;

public interface NotificationService {
    void sendNotification(Long userId, PushNotification notification) throws InvalidException, NotFoundException, JsonProcessingException, UnknownException;

    void sendNotification(PushNotification notification) throws InvalidException, JsonProcessingException, UnknownException;

}

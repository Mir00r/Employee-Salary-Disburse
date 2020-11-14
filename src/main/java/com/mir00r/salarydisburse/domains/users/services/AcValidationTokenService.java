package com.mir00r.salarydisburse.domains.users.services;


import com.mir00r.salarydisburse.domains.users.models.entities.AcValidationToken;
import com.mir00r.salarydisburse.domains.users.models.entities.User;
import com.mir00r.salarydisburse.exceptions.forbidden.ForbiddenException;

public interface AcValidationTokenService {
    AcValidationToken save(AcValidationToken acValidationToken);
    AcValidationToken findOne(Long id);
    AcValidationToken findByToken(String token) throws ForbiddenException;
    void delete(Long id);
    boolean isTokenValid(String token) throws ForbiddenException;
    boolean isLimitExceeded(User user);

    boolean canGetOTP(String username);
}

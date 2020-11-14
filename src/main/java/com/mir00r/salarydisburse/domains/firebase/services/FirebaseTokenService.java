package com.mir00r.salarydisburse.domains.firebase.services;


import com.mir00r.salarydisburse.domains.firebase.models.entities.FirebaseUserToken;
import com.mir00r.salarydisburse.exceptions.invalid.InvalidException;
import com.mir00r.salarydisburse.exceptions.notfound.FirebaseTokenNotFoundException;
import com.mir00r.salarydisburse.exceptions.notfound.UserNotFoundException;

public interface FirebaseTokenService {
    FirebaseUserToken save(FirebaseUserToken token) throws InvalidException;

    FirebaseUserToken get(Long userId) throws FirebaseTokenNotFoundException;

    FirebaseUserToken save(Long userId, String token) throws InvalidException, UserNotFoundException;
}

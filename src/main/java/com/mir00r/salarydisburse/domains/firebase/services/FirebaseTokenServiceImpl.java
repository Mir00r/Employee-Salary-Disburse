package com.mir00r.salarydisburse.domains.firebase.services;

import com.mir00r.salarydisburse.domains.firebase.models.entities.FirebaseUserToken;
import com.mir00r.salarydisburse.domains.firebase.repositories.FirebaseTokenRepository;
import com.mir00r.salarydisburse.domains.users.models.entities.User;
import com.mir00r.salarydisburse.domains.users.repositories.UserRepository;
import com.mir00r.salarydisburse.exceptions.invalid.InvalidException;
import com.mir00r.salarydisburse.exceptions.notfound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseTokenServiceImpl implements FirebaseTokenService {
    private final FirebaseTokenRepository firebaseTokenRepo;
    private final UserRepository userRepository;

    @Autowired
    public FirebaseTokenServiceImpl(FirebaseTokenRepository firebaseTokenRepo,
                                    UserRepository userRepository) {
        this.firebaseTokenRepo = firebaseTokenRepo;
        this.userRepository = userRepository;
    }

    @Override
    public FirebaseUserToken save(FirebaseUserToken token) throws InvalidException {
        if (token == null) throw new InvalidException("Token can not be null!");
        return this.firebaseTokenRepo.save(token);
    }

    @Override
    public FirebaseUserToken get(Long userId) {
        return this.firebaseTokenRepo.findByUserId(userId);
    }

    @Override
    public FirebaseUserToken save(Long userId, String token) throws InvalidException, UserNotFoundException {
        if (userId == null || token == null) throw new InvalidException("userId or token can not be null");
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Could not find user with id: " + userId));
        FirebaseUserToken firebaseUserToken = this.firebaseTokenRepo.findByUserId(userId);
        if (firebaseUserToken != null)
            firebaseUserToken.setUserToken(token);
        else
            firebaseUserToken = new FirebaseUserToken(user, token);
        return this.save(firebaseUserToken);
    }
}

package com.mir00r.salarydisburse.domains.firebase.controllers;


import com.mir00r.salarydisburse.domains.firebase.services.FirebaseTokenService;
import com.mir00r.salarydisburse.domains.users.models.annotations.CurrentUser;
import com.mir00r.salarydisburse.domains.users.models.entities.User;
import com.mir00r.salarydisburse.exceptions.invalid.InvalidException;
import com.mir00r.salarydisburse.exceptions.notfound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/v1/firebase")
public class FirebaseController {
    private final FirebaseTokenService firebaseTokenService;

    @Autowired
    public FirebaseController(FirebaseTokenService firebaseTokenService) {
        this.firebaseTokenService = firebaseTokenService;
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    private void saveToken(@CurrentUser User user,
                           @RequestParam("token") String token) throws UserNotFoundException, InvalidException {
        this.firebaseTokenService.save(user.getId(), token);
    }

}
